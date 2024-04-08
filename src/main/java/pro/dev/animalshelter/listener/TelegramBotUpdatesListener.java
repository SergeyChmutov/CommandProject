package pro.dev.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.service.TelegramBotSender;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;

    private final TelegramBotSender telegramBotSender;

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final String START = "Привет! Рады вас здесь видеть!\n" +
            "Я бот, который поможет вам взаимодействовать с приютами для собачек!\n" +
            "Чем я могу помочь? Выберите нужный пункт меню:\n\n" +
            "/shelters - получить информацию о приюте\n\n" +
            "/pets - взять животное из приюта\n\n" +
            "/report - прислать отчет о питомце\n\n" +
            "/volunteer - позвать волонтера";

    private final String CHOOSE_SHELTERS = "Информацию о каком приюте вы бы хотели получить?\n\n" +
            "/wet_nose - о приюте Мокрый нос\n\n" +
            "/pug - о приюте Мопс";

    private final String INFORMATION_ABOUT_SHELTERS = "Здесь вы можете получить следующую информацию:\n\n" +
            "/adress - расписание работы приюта, адрес и схему проезда\n\n" +
            "/pass - как оформить пропуск на машину\n\n" +
            "/safety - рекомендации о технике безопасности на территории приюта\n\n" +
            "/contacts - принять и записать контактные данные для связи\n\n" +
            "/volunteer - позвать волонтера";

    private final String INFORMATION_ABOUT_PETS = "Здесь я помогаю потенциальным усыновителям животного из приюта разобраться с бюрократическими и бытовыми вопросами. Что вас интересует?\n\n" +
            "/choose_pet - получить список животных для усыновления\n\n" +
            "/rules - ознакомиться с правилами знакомства с животным до того, как забрать его из приюта\n\n" +
            "/documents - получить список документов, необходимых для того, чтобы взять животное из приюта\n\n" +
            "/recommendations - получить рекомендации\n\n" +
            "/advice_dog_handler - получить советы кинолога по первичному общению с собакой\n\n" +
            "/reasons_for_refusal - получить список причин, почему могут отказать и не дать забрать собаку из приюта\n\n" +
            "/contacts - принять и записать контактные данные для связи\n\n" +
            "/volunteer - позвать волонтера";

    private final String RECOMENDATIONS = "Здесь вы можете получить следующие рекомендации:\n\n" +
            "/transportation - транспортировке животного\n\n" +
            "/home - по обустройству дома\n\n" +
            "/dog_handler - получить рекомендации по проверенным кинологам для дальнейшего обращения к ним";

    private final String HOME_RECOMENDATIONS = "Здесь вы можете получить рекомендации по обустройству дома в зависимости от особенностей собаки:\n\n" +
            "/puppy_home - для щенка\n\n" +
            "/dog_home - для взрослого животного\n\n" +
            "animal_with_disabilities_home - для животного с ограниченными возможностями (зрение, передвижение)";

    private final String SEND_REPORT = "Сюда можно будет прислать отчет о питомце";

    private final String VOLONTEER = "Оставьте свои контакты и наш волонтер свяжется с вами в ближайшее время";


    public TelegramBotUpdatesListener(TelegramBot telegramBot, TelegramBotSender telegramBotSender) {
        this.telegramBot = telegramBot;
        this.telegramBotSender = telegramBotSender;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            Long chatId = update.message().chat().id();
            String inputText = update.message().text();

            switch (inputText) {
                case "/start":
                    telegramBotSender.send(chatId, START);
                    break;

                case "/shelters":
                    telegramBotSender.send(chatId, CHOOSE_SHELTERS);
                    break;

                case "/wet_nose":
                    telegramBotSender.send(chatId, "Добро пожаловать в приют Мокрый нос! " + INFORMATION_ABOUT_SHELTERS);
                    break;

                case "/pug":
                    telegramBotSender.send(chatId, "Добро пожаловать в приют Мопс! " + INFORMATION_ABOUT_SHELTERS);
                    break;

                case "/pets":
                    telegramBotSender.send(chatId, INFORMATION_ABOUT_PETS);
                    break;

                case "/recomendation":
                    telegramBotSender.send(chatId, RECOMENDATIONS);
                    break;

                case "/home":
                    telegramBotSender.send(chatId, HOME_RECOMENDATIONS);
                    break;

                case "/report":
                    telegramBotSender.send(chatId, SEND_REPORT);
                    break;

                case "/volunteer":
                    telegramBotSender.send(chatId, VOLONTEER);
                    break;
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
