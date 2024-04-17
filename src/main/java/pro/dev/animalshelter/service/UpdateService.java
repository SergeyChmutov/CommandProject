package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;

import static pro.dev.animalshelter.constant.Constants.*;

@Service
public class UpdateService {
    private final TelegramBotSender telegramBotSender;
    private final InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator;

    public UpdateService(TelegramBotSender telegramBotSender, InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator) {
        this.telegramBotSender = telegramBotSender;
        this.inlineKeyboardMarkupCreator = inlineKeyboardMarkupCreator;
    }

    public void processUpdate(Update update) {
        Message message = update.message();
        CallbackQuery callbackQuery = update.callbackQuery();
        if (message != null && message.text() != null && message.text().equals("/start")) {

            Long chatId = update.message().chat().id();
            InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
            telegramBotSender.send(chatId, MESSAGE_START, markupStart);

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

            case WET_NOSE_BUTTON:
                InlineKeyboardMarkup markupInformationAboutWetNose = inlineKeyboardMarkupCreator.createKeyboardInformationAboutShelter();
                telegramBotSender.send(
                        chatId,
                        "Добро пожаловать в приют Мокрый нос! " + MESSAGE_INFORMATION_ABOUT_SHELTER,
                        markupInformationAboutWetNose
                );
                break;

            case PUG_BUTTON:
                InlineKeyboardMarkup markupInformationAboutPug = inlineKeyboardMarkupCreator.createKeyboardInformationAboutShelter();
                telegramBotSender.send(
                        chatId,
                        "Добро пожаловать в приют Мопс! " + MESSAGE_INFORMATION_ABOUT_SHELTER,
                        markupInformationAboutPug
                );
                break;

            case ADDRESS_BUTTON:
                telegramBotSender.send(chatId, "Здесь будет расписание работы, адрес и схема проезда");
                break;

            case PASS_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь можно будет получить контактные данные охраны для оформления пропуска на машину"
                );
                break;

            case SAFETY_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь будут общие рекомендации по технике безопасности на территории приюта"
                );
                break;

            case CHOOSE_PET_BUTTON:
                telegramBotSender.send(chatId, "Здесь будет список животных");
                break;

            case RULES_BUTTON:
                telegramBotSender.send(
                        chatId,
                        "Здесь будут правила знакомства с животным до того, как забрать его из приюта"
                );
                break;

            case RECOMMENDATIONS_BUTTON:
                InlineKeyboardMarkup markupRecommendations = inlineKeyboardMarkupCreator.createKeyboardRecommendation();
                telegramBotSender.send(chatId, MESSAGE_RECOMMENDATIONS, markupRecommendations);
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
                break;
        }
    }
}
