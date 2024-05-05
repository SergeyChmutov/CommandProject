package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RecommendationType;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.enums.UserMessageStatus;
import pro.dev.animalshelter.exception.*;
import pro.dev.animalshelter.interfaces.*;
import pro.dev.animalshelter.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.dev.animalshelter.constant.Constants.*;

@Service
public class UpdateService {
    private final TelegramBotSender telegramBotSender;
    private final UserService userService;
    private final ShelterService shelterService;
    private final ShelterInformationInterface informationService;
    private final TravelDirectionsInterface travelDirectionsService;
    private final InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator;
    private final RecommendationInformationInterface recommendationService;
    private final AdoptionReportAnimalPhotoInterface reportAnimalPhotoService;
    private final AdoptionReportInterface reportService;
    private final Map<Long, Long> userCurrentShelterId = new HashMap<>();
    private final Map<Long, UserMessageStatus> userCurrentMessageStatus = new HashMap<>();
    private final Map<Long, AdoptionReport> userCurrentAdoptionReport = new HashMap<>();

    public UpdateService(
            TelegramBotSender telegramBotSender,
            InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator,
            UserService userService, ShelterService shelterService,
            ShelterInformationService informationService,
            TravelDirectionsInterface travelDirectionsService,
            RecommendationInformationInterface recommendationService,
            AdoptionReportAnimalPhotoInterface reportAnimalPhotoService,
            AdoptionReportInterface reportService
    ) {
        this.telegramBotSender = telegramBotSender;
        this.inlineKeyboardMarkupCreator = inlineKeyboardMarkupCreator;
        this.userService = userService;
        this.shelterService = shelterService;
        this.informationService = informationService;
        this.travelDirectionsService = travelDirectionsService;
        this.recommendationService = recommendationService;
        this.reportAnimalPhotoService = reportAnimalPhotoService;
        this.reportService = reportService;
    }

    public void processUpdate(Update update) {
        Message message = update.message();

        if (message != null) {

            if (message.text() != null && message.text().equals("/start")) {
                processStartCommand(update);
            } else {
                processUserUpdate(update);
            }

        } else if (update.callbackQuery() != null) {

            chooseCommand(update);

        }
    }

    private void processStartCommand(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();

        if (!userService.existsById(chatId)) {
            userService.addUser(chatId, update.message().chat().firstName(), null);
            InlineKeyboardMarkup markupChooseShelters = inlineKeyboardMarkupCreator.createKeyboardChooseShelters();
            telegramBotSender.send(chatId, MESSAGE_START, markupChooseShelters);
        } else {
            InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
            telegramBotSender.send(chatId, MESSAGE_RETURN, markupStart);
        }

        userCurrentMessageStatus.remove(chatId);
    }

    private void chooseCommand(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();
        final Integer messageId = callbackQuery.maybeInaccessibleMessage().messageId();

        switch (data) {
            case INFORMATION_ABOUT_SHELTERS_BUTTON:
                chooseShelters(chatId, messageId);
                break;

            case INFORMATION_ABOUT_PETS_BUTTON:
                InlineKeyboardMarkup markupInformationAboutPets = inlineKeyboardMarkupCreator.createKeyboardInformationAboutPets();
                telegramBotSender.editMessageText(
                        chatId,
                        messageId,
                        MESSAGE_INFORMATION_ABOUT_PETS,
                        markupInformationAboutPets
                );
                break;

            case SEND_REPORT_BUTTON:
                updateAdoptionReportCommand(update);
                break;

            case VOLUNTEER_BUTTON:
                telegramBotSender.send(chatId, MESSAGE_VOLUNTEER);
                break;

            case ABOUT_SHELTER_BUTTON:
            case ADDRESS_SHELTER_BUTTON:
            case PASS_SHELTER_BUTTON:
            case ROADMAP_SHELTER_BUTTON:
            case SAFETY_SHELTER_BUTTON:
            case SCHEDULE_SHELTER_BUTTON:
                updateShelterInformationCommands(update);
                break;

            case CHOOSE_PET_BUTTON:
                telegramBotSender.send(chatId, "Здесь будет список животных");
                break;

            case RULES_BUTTON:
            case TRANSPORTATION_BUTTON:
            case DOCUMENTS_BUTTON:
            case PUPPY_HOME_BUTTON:
            case DOG_HOME_BUTTON:
            case ANIMALS_WITH_DISABILITIES_HOME_BUTTON:
            case DOG_HANDLER_BUTTON:
            case ADVICES_DOG_HANDLER_BUTTON:
            case REASONS_FOR_REFUSAL_BUTTON:
                updateRecommendationInformationCommands(update);
                break;

            case CONTACTS_BUTTON:
                getUserContactsInformation(update);
                break;

            case MAIN_MENU_BUTTON:
                returnToMainMenu(chatId, messageId);
                break;

            default:
                sendShelterMessage(chatId, messageId, data);
                break;
        }
    }

    private void chooseShelters(Long chatId, Integer messageId) {
        InlineKeyboardMarkup markupChooseShelters = inlineKeyboardMarkupCreator.createKeyboardChooseShelters();
        telegramBotSender.editMessageText(chatId, messageId, MESSAGE_CHOOSE_SHELTERS, markupChooseShelters);
    }

    private void sendShelterMessage(Long chatId, Integer messageId, String data) {
        long shelterId = Long.parseLong(data);
        userCurrentShelterId.put(chatId, shelterId);
        Shelter shelter = shelterService.getShelter(shelterId);
        InlineKeyboardMarkup markupInformationAboutShelter = inlineKeyboardMarkupCreator.createKeyboardInformationAboutShelter();
        String message = "Добро пожаловать в приют " + shelter.getName() + "! " + MESSAGE_INFORMATION_ABOUT_SHELTER;
        telegramBotSender.editMessageText(chatId, messageId, message, markupInformationAboutShelter);
    }

    private void updateRecommendationInformationCommands(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();
        final Integer messageId = callbackQuery.maybeInaccessibleMessage().messageId();

        String information = getRecommendationInformationByCommand(data);

        telegramBotSender.editMessageText(chatId, messageId, information, callbackQuery.message().replyMarkup());
    }

    private String getRecommendationInformationByCommand(String command) {
        String recommendation = "";
        try {
            recommendation = switch (command) {
                case RULES_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.DATING_RULES.getTypeName()
                ).getInformation();
                case TRANSPORTATION_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.TRANSPORTATIONS.getTypeName()
                ).getInformation();
                case DOCUMENTS_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.DOCUMENTS.getTypeName()
                ).getInformation();
                case PUPPY_HOME_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.PUPPY_HOME.getTypeName()
                ).getInformation();
                case DOG_HOME_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.DOG_HOME.getTypeName()
                ).getInformation();
                case ANIMALS_WITH_DISABILITIES_HOME_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.DISABILITIES.getTypeName()
                ).getInformation();
                case DOG_HANDLER_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.DOG_HANDLER.getTypeName()
                ).getInformation();
                case ADVICES_DOG_HANDLER_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.ADVICES_DOG_HANDLER.getTypeName()
                ).getInformation();
                case REASONS_FOR_REFUSAL_BUTTON -> recommendationService.getInformationBy(
                        RecommendationType.REASONS_REFUSAL.getTypeName()
                ).getInformation();
                default -> "";
            };
        } catch (RecommendationInformationNotFoundException e) {
            recommendation = "Такая информация на текущий момент не занесена";
        } catch (RecommendationTypeErrorNameException e) {
            recommendation = "Информация такого типа мне не известна";
        }

        return recommendation;
    }

    private void updateShelterInformationCommands(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();
        final Integer messageId = callbackQuery.maybeInaccessibleMessage().messageId();
        InlineKeyboardMarkup markup = callbackQuery.message().replyMarkup();

        if (!userCurrentShelterId.containsKey(chatId)) {
            chooseShelters(chatId, messageId);
            return;
        }

        final Long shelterId = userCurrentShelterId.get(chatId);

        String data = callbackQuery.data();

        if (data.equals(ROADMAP_SHELTER_BUTTON)) {
            sendRoadmapInformation(shelterId, chatId, messageId, markup);
        } else {
            ShelterInformation information = getShelterInformationByCommand(shelterId, data);
            telegramBotSender.editMessageText(
                    chatId,
                    messageId,
                    information.getDescription(),
                    callbackQuery.message().replyMarkup()
            );
        }
    }

    private ShelterInformation getShelterInformationByCommand(Long shelterId, String command) {
        ShelterInformation information;
        try {
            information = switch (command) {
                case ABOUT_SHELTER_BUTTON -> informationService.getPropertyByShelterAndName(
                        shelterId,
                        ShelterInformationProperty.ABOUT.getPropertyName()
                );
                case ADDRESS_SHELTER_BUTTON -> informationService.getPropertyByShelterAndName(
                        shelterId,
                        ShelterInformationProperty.ADDRESS.getPropertyName()
                );
                case PASS_SHELTER_BUTTON -> informationService.getPropertyByShelterAndName(
                        shelterId,
                        ShelterInformationProperty.PASS.getPropertyName()
                );
                case SAFETY_SHELTER_BUTTON -> informationService.getPropertyByShelterAndName(
                        shelterId,
                        ShelterInformationProperty.SAFETY.getPropertyName()
                );
                case SCHEDULE_SHELTER_BUTTON -> informationService.getPropertyByShelterAndName(
                        shelterId,
                        ShelterInformationProperty.SCHEDULE.getPropertyName()
                );
                default -> new ShelterInformation("Неизвестная команда получения информации о приюте");
            };
        } catch (ShelterInformationNotFoundException e) {
            information = new ShelterInformation("Такая информация на текущий момент не занесена");
        } catch (ShelterInformationPropertyErrorNameException e) {
            information = new ShelterInformation("Информация такого типа мне не известна");
        }

        return information;
    }

    private void sendRoadmapInformation(Long shelterId, Long chatId, Integer messageId, InlineKeyboardMarkup markup) {
        try {
            byte[] roadmap = travelDirectionsService.downloadTravelDirectionsDataFromDb(shelterId);
            telegramBotSender.deleteMessage(chatId, messageId);
            telegramBotSender.sendPhoto(chatId, roadmap);
            telegramBotSender.send(chatId, MESSAGE_INFORMATION_ABOUT_SHELTER, markup);
        } catch (TravelDirectionsNotFoundException e) {
            telegramBotSender.editMessageText(
                    chatId,
                    messageId,
                    "Информация о схеме проезда для приюта не указана",
                    markup
            );
        }
    }

    private void returnToMainMenu(Long chatId, Integer messageId) {
        InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
        telegramBotSender.editMessageText(chatId, messageId, MESSAGE_RETURN, markupStart);
    }

    private void showMainMenu(Long chatId) {
        InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
        telegramBotSender.send(chatId, MESSAGE_RETURN, markupStart);
    }

    private void getUserContactsInformation(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();
        final Integer messageId = callbackQuery.maybeInaccessibleMessage().messageId();

        telegramBotSender.deleteMessage(chatId, messageId);
        telegramBotSender.send(chatId, MESSAGE_CONTACT_INFORMATION_HELP);

        userCurrentMessageStatus.put(chatId, UserMessageStatus.CONTACT_INFORMATION_INPUT);
    }

    private void processUserUpdate(Update update) {
        final Message message = update.message();
        final Long chatId = message.chat().id();

        if (!userCurrentMessageStatus.containsKey(chatId)) {
            telegramBotSender.send(chatId, MESSAGE_SORRY);
            return;
        }

        UserMessageStatus userMessageStatus = userCurrentMessageStatus.get(chatId);

        switch (userMessageStatus) {
            case SEND_REPORT:
            case REPORT_PHOTO_INPUT:
            case REPORT_RATION_INPUT:
            case REPORT_WELL_BEING_INPUT:
            case REPORT_BEHAVIOR_INPUT:
                processAdoptionReportUpdate(update, userMessageStatus);
                break;
            case CONTACT_INFORMATION_INPUT:
                parseUserContactsInformation(chatId, message.text());
        }
    }

    private void parseUserContactsInformation(Long chatId, String messageText) {
        Pattern notificationPattern = Pattern.compile("(\\+7-9\\d{2}-\\d{3}-\\d{2}-\\d{2})(\\s+)(\\S+)");
        Matcher matcher = notificationPattern.matcher(messageText);

        if (!matcher.matches()) {
            telegramBotSender.send(chatId, "Сообщение не соответствует шаблону.");
            telegramBotSender.send(chatId, MESSAGE_CONTACT_INFORMATION_HELP);
            return;
        }

        final String phoneNumber = matcher.group(1);
        final String contactName = matcher.group(3);

        // TODO: Решить как сохранять информацию о телефоне и имени для связи
        if (userService.existsById(chatId)) {
            Users user = userService.findById(chatId);
        } else {
            Users user = new Users();
        }

        telegramBotSender.send(chatId, "Информация для связи сохранена.");

        userCurrentMessageStatus.remove(chatId);

        showMainMenu(chatId);
    }

    private void processAdoptionReportUpdate(Update update, UserMessageStatus status) {
        final Message message = update.message();
        final String messageText = message.text();
        final Long chatId = message.chat().id();

        switch (status) {
            case REPORT_PHOTO_INPUT:
                if (message.photo() == null) {
                    telegramBotSender.send(chatId, MESSAGE_SORRY);
                }
                List<PhotoSize> photos = List.of(message.photo());
                if (message.photo() != null) {
                    PhotoSize photo = photos.stream()
                            .sorted(Comparator.comparing(PhotoSize::fileSize).reversed())
                            .findFirst()
                            .orElse(null);
                    byte[] photoData = telegramBotSender.getPhotoData(photo.fileId());
                    reportAnimalPhotoService.saveAnimalPhoto(
                            userCurrentAdoptionReport.get(chatId).getPk(),
                            photoData,
                            photo.fileSize()
                    );
                }
                break;
            case REPORT_RATION_INPUT:
                if (messageText != null) {
                    userCurrentAdoptionReport.get(chatId).setRation(messageText);
                    reportService.updateAdoptionReport(userCurrentAdoptionReport.get(chatId));
                }
            case REPORT_WELL_BEING_INPUT:
                if (messageText != null) {
                    userCurrentAdoptionReport.get(chatId).setWellBeing(messageText);
                    reportService.updateAdoptionReport(userCurrentAdoptionReport.get(chatId));
                }
            case REPORT_BEHAVIOR_INPUT:
                if (messageText != null) {
                    userCurrentAdoptionReport.get(chatId).setBehaviourChange(messageText);
                    reportService.updateAdoptionReport(userCurrentAdoptionReport.get(chatId));
                }
        }

        sendNextAdoptionReportMessage(chatId);
    }

    private void updateAdoptionReportCommand(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();
        final Integer messageId = callbackQuery.maybeInaccessibleMessage().messageId();
        final LocalDate currentDate = LocalDate.from(LocalDateTime.now());

        // TODO: Нужно сегодня отправлять отчет пользователю?

        telegramBotSender.deleteMessage(chatId, messageId);
        telegramBotSender.send(chatId, MESSAGE_SEND_REPORT_HELP);

        if (!userCurrentAdoptionReport.containsKey(chatId)) {
            Adoption adoption = new Adoption();
            adoption.setId(1L);

            AdoptionReport report = new AdoptionReport(new AdoptionReportPK(adoption, currentDate));

            userCurrentAdoptionReport.put(chatId, report);
        }

        userCurrentMessageStatus.put(chatId, UserMessageStatus.SEND_REPORT);

        sendNextAdoptionReportMessage(chatId);
    }

    private void sendNextAdoptionReportMessage(Long chatId) {
        if (!userCurrentMessageStatus.containsKey(chatId)) {
            return;
        }

        UserMessageStatus status = userCurrentMessageStatus.get(chatId);

        switch (status) {
            case SEND_REPORT:
                telegramBotSender.send(chatId, MESSAGE_REPORT_PHOTO_INPUT);
                userCurrentMessageStatus.put(chatId, UserMessageStatus.REPORT_PHOTO_INPUT);
                break;
            case REPORT_PHOTO_INPUT:
                telegramBotSender.send(chatId, MESSAGE_REPORT_RATION_INPUT);
                userCurrentMessageStatus.put(chatId, UserMessageStatus.REPORT_RATION_INPUT);
                break;
            case REPORT_RATION_INPUT:
                telegramBotSender.send(chatId, MESSAGE_WELL_BEING_INPUT);
                userCurrentMessageStatus.put(chatId, UserMessageStatus.REPORT_WELL_BEING_INPUT);
                break;
            case REPORT_WELL_BEING_INPUT:
                telegramBotSender.send(chatId, MESSAGE_BEHAVIOR_INPUT);
                userCurrentMessageStatus.put(chatId, UserMessageStatus.REPORT_BEHAVIOR_INPUT);
                break;
            case REPORT_BEHAVIOR_INPUT:
                telegramBotSender.send(chatId, MESSAGE_REPORT_DONE);
                userCurrentMessageStatus.remove(chatId);
                showMainMenu(chatId);
                break;
        }
    }
}