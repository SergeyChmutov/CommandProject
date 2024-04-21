package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;
import pro.dev.animalshelter.model.Shelter;

import java.util.ArrayList;
import java.util.List;

import static pro.dev.animalshelter.constant.Constants.*;

@Component
public class InlineKeyboardMarkupCreator {
    private final ShelterService shelterService;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public InlineKeyboardMarkupCreator(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    public InlineKeyboardMarkup createKeyboardStart() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(
                new InlineKeyboardButton("Получить информацию о приюте")
                        .callbackData(INFORMATION_ABOUT_SHELTERS_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(
                new InlineKeyboardButton("Взять животное из приюта")
                        .callbackData(INFORMATION_ABOUT_PETS_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(
                new InlineKeyboardButton("Прислать отчет о питомце")
                        .callbackData(SEND_REPORT_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER_BUTTON));

        List<List<InlineKeyboardButton>> keyboardStart = new ArrayList<>();
        keyboardStart.add(buttonsRow1);
        keyboardStart.add(buttonsRow2);
        keyboardStart.add(buttonsRow3);
        keyboardStart.add(buttonsRow4);

        InlineKeyboardMarkup markupStart = getInlineKeyboardMarkup(keyboardStart);
        return markupStart;
    }

    public InlineKeyboardMarkup createKeyboardChooseShelters() {
        List<List<InlineKeyboardButton>> keyboardShelters = new ArrayList<>();
        for (Shelter shelter :
                shelterService.getShelters()) {
            List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(shelter.getName());
            button.callbackData(Long.toString(shelter.getId()));

            buttonsRow.add(button);
            keyboardShelters.add(buttonsRow);
        }
        InlineKeyboardMarkup markupShelters = getInlineKeyboardMarkup(keyboardShelters);
        return markupShelters;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutShelter() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Расписание работы, адрес и схема проезда")
                .callbackData(ADDRESS_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Оформить пропуск на машину").callbackData(PASS_BUTTON));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(
                new InlineKeyboardButton("Техника безопасности на территории приюта")
                        .callbackData(SAFETY_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(
                new InlineKeyboardButton("Оставить контактные данные для связи")
                        .callbackData(CONTACTS_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow5 = new ArrayList<>();
        buttonsRow5.add(new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER_BUTTON));

        List<List<InlineKeyboardButton>> keyboardWetNose = new ArrayList<>();
        keyboardWetNose.add(buttonsRow1);
        keyboardWetNose.add(buttonsRow2);
        keyboardWetNose.add(buttonsRow3);
        keyboardWetNose.add(buttonsRow4);
        keyboardWetNose.add(buttonsRow5);

        InlineKeyboardMarkup markupShelter = getInlineKeyboardMarkup(keyboardWetNose);
        return markupShelter;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutPets() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(
                new InlineKeyboardButton("Список животных для усыновления")
                        .callbackData(CHOOSE_PET_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Правила знакомства с животным").callbackData(RULES_BUTTON));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("Рекомендации").callbackData(RECOMMENDATIONS_BUTTON));

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(new InlineKeyboardButton("Советы кинолога").callbackData(ADVICES_DOG_HANDLER_BUTTON));

        List<InlineKeyboardButton> buttonsRow5 = new ArrayList<>();
        buttonsRow5.add(new InlineKeyboardButton("Причины отказа").callbackData(REASONS_FOR_REFUSAL_BUTTON));

        List<InlineKeyboardButton> buttonsRow6 = new ArrayList<>();
        buttonsRow6.add(new InlineKeyboardButton("Оставить контакты для связи").callbackData(CONTACTS_BUTTON));

        List<InlineKeyboardButton> buttonsRow7 = new ArrayList<>();
        buttonsRow7.add(new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER_BUTTON));

        List<List<InlineKeyboardButton>> keyboardInformationAboutPets = new ArrayList<>();
        keyboardInformationAboutPets.add(buttonsRow1);
        keyboardInformationAboutPets.add(buttonsRow2);
        keyboardInformationAboutPets.add(buttonsRow3);
        keyboardInformationAboutPets.add(buttonsRow4);
        keyboardInformationAboutPets.add(buttonsRow5);
        keyboardInformationAboutPets.add(buttonsRow6);
        keyboardInformationAboutPets.add(buttonsRow7);

        InlineKeyboardMarkup markupInformationAboutPets = getInlineKeyboardMarkup(keyboardInformationAboutPets);
        return markupInformationAboutPets;
    }

    public InlineKeyboardMarkup createKeyboardRecommendation() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(
                new InlineKeyboardButton("По транспортировке животного")
                        .callbackData(TRANSPORTATION_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("По обустройству дома").callbackData(HOME_BUTTON));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("По проверенным кинологам").callbackData(DOG_HANDLER_BUTTON));

        List<List<InlineKeyboardButton>> keyboardRecommendation = new ArrayList<>();
        keyboardRecommendation.add(buttonsRow1);
        keyboardRecommendation.add(buttonsRow2);
        keyboardRecommendation.add(buttonsRow3);

        InlineKeyboardMarkup markupRecommendation = getInlineKeyboardMarkup(keyboardRecommendation);
        return markupRecommendation;
    }

    public InlineKeyboardMarkup createKeyboardHomeRecommendation() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Для щенка").callbackData(PUPPY_HOME_BUTTON));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Для взрослого животного").callbackData(DOG_HOME_BUTTON));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(
                new InlineKeyboardButton("Для животного с ограниченными возможностями")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)
        );

        List<List<InlineKeyboardButton>> keyboardHomeRecommendation = new ArrayList<>();
        keyboardHomeRecommendation.add(buttonsRow1);
        keyboardHomeRecommendation.add(buttonsRow2);
        keyboardHomeRecommendation.add(buttonsRow3);

        InlineKeyboardMarkup markupHomeRecommendation = getInlineKeyboardMarkup(keyboardHomeRecommendation);
        return markupHomeRecommendation;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup(List<List<InlineKeyboardButton>> keyboardStart) {
        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardStart.size()][];

        for (int i = 0; i < keyboardStart.size(); i++) {
            List<InlineKeyboardButton> row = keyboardStart.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }

        return new InlineKeyboardMarkup(buttonsArray);
    }
}
