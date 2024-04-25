package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.RecommendationType;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.exception.RecommendationInformationNotFoundException;
import pro.dev.animalshelter.exception.RecommendationTypeErrorNameException;
import pro.dev.animalshelter.interfaces.RecommendationInformationInterface;
import pro.dev.animalshelter.interfaces.ShelterService;
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
    private final ShelterInformationService informationService;
    private final InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator;
    private final RecommendationInformationInterface recommendationService;
    private final Map<Long, Long> userCurrentShelterId = new HashMap<>();

    public UpdateService(
            TelegramBotSender telegramBotSender,
            InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator,
            UserService userService, ShelterService shelterService,
            ShelterInformationService informationService,
            RecommendationInformationInterface recommendationService
    ) {
        this.telegramBotSender = telegramBotSender;
        this.inlineKeyboardMarkupCreator = inlineKeyboardMarkupCreator;
        this.userService = userService;
        this.shelterService = shelterService;
        this.informationService = informationService;
        this.recommendationService = recommendationService;
    }

    public void processUpdate(Update update) {
        Message message = update.message();

        if (message != null && message.text() != null && message.text().equals("/start")) {

            Long chatId = update.message().chat().id();
            if (!userService.existsById(chatId)) {
                userService.addUser(chatId, update.message().chat().firstName(), null);
                InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
                telegramBotSender.send(chatId, MESSAGE_START, markupStart);
            } else {
                InlineKeyboardMarkup markupChooseShelters = inlineKeyboardMarkupCreator.createKeyboardChooseShelters();
                telegramBotSender.send(chatId, MESSAGE_CHOOSE_SHELTERS, markupChooseShelters);
            }

        } else if (update.callbackQuery() != null) {

            chooseCommand(update);

        }
    }

    private void chooseCommand(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();

        switch (data) {
            case INFORMATION_ABOUT_SHELTERS_BUTTON:
                InlineKeyboardMarkup markupChooseShelters = inlineKeyboardMarkupCreator.createKeyboardChooseShelters();
                telegramBotSender.send(chatId, MESSAGE_CHOOSE_SHELTERS, markupChooseShelters);
                break;

            case INFORMATION_ABOUT_PETS_BUTTON:
                InlineKeyboardMarkup markupInformationAboutPets = inlineKeyboardMarkupCreator.createKeyboardInformationAboutPets();
                telegramBotSender.send(chatId, MESSAGE_INFORMATION_ABOUT_PETS, markupInformationAboutPets);
                break;

            case SEND_REPORT_BUTTON:
                telegramBotSender.send(chatId, MESSAGE_SEND_REPORT);
                break;

            case VOLUNTEER_BUTTON:
                telegramBotSender.send(chatId, MESSAGE_VOLUNTEER);
                break;

            case ADDRESS_BUTTON:
                ShelterInformation address = informationService.getPropertyByShelterAndName(
                        userCurrentShelterId.get(chatId),
                        ShelterInformationProperty.ADDRESS.getPropertyName()
                );
                telegramBotSender.send(chatId, address.getDescription());
                break;

            case PASS_BUTTON:
                ShelterInformation pass = informationService.getPropertyByShelterAndName(
                        userCurrentShelterId.get(chatId),
                        ShelterInformationProperty.PASS.getPropertyName()
                );
                telegramBotSender.send(chatId, pass.getDescription());
                break;

            case SAFETY_BUTTON:
                ShelterInformation safety = informationService.getPropertyByShelterAndName(
                        userCurrentShelterId.get(chatId),
                        ShelterInformationProperty.SAFETY.getPropertyName()
                );
                telegramBotSender.send(chatId, safety.getDescription());
                break;

            case CHOOSE_PET_BUTTON:
                telegramBotSender.send(chatId, "Здесь будет список животных");
                break;

            case RECOMMENDATIONS_BUTTON:
                InlineKeyboardMarkup markupRecommendations = inlineKeyboardMarkupCreator.createKeyboardRecommendation();
                telegramBotSender.send(chatId, MESSAGE_RECOMMENDATIONS, markupRecommendations);
                break;

            case RULES_BUTTON:
            case TRANSPORTATION_BUTTON:
            case HOME_BUTTON:
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

            default:
                sendShelterMessage(chatId, data);
                break;
        }
    }

    private void sendShelterMessage(Long chatId, String data) {
        long shelterId = Long.parseLong(data);
        userCurrentShelterId.put(chatId, shelterId);
        Shelter shelter = shelterService.getShelter(shelterId);
        InlineKeyboardMarkup markupInformationAboutShelter = inlineKeyboardMarkupCreator.createKeyboardInformationAboutShelter();
        String message = "Добро пожаловать в приют " + shelter.getName() + "! " + MESSAGE_INFORMATION_ABOUT_SHELTER;
        telegramBotSender.send(chatId, message, markupInformationAboutShelter);
    }

    private void updateRecommendationInformationCommands(Update update) {
        final CallbackQuery callbackQuery = update.callbackQuery();
        String data = callbackQuery.data();
        final Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();
        final Integer messageId = callbackQuery.maybeInaccessibleMessage().messageId();

        String information = getRecommendationInformationByCommand(data);

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
                case HOME_BUTTON -> recommendationService.getInformationBy(
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
}
