package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
@Component
public class InlineKeyboardMarkupCreator {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);


    public InlineKeyboardMarkup createKeyboardStart() {

        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Получить информацию о приюте").callbackData("INFORMATION_ABOUT_SHELTERS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Взять животное из приюта").callbackData("INFORMATION_ABOUT_PETS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("Прислать отчет о питомце").callbackData("SEND_REPORT_BUTTON"));

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(new InlineKeyboardButton("Позвать волонтера").callbackData("VOLONTEER_BUTTON"));
=======
import static pro.dev.animalshelter.constant.Constants.*;

@Component
public class InlineKeyboardMarkupCreator {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

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
>>>>>>> f182312 (1)

        List<List<InlineKeyboardButton>> keyboardStart = new ArrayList<>();
        keyboardStart.add(buttonsRow1);
        keyboardStart.add(buttonsRow2);
        keyboardStart.add(buttonsRow3);
        keyboardStart.add(buttonsRow4);

<<<<<<< HEAD
        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardStart.size()][];
        for (int i = 0; i < keyboardStart.size(); i++) {
            List<InlineKeyboardButton> row = keyboardStart.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markupStart = new InlineKeyboardMarkup(buttonsArray);
=======
        InlineKeyboardMarkup markupStart = getInlineKeyboardMarkup(keyboardStart);
>>>>>>> f182312 (1)
        return markupStart;
    }

    public InlineKeyboardMarkup createKeyboardChooseShelters() {
<<<<<<< HEAD

        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton("Мокрый нос").callbackData("WET_NOSE_BUTTON"));
        buttonsRow.add(new InlineKeyboardButton("Мопс").callbackData("PUG_BUTTON"));
=======
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton("Мокрый нос").callbackData(WET_NOSE_BUTTON));
        buttonsRow.add(new InlineKeyboardButton("Мопс").callbackData(PUG_BUTTON));
>>>>>>> f182312 (1)

        List<List<InlineKeyboardButton>> keyboardShelters = new ArrayList<>();
        keyboardShelters.add(buttonsRow);

<<<<<<< HEAD
        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardShelters.size()][];
        for (int i = 0; i < keyboardShelters.size(); i++) {
            List<InlineKeyboardButton> row = keyboardShelters.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markupShelters = new InlineKeyboardMarkup(buttonsArray);
        return markupShelters;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutWetNose() {

        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Расписание работы, адрес и схема проезда").callbackData("WET_NOSE_ADRESS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Оформить пропуск на машину").callbackData("WET_NOSE_PASS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("Техника безопасности на территории приюта").callbackData("WET_NOSE_SAFETY_BUTTON"));

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(new InlineKeyboardButton("Оставить контактные данные для связи").callbackData("CONTACTS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow5 = new ArrayList<>();
        buttonsRow5.add(new InlineKeyboardButton("Позвать волонтера").callbackData("VOLONTEER_BUTTON"));
=======
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
>>>>>>> f182312 (1)

        List<List<InlineKeyboardButton>> keyboardWetNose = new ArrayList<>();
        keyboardWetNose.add(buttonsRow1);
        keyboardWetNose.add(buttonsRow2);
        keyboardWetNose.add(buttonsRow3);
        keyboardWetNose.add(buttonsRow4);
        keyboardWetNose.add(buttonsRow5);

<<<<<<< HEAD
        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardWetNose.size()][];
        for (int i = 0; i < keyboardWetNose.size(); i++) {
            List<InlineKeyboardButton> row = keyboardWetNose.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markuWetNose = new InlineKeyboardMarkup(buttonsArray);
        return markuWetNose;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutPug() {

        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Расписание работы, адрес и схема проезда").callbackData("PUG_ADRESS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Оформить пропуск на машину").callbackData("PUG_PASS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("Техника безопасности на территории приюта").callbackData("PUG_SAFETY_BUTTON"));

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(new InlineKeyboardButton("Оставить контактные данные для связи").callbackData("CONTACTS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow5 = new ArrayList<>();
        buttonsRow5.add(new InlineKeyboardButton("Позвать волонтера").callbackData("VOLONTEER_BUTTON"));

        List<List<InlineKeyboardButton>> keyboardPug = new ArrayList<>();
        keyboardPug.add(buttonsRow1);
        keyboardPug.add(buttonsRow2);
        keyboardPug.add(buttonsRow3);
        keyboardPug.add(buttonsRow4);
        keyboardPug.add(buttonsRow5);

        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardPug.size()][];
        for (int i = 0; i < keyboardPug.size(); i++) {
            List<InlineKeyboardButton> row = keyboardPug.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markuPug = new InlineKeyboardMarkup(buttonsArray);
        return markuPug;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutPets() {

        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Cписок животных для усыновления").callbackData("CHOOSE_PET_BUTTON"));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Правила знакомства с животным").callbackData("RULES_BUTTON"));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("Рекомендации").callbackData("RECOMMENDATIONS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow4 = new ArrayList<>();
        buttonsRow4.add(new InlineKeyboardButton("Советы кинолога").callbackData("ADVICES_DOG_HANDLER_BUTTON"));

        List<InlineKeyboardButton> buttonsRow5 = new ArrayList<>();
        buttonsRow5.add(new InlineKeyboardButton("Причины отказа").callbackData("REASONS_FOR_REFUSAL_BUTTON"));

        List<InlineKeyboardButton> buttonsRow6 = new ArrayList<>();
        buttonsRow6.add(new InlineKeyboardButton("Оставить контакты для связи").callbackData("CONTACTS_BUTTON"));

        List<InlineKeyboardButton> buttonsRow7 = new ArrayList<>();
        buttonsRow7.add(new InlineKeyboardButton("Позвать волонтера").callbackData("VOLONTEER_BUTTON"));
=======
        InlineKeyboardMarkup markupShelter = getInlineKeyboardMarkup(keyboardWetNose);
        return markupShelter;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutPets() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(
                new InlineKeyboardButton("Cписок животных для усыновления")
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
>>>>>>> f182312 (1)

        List<List<InlineKeyboardButton>> keyboardInformationAboutPets = new ArrayList<>();
        keyboardInformationAboutPets.add(buttonsRow1);
        keyboardInformationAboutPets.add(buttonsRow2);
        keyboardInformationAboutPets.add(buttonsRow3);
        keyboardInformationAboutPets.add(buttonsRow4);
        keyboardInformationAboutPets.add(buttonsRow5);
        keyboardInformationAboutPets.add(buttonsRow6);
        keyboardInformationAboutPets.add(buttonsRow7);

<<<<<<< HEAD
        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardInformationAboutPets.size()][];
        for (int i = 0; i < keyboardInformationAboutPets.size(); i++) {
            List<InlineKeyboardButton> row = keyboardInformationAboutPets.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markupInformationAboutPets = new InlineKeyboardMarkup(buttonsArray);
=======
        InlineKeyboardMarkup markupInformationAboutPets = getInlineKeyboardMarkup(keyboardInformationAboutPets);
>>>>>>> f182312 (1)
        return markupInformationAboutPets;
    }

    public InlineKeyboardMarkup createKeyboardRecommendation() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
<<<<<<< HEAD
        buttonsRow1.add(new InlineKeyboardButton("По траснпортировке животного").callbackData("TRANSPORTATION_BUTTON"));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("По обустройству дома").callbackData("HOME_BUTTON"));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("По проверенным кинологам").callbackData("DOG_HANDLER_BUTTON"));
=======
        buttonsRow1.add(
                new InlineKeyboardButton("По траснпортировке животного")
                        .callbackData(TRANSPORTATION_BUTTON)
        );

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("По обустройству дома").callbackData(HOME_BUTTON));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("По проверенным кинологам").callbackData(DOG_HANDLER_BUTTON));
>>>>>>> f182312 (1)

        List<List<InlineKeyboardButton>> keyboardRecommendation = new ArrayList<>();
        keyboardRecommendation.add(buttonsRow1);
        keyboardRecommendation.add(buttonsRow2);
        keyboardRecommendation.add(buttonsRow3);

<<<<<<< HEAD

        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardRecommendation.size()][];
        for (int i = 0; i < keyboardRecommendation.size(); i++) {
            List<InlineKeyboardButton> row = keyboardRecommendation.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markupRecommendation = new InlineKeyboardMarkup(buttonsArray);
        return markupRecommendation;
    }

    public InlineKeyboardMarkup createKeyboardHomeRecomendation() {
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Для щенка").callbackData("PUPPY_HOME_BUTTON"));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Для взрослого животного").callbackData("DOG_HOME_BUTTON"));

        List<InlineKeyboardButton> buttonsRow3 = new ArrayList<>();
        buttonsRow3.add(new InlineKeyboardButton("Для животного с ограниченными возможностями").callbackData("ANIMALS_WITH_DISABILITIES_HOME_BUTTON"));
=======
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
>>>>>>> f182312 (1)

        List<List<InlineKeyboardButton>> keyboardHomeRecommendation = new ArrayList<>();
        keyboardHomeRecommendation.add(buttonsRow1);
        keyboardHomeRecommendation.add(buttonsRow2);
        keyboardHomeRecommendation.add(buttonsRow3);

<<<<<<< HEAD

        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardHomeRecommendation.size()][];
        for (int i = 0; i < keyboardHomeRecommendation.size(); i++) {
            List<InlineKeyboardButton> row = keyboardHomeRecommendation.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }
        InlineKeyboardMarkup markupHomeRecommendation = new InlineKeyboardMarkup(buttonsArray);
        return markupHomeRecommendation;
    }
=======
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
>>>>>>> f182312 (1)
}
