package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;
import pro.dev.animalshelter.repository.ShelterRepository;

@Service
public class TelegramBotSender {
    private final TelegramBot telegramBot;
    private final ShelterRepository shelterRepository;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public TelegramBotSender(TelegramBot telegramBot, ShelterRepository shelterRepository) {
        this.telegramBot = telegramBot;
        this.shelterRepository = shelterRepository;
    }

    public void send(Long chatId, String message, InlineKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage(chatId, message).replyMarkup(markup);
        sendMessage(message, sendMessage);
    }

    public void send(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage(message, sendMessage);
    }

    public void editMessageText(Long chatId, Integer messageId, String message, InlineKeyboardMarkup markup) {
        EditMessageText editMessage = new EditMessageText(chatId, messageId, message)
                .replyMarkup(markup);
        sendEditedMessage(message, editMessage);
    }

    public void sendPhoto(Long chatId, Integer messageId, byte[] photo, InlineKeyboardMarkup markup) {
        telegramBot.execute(new DeleteMessage(chatId, messageId));
        SendPhoto photoMessage = new SendPhoto(chatId, photo)
                .replyMarkup(markup);
        sendPhotoMessage(chatId, photoMessage);
    }

    private void sendMessage(String message, SendMessage sendMessage) {
        SendResponse response = telegramBot.execute(sendMessage);
        if (response.isOk()) {
            logger.info("Успешно отправлено сообщение: {}", message);
        } else {
            logger.error("Ошибка в отправке сообщения: {}", response.errorCode());
        }
    }

    private void sendEditedMessage(String message, EditMessageText editMessage) {
        SendResponse response = (SendResponse) telegramBot.execute(editMessage);
        if (response.isOk()) {
            logger.info("Успешно отправлено сообщение: {}", message);
        } else {
            logger.error("Ошибка в отправке сообщения: {}", response.description());
        }
    }

    private void sendPhotoMessage(Long chatId, SendPhoto photoMessage) {
        SendResponse response = (SendResponse) telegramBot.execute(photoMessage);
        if (response.isOk()) {
            logger.info("Успешно отправлено сообщение: {}", photoMessage);
        } else {
            logger.error("Ошибка в отправке сообщения: {}", response.description());
        }
    }
}
