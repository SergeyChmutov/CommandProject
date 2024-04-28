package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RecommendationType;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.exception.*;
import pro.dev.animalshelter.interfaces.RecommendationInformationInterface;
import pro.dev.animalshelter.interfaces.ShelterInformationInterface;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.interfaces.TravelDirectionsInterface;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.ShelterInformation;

import java.util.HashMap;
import java.util.Map;

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
    private final Map<Long, Long> userCurrentShelterId = new HashMap<>();

    public UpdateService(
            TelegramBotSender telegramBotSender,
            InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator,
            UserService userService, ShelterService shelterService,
            ShelterInformationService informationService,
            TravelDirectionsInterface travelDirectionsService,
            RecommendationInformationInterface recommendationService
    ) {
        this.telegramBotSender = telegramBotSender;
        this.inlineKeyboardMarkupCreator = inlineKeyboardMarkupCreator;
        this.userService = userService;
        this.shelterService = shelterService;
        this.informationService = informationService;
        this.travelDirectionsService = travelDirectionsService;
        this.recommendationService = recommendationService;
    }

    public void processUpdate(Update update) {
        Message message = update.message();

        if (message != null && message.text() != null && message.text().equals("/start")) {

            Long chatId = update.message().chat().id();
            if (!userService.existsById(chatId)) {
                userService.addUser(chatId, update.message().chat().firstName(), null);
                InlineKeyboardMarkup markupChooseShelters = inlineKeyboardMarkupCreator.createKeyboardChooseShelters();
                telegramBotSender.send(chatId, MESSAGE_START, markupChooseShelters);
            } else {
                InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
                telegramBotSender.send(chatId, MESSAGE_RETURN, markupStart);
            }

        } else if (update.callbackQuery() != null) {

            chooseCommand(update);

        }
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
                telegramBotSender.send(chatId, MESSAGE_SEND_REPORT);
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
                telegramBotSender.send(chatId, "Здесь можно будет оставить контактные данные для связи");
                break;

            case MAIN_MENU_BUTTON:
                showMainMenu(chatId, messageId);
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

        final Long shelterId = userCurrentShelterId.get(chatId);

        if (shelterId == null) {
            chooseShelters(chatId, messageId);
            return;
        }

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

    private void showMainMenu(Long chatId, Integer messageId) {
        InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
        telegramBotSender.editMessageText(chatId, messageId, MESSAGE_RETURN, markupStart);
    }
}
