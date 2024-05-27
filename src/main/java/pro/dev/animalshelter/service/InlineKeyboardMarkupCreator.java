package pro.dev.animalshelter.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.dev.animalshelter.interfaces.ShelterService;
import pro.dev.animalshelter.listener.TelegramBotUpdatesListener;
import pro.dev.animalshelter.model.Shelter;
import pro.dev.animalshelter.model.Users;

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
                            .callbackData("INFO_" + shelter.getId()))
            );
        }

        keyboardShelters.add(Collections.singletonList(new InlineKeyboardButton("Основное меню")
                .callbackData(MAIN_MENU_BUTTON)));

        InlineKeyboardMarkup markupShelters = getInlineKeyboardMarkup(keyboardShelters);
        return markupShelters;
    }

    public InlineKeyboardMarkup createKeyboardChooseVolunteersShelter() {
        List<List<InlineKeyboardButton>> keyboardShelters = new ArrayList<>();
        for (Shelter shelter : shelterService.getShelters()) {
            keyboardShelters.add(
                    Collections.singletonList(new InlineKeyboardButton(shelter.getName())
                            .callbackData("VOLUNTEER_" + shelter.getId()))
            );
        }

        keyboardShelters.add(Collections.singletonList(new InlineKeyboardButton("Основное меню")
                .callbackData(MAIN_MENU_BUTTON)));

        InlineKeyboardMarkup markupShelters = getInlineKeyboardMarkup(keyboardShelters);
        return markupShelters;
    }

    public InlineKeyboardMarkup createKeyboardInformationAboutShelter() {
        List<List<InlineKeyboardButton>> keyboardWetNose = Arrays.asList(
                Collections.singletonList(new InlineKeyboardButton("Список животных для усыновления")
                        .callbackData(CHOOSE_PET_BUTTON)),
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
                Collections.singletonList(new InlineKeyboardButton("Правила знакомства с животным")
                        .callbackData(RULES_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Список документов для усыновления")
                        .callbackData(DOCUMENTS_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Рекомендации по транспортировке")
                        .callbackData(TRANSPORTATION_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обустройство дома для щенка")
                        .callbackData(PUPPY_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обустройство дома для взрослого животного")
                        .callbackData(DOG_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Обустройство дома для животного с ограничениями")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Советы кинолога по первичному общению с собакой")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Проверенные кинологи для обращения")
                        .callbackData(ANIMALS_WITH_DISABILITIES_HOME_BUTTON)),
                Collections.singletonList(new InlineKeyboardButton("Причины отказа в усыновлении")
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

    public InlineKeyboardMarkup createKeyboardShowSheltersAnimalNext(Long animalId) {
        List<List<InlineKeyboardButton>> keyboardDoneReport = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Заявка на усыновление")
                .callbackData("ADOPT_" + animalId));

        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Показать еще >>")
                .callbackData(SHOW_ANIMAL_NEXT_BUTTON));

        keyboardDoneReport.add(buttonsRow1);
        keyboardDoneReport.add(buttonsRow2);
        InlineKeyboardMarkup keyboardMarkup = getInlineKeyboardMarkup(keyboardDoneReport);
        return keyboardMarkup;
    }

    public InlineKeyboardMarkup createKeyboardAdopt(Long animalId) {
        List<List<InlineKeyboardButton>> keyboardAdopt = new ArrayList<>();
        keyboardAdopt.add(Collections.singletonList(new InlineKeyboardButton("Заявка на усыновление")
                .callbackData("ADOPT_" + animalId))
        );

        InlineKeyboardMarkup markupShowSheltersAnimalWithoutPrevious = getInlineKeyboardMarkup(keyboardAdopt);
        return markupShowSheltersAnimalWithoutPrevious;
    }


    private InlineKeyboardMarkup getInlineKeyboardMarkup(List<List<InlineKeyboardButton>> keyboardStart) {
        InlineKeyboardButton[][] buttonsArray = new InlineKeyboardButton[keyboardStart.size()][];

        for (int i = 0; i < keyboardStart.size(); i++) {
            List<InlineKeyboardButton> row = keyboardStart.get(i);
            buttonsArray[i] = row.toArray(new InlineKeyboardButton[0]);
        }

        return new InlineKeyboardMarkup(buttonsArray);
    }

    public InlineKeyboardMarkup createKeyboardCallVolunteer(String callbackData) {

        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton("Обратиться").callbackData(callbackData));
        List<List<InlineKeyboardButton>> keyboardCallVolunteer = new ArrayList<>();
        keyboardCallVolunteer.add(buttonsRow);
        InlineKeyboardMarkup markupCallVolunteer = getInlineKeyboardMarkup(keyboardCallVolunteer);
        return markupCallVolunteer;
    }

    public InlineKeyboardMarkup createKeyboardReturnMainMenu() {

        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton("Вернуться в основное меню").callbackData(MAIN_MENU_BUTTON));
        List<List<InlineKeyboardButton>> keyboardReturnMainMenu = new ArrayList<>();
        keyboardReturnMainMenu.add(buttonsRow);
        InlineKeyboardMarkup markupCallVolunteer = getInlineKeyboardMarkup(keyboardReturnMainMenu);
        return markupCallVolunteer;
    }

    public InlineKeyboardMarkup createKeyboardBackToRecomendation() {

        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton("Назад к списку рекомендаций").callbackData(INFORMATION_ABOUT_PETS_BUTTON));
        List<List<InlineKeyboardButton>> keyboardBack = new ArrayList<>();
        keyboardBack.add(buttonsRow);
        InlineKeyboardMarkup markupCallVolunteer = getInlineKeyboardMarkup(keyboardBack);
        return markupCallVolunteer;
    }

    public InlineKeyboardMarkup createKeyboardBackToShowAnimal() {

        List<InlineKeyboardButton> buttonsRow1 = new ArrayList<>();
        buttonsRow1.add(new InlineKeyboardButton("Назад к списку животных").callbackData(CHOOSE_PET_BUTTON));
        List<InlineKeyboardButton> buttonsRow2 = new ArrayList<>();
        buttonsRow2.add(new InlineKeyboardButton("Вернуться в основное меню").callbackData(MAIN_MENU_BUTTON));

        List<List<InlineKeyboardButton>> keyboardBacktoShowAnimal = new ArrayList<>();
        keyboardBacktoShowAnimal.add(buttonsRow1);
        keyboardBacktoShowAnimal.add(buttonsRow2);
        InlineKeyboardMarkup markupBackToShowAnimal = getInlineKeyboardMarkup(keyboardBacktoShowAnimal);
        return markupBackToShowAnimal;
    }

    public InlineKeyboardMarkup createKeyboardBackToMenuOfShelter(Long shelterId) {
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(new InlineKeyboardButton("Назад к информации о приюте").callbackData("INFO_" + shelterId));
        List<List<InlineKeyboardButton>> keyboardBackToMenuOfShelter = new ArrayList<>();
        keyboardBackToMenuOfShelter.add(buttonsRow);
        InlineKeyboardMarkup markupBackToMenuOfShelter = getInlineKeyboardMarkup(keyboardBackToMenuOfShelter);
        return markupBackToMenuOfShelter;

    }
}
