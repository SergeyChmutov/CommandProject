package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;

@Service
public class TelegramBotSender {
    private final TelegramBot telegramBot;

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public TelegramBotSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void send(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse response = telegramBot.execute(sendMessage);
        if (response.isOk()) {
            logger.info("Успешно отправлено сообщение: {}", message);
        } else {
            logger.error("Ошибка в отправке сообщения: {}", response.errorCode());
        }
    }
}
