package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;
import pro.dev.animalshelter.model.Shelter;
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

    public void sendShelterMessage(Long chatId, String shelterId, String message, InlineKeyboardMarkup markup) {
        Shelter shelter = shelterRepository.findById(Long.parseLong(shelterId)).orElseThrow();
        SendMessage sendMessage = new SendMessage(chatId, "Добро пожаловать в приют " + shelter.getName() + "! " + message).replyMarkup(markup);
        sendMessage(message, sendMessage);
    }

    private void sendMessage(String message, SendMessage sendMessage) {
        SendResponse response = telegramBot.execute(sendMessage);
        if (response.isOk()) {
            logger.info("Успешно отправлено сообщение: {}", message);
        } else {
            logger.error("Ошибка в отправке сообщения: {}", response.errorCode());
        }
    }
}
