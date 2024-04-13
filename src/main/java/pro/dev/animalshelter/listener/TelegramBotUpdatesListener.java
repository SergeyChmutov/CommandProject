package pro.dev.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.service.InlineKeyboardMarkupCreator;
import pro.dev.animalshelter.service.TelegramBotSender;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;

    private final TelegramBotSender telegramBotSender;

    private final InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator;
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final String START = "Привет! Рад вас здесь видеть!\n" +
            "Я бот, который поможет вам взаимодействовать с приютами для собачек!\n" +
            "Чем я могу помочь? Выберите нужный пункт меню:";

    private final String CHOOSE_SHELTERS = "Информацию о каком приюте вы бы хотели получить?";

    private final String INFORMATION_ABOUT_SHELTERS = "Здесь вы можете получить следующую информацию:";

    private final String INFORMATION_ABOUT_PETS = "Здесь я помогу вам разобраться с бюрократическими и бытовыми вопросами. Что вас интересует?";

    private final String RECOMENDATIONS = "Здесь вы можете получить следующие рекомендации:";

    private final String HOME_RECOMENDATIONS = "Здесь вы можете получить рекомендации по обустройству дома в зависимости от особенностей собаки:";
    private final String SEND_REPORT = "Сюда можно будет прислать отчет о питомце";

    private final String VOLONTEER = "Здесь можно будет позвать волонтера";


    public TelegramBotUpdatesListener(TelegramBot telegramBot, TelegramBotSender telegramBotSender, InlineKeyboardMarkupCreator inlineKeyboardMarkupCreator) {
        this.telegramBot = telegramBot;
        this.telegramBotSender = telegramBotSender;
        this.inlineKeyboardMarkupCreator = inlineKeyboardMarkupCreator;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            if (message != null && message.text() != null && message.text().equals("/start")) {

                Long chatId = update.message().chat().id();
                InlineKeyboardMarkup markupStart = inlineKeyboardMarkupCreator.createKeyboardStart();
                telegramBotSender.send(chatId, START, markupStart);

            } else if (update.callbackQuery() != null) {
                CallbackQuery callbackQuery = update.callbackQuery();
                String data = callbackQuery.data();
                Long chatId = callbackQuery.message().chat().id();

                chooseCommand(data, chatId);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;

    }

    public void chooseCommand(String data, Long chatId) {
        switch (data) {
            case "INFORMATION_ABOUT_SHELTERS_BUTTON":
                InlineKeyboardMarkup markupChoosSheletrs = inlineKeyboardMarkupCreator.createKeyboardChooseShelters();
                telegramBotSender.send(chatId, CHOOSE_SHELTERS, markupChoosSheletrs);
                break;

            case "INFORMATION_ABOUT_PETS_BUTTON":
                InlineKeyboardMarkup markupInformationAboutPets = inlineKeyboardMarkupCreator.createKeyboardInformationAboutPets();
                telegramBotSender.send(chatId, INFORMATION_ABOUT_PETS, markupInformationAboutPets);
                break;

            case "SEND_REPORT_BUTTON":
                telegramBotSender.send(chatId, SEND_REPORT);
                break;

            case "VOLONTEER_BUTTON":
                telegramBotSender.send(chatId, VOLONTEER);
                break;

            case "WET_NOSE_BUTTON":
                InlineKeyboardMarkup markupInformationAboutWetNose = inlineKeyboardMarkupCreator.createKeyboardInformationAboutWetNose();
                telegramBotSender.send(chatId, "Добро пожаловать в приют Мокрый нос! " + INFORMATION_ABOUT_SHELTERS, markupInformationAboutWetNose);
                break;

            case "PUG_BUTTON":
                InlineKeyboardMarkup markupInformationAboutPug = inlineKeyboardMarkupCreator.createKeyboardInformationAboutPug();
                telegramBotSender.send(chatId, "Добро пожаловать в приют Мопс! " + INFORMATION_ABOUT_SHELTERS, markupInformationAboutPug);
                break;

            case "WET_NOSE_ADRESS_BUTTON":
                telegramBotSender.send(chatId, "Здесь будет расписание работы, адрес и схема проезда");
                break;

            case "PUG_ADRESS_BUTTON":
                telegramBotSender.send(chatId, "Здесь будет расписание работы, адрес и схема проезда");
                break;

            case "WET_NOSE_PASS_BUTTON":
                telegramBotSender.send(chatId, "Здесь можно будет получить контактные данные охраны для оформления пропуска на машину");
                break;

            case "PUG_PASS_BUTTON":
                telegramBotSender.send(chatId, "Здесь можно будет получить контактные данные охраны для оформления пропуска на машину");
                break;

            case "WET_NOSE_SAFETY_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут общие рекомендации по технике безопасности на территории приюта");
                break;

            case "PUG_SAFETY_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут общие рекомендации по технике безопасности на территории приюта");
                break;

            case "CHOOSE_PET_BUTTON":
                telegramBotSender.send(chatId, "Здесь будет список животных");
                break;

            case "RULES_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут правила знакомства с животным до того, как забрать его из приюта");
                break;

            case "RECOMMENDATIONS_BUTTON":
                InlineKeyboardMarkup markupRecommendations = inlineKeyboardMarkupCreator.createKeyboardRecommendation();
                telegramBotSender.send(chatId, RECOMENDATIONS, markupRecommendations);
                break;

            case "TRANSPORTATION_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут рекомендации по транспортировке животного");
                break;

            case "HOME_BUTTON":
                InlineKeyboardMarkup markupHomeRecommendations = inlineKeyboardMarkupCreator.createKeyboardHomeRecomendation();
                telegramBotSender.send(chatId, HOME_RECOMENDATIONS, markupHomeRecommendations);
                break;

            case "PUPPY_HOME_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут рекомендации по обустройству дома для щенка");
                break;

            case "DOG_HOME_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут рекомендации по обустройству дома для взрослого животного");
                break;

            case "ANIMALS_WITH_DISABILITIES_HOME_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут рекомендации по обустройству дома для животного с ограниченными возможностями (зрение, передвижение)");
                break;

            case "DOG_HANDLER_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут рекомендации по проверенным кинологам для дальнейшего обращения к ним");
                break;

            case "ADVICES_DOG_HANDLER_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут советы кинолога по первичному общению с собакой");
                break;

            case "REASONS_FOR_REFUSAL_BUTTON":
                telegramBotSender.send(chatId, "Здесь будут список причин, почему могут отказать и не дать забрать собаку из приюта");
                break;

            case "CONTACTS_BUTTON":
                telegramBotSender.send(chatId, "Здесь можно будет оставить контактные данные для связи");
                break;

        }
    }
}
