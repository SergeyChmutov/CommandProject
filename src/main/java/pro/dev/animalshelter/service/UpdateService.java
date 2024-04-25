package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.enums.ShelterInformationProperty;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.ShelterInformation;
import pro.dev.animalshelter.model.Users;

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
    private final Map<Long, Long> userCurrentShelterId = new HashMap<>();

    public UpdateService(
            TelegramBotSender telegramBotSender,
            InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator,
            UserService userService, ShelterService shelterService,
            ShelterInformationService informationService
    ) {
        this.telegramBotSender = telegramBotSender;
        this.inlineKeyboardMarkupCreator = inlineKeyboardMarkupCreator;
        this.userService = userService;
        this.shelterService = shelterService;
        this.informationService = informationService;
    }

    public void processUpdate(Update update) {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
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

        } else if (callbackQuery != null) {
            String data = callbackQuery.data();
            Long chatId = callbackQuery.maybeInaccessibleMessage().chat().id();

            chooseCommand(data, chatId);
        }
    }

    private void chooseCommand(String data, Long chatId) {
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
                telegramBotSender.send(
                        chatId,
                        "Здесь будут правила знакомства с животным до того, как забрать его из приюта"
                );
                break;

            case TRANSPORTATION_BUTTON:
                telegramBotSender.send(chatId, "Здесь будут рекомендации по транспортировке животного");
                break;

            case HOME_BUTTON:
                InlineKeyboardMarkup markupHomeRecommendations = inlineKeyboardMarkupCreator.createKeyboardHomeRecommendation();
                telegramBotSender.send(chatId, MESSAGE_HOME_RECOMMENDATIONS, markupHomeRecommendations);
                break;

            case PUPPY_HOME_BUTTON:
                telegramBotSender.send(chatId, "Здесь будут рекомендации по обустройству дома для щенка");
                break;

            case DOG_HOME_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь будут рекомендации по обустройству дома для взрослого животного"
                );
                break;

            case ANIMALS_WITH_DISABILITIES_HOME_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь будут рекомендации по обустройству дома для животного с ограниченными возможностями (зрение, передвижение)"
                );
                break;

            case DOG_HANDLER_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь будут рекомендации по проверенным кинологам для дальнейшего обращения к ним"
                );
                break;

            case ADVICES_DOG_HANDLER_BUTTON:
                telegramBotSender.send(chatId, "Здесь будут советы кинолога по первичному общению с собакой");
                break;

            case REASONS_FOR_REFUSAL_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь будут список причин, почему могут отказать и не дать забрать собаку из приюта"
                );
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
}
