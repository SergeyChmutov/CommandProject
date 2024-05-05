package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetFileResponse;
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

    public void sendPhoto(Long chatId, byte[] photo) {
        SendPhoto photoMessage = new SendPhoto(chatId, photo);
        sendPhotoMessage(chatId, photoMessage);
    }

    public void deleteMessage(Long chatId, Integer messageId) {
        BaseResponse response = telegramBot.execute(new DeleteMessage(chatId, messageId));
        if (response.isOk()) {
            logger.info("Успешно удалено сообщение {} из чата {}", messageId, chatId);
        } else {
            logger.error("Ошибка при удалении сообщения {} из чата {}: {}", messageId, chatId, response.description());
        }
    }

    public File getFileBy(String fileId) {
        GetFile request = new GetFile(fileId);
        GetFileResponse getFileResponse = telegramBot.execute(request);

        return getFileResponse.file();
    }

    private void sendMessage(String message, SendMessage sendMessage) {
        SendResponse response = telegramBot.execute(sendMessage);
        loggingTelegramBotSendMessage(message, response);
    }

    private void sendEditedMessage(String message, EditMessageText editMessage) {
        SendResponse response = (SendResponse) telegramBot.execute(editMessage);
        loggingTelegramBotSendMessage(message, response);
    }

    private void sendPhotoMessage(Long chatId, SendPhoto photoMessage) {
        SendResponse response = (SendResponse) telegramBot.execute(photoMessage);
        loggingTelegramBotSendMessage("Фото", response);
    }

    private void loggingTelegramBotSendMessage(String message, BaseResponse response) {
        if (response.isOk()) {
            logger.info("Успешно отправлено сообщение: {}", message);
        } else {
            logger.error("Ошибка в отправке сообщения: {}", response.errorCode());
        }
    }
}
