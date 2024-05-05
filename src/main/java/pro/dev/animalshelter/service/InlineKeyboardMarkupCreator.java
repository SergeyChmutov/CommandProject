package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;
import pro.dev.animalshelter.model.AdoptionReport;
import pro.dev.animalshelter.model.Shelter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
                new InlineKeyboardButton("Как взять животное из приюта")
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
        for (Shelter shelter : shelterService.getShelters()) {
            keyboardShelters.add(
                    Collections.singletonList(new InlineKeyboardButton(shelter.getName())
                            .callbackData(Long.toString(shelter.getId())))
            );
        }

        keyboardShelters.add(Collections.singletonList(new InlineKeyboardButton("Основное меню")
                .callbackData(MAIN_MENU_BUTTON)));

        InlineKeyboardMarkup markupShelters = getInlineKeyboardMarkup(keyboardShelters);
        return markupShelters;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutShelter() {
        List<List<InlineKeyboardButton>> keyboardWetNose = Arrays.asList(
                Collections.singletonList(new InlineKeyboardButton("Расписание работы")
                        .callbackData(SCHEDULE_SHELTER_BUTTON)),
                Arrays.asList(
                        new InlineKeyboardButton("О приюте")
                                .callbackData(ABOUT_SHELTER_BUTTON),
                        new InlineKeyboardButton("Адрес")
                                .callbackData(ADDRESS_SHELTER_BUTTON),
                        new InlineKeyboardButton("Схема проезда")
                                .callbackData(ROADMAP_SHELTER_BUTTON)
                ),
                Collections.singletonList(new InlineKeyboardButton("Оформить пропуск на машину")
                        .callbackData(PASS_SHELTER_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Техника безопасности на территории приюта")
                        .callbackData(SAFETY_SHELTER_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Оставить контактные данные для связи")
                        .callbackData(CONTACTS_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Позвать волонтера")
                        .callbackData(VOLUNTEER_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Основное меню")
                        .callbackData(MAIN_MENU_BUTTON))
        );

        InlineKeyboardMarkup markupShelter = getInlineKeyboardMarkup(keyboardWetNose);
        return markupShelter;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutPets() {
        List<List<InlineKeyboardButton>> keyboardInformationAboutPets = Arrays.asList(
                Collections.singletonList(new InlineKeyboardButton("Список животных для усыновления")
                        .callbackData(CHOOSE_PET_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Правила знакомства с животным")
                        .callbackData(RULES_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Список документов")
                        .callbackData(DOCUMENTS_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Рекомендации по транспортировке")
                        .callbackData(TRANSPORTATION_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обустройство дома для щенка")
                        .callbackData(PUPPY_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обустройство дома для взрослого животного")
                        .callbackData(DOG_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обустройство дома для животного с ограничениями")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Советы кинолога по первичному общению")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обращение к кинологам")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Причины отказа, по которым могут не дать забрать животное из приюта")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Оставить контакты для связи")
                        .callbackData(CONTACTS_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Позвать волонтера")
                        .callbackData(VOLUNTEER_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Основное меню")
                        .callbackData(MAIN_MENU_BUTTON))
        );

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

    public InlineKeyboardMarkup createKeyboardDoneReport() {
        List<List<InlineKeyboardButton>> keyboardDoneReport = Arrays.asList(
                Collections.singletonList(new InlineKeyboardButton("Отправить отчет")
                        .callbackData(REPORT_SEND_BUTTON))
        );

        InlineKeyboardMarkup keyboardMarkup = getInlineKeyboardMarkup(keyboardDoneReport);
        return keyboardMarkup;
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
