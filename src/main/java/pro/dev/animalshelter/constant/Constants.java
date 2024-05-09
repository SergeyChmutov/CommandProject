package pro.dev.animalshelter.constant;

import java.time.format.DateTimeFormatter;

public class Constants {
    // About shelter
    public static final String INFORMATION_ABOUT_SHELTERS_BUTTON = "INFORMATION_ABOUT_SHELTERS_BUTTON";
    public static final String ABOUT_SHELTER_BUTTON = "ABOUT_SHELTER_BUTTON";
    public static final String ADDRESS_SHELTER_BUTTON = "ADDRESS_SHELTER_BUTTON";
    public static final String SCHEDULE_SHELTER_BUTTON = "SCHEDULE_SHELTER_BUTTON";
    public static final String ROADMAP_SHELTER_BUTTON = "ROADMAP_SHELTER_BUTTON";
    public static final String PASS_SHELTER_BUTTON = "PASS_SHELTER_BUTTON";
    public static final String SAFETY_SHELTER_BUTTON = "SAFETY_SHELTER_BUTTON";

    public static final String INFORMATION_ABOUT_PETS_BUTTON = "INFORMATION_ABOUT_PETS_BUTTON";
    public static final String SEND_REPORT_BUTTON = "SEND_REPORT_BUTTON";
    public static final String VOLUNTEER_BUTTON = "VOLUNTEER_BUTTON";
    public static final String CONTACTS_BUTTON = "CONTACTS_BUTTON";
    public static final String CHOOSE_PET_BUTTON = "CHOOSE_PET_BUTTON";
    public static final String MAIN_MENU_BUTTON = "MAIN_MENU_BUTTON";

    // Recommendations
    public static final String RULES_BUTTON = "RULES_BUTTON";
    public static final String ADVICES_DOG_HANDLER_BUTTON = "ADVICES_DOG_HANDLER_BUTTON";
    public static final String REASONS_FOR_REFUSAL_BUTTON = "REASONS_FOR_REFUSAL_BUTTON";
    public static final String TRANSPORTATION_BUTTON = "TRANSPORTATION_BUTTON";
    public static final String PUPPY_HOME_BUTTON = "PUPPY_HOME_BUTTON";
    public static final String DOG_HOME_BUTTON = "DOG_HOME_BUTTON";
    public static final String HOME_BUTTON = "HOME_BUTTON";
    public static final String DOG_HANDLER_BUTTON = "DOG_HANDLER_BUTTON";
    public static final String ANIMALS_WITH_DISABILITIES_HOME_BUTTON = "ANIMALS_WITH_DISABILITIES_HOME_BUTTON";
    public static final String DOCUMENTS_BUTTON = "DOCUMENTS_BUTTON";

    public static final String CALL_VOLUNTEER_BUTTON = "CALL_VOLUNTEER_BUTTON";

    public static final String MESSAGE_START = """
            Привет! Рад Вас здесь видеть!
            Я бот, который поможет вам взаимодействовать с приютами для собачек!
            Чем я могу помочь? Для начала выберите информацию о каком приюте Вы хотели бы получить:
            """;

    public static final String MESSAGE_SORRY = """
            Извините, я ограничен в возможностях общения! Для общения со мной используйте меню.
            Если в меню нет информации, которая Вас интересует, используйте пункт меню "Позвать волонтера"
            """;

    public static final String MESSAGE_CONTACT_INFORMATION_HELP = """
            Укажите телефон в формате +7-9XX-XXX-XX-XX и имя для связи
            (например, +7-912-345-67-89 Иван) или команду /start для возврата в основное меню:
            """;

    public static final String MESSAGE_CONTACT_INFORMATION_FOR_VOLUNTEER = """
            Для того, чтобы волонтер мог с вами связаться, оставьте ваши контактные данные для связи в формате "+7-9XX-XXX-XX-XX Имя" (например, +7-912-345-67-89 Иван) и они будут переданы волонтеру""";

    // Adoption Report
    public static final String MESSAGE_SEND_REPORT_HELP = """
            Заполнение и отправка отчета состоит из 4-х простых шагов:
            1. Прислать фото питомца
            2. Прислать информацию о рационе
            3. Описать общее самочувствие питомца
            4. Сообщить есть ли изменения в поведении питомца
            После выполнения всех шагов Вы автоматически будете перенаправлены в основное меню.
            """;
    public static final String REPORT_SEND_BUTTON = "SEND_REPORT_BUTTON";
    public static final String MESSAGE_REPORT_PHOTO_INPUT = "Пришлите фото питомца";
    public static final String MESSAGE_REPORT_RATION_INPUT = "Опишите рацион животного";
    public static final String MESSAGE_WELL_BEING_INPUT = "Опишите общее самочувствие животного и как он привыкает к новому месту";
    public static final String MESSAGE_BEHAVIOR_INPUT = "Есть ли изменение в поведении животного: отказ от старых привычек, приобретение новых?";
    public static final String MESSAGE_REPORT_DONE = "Спасибо за предоставленную информацию. Отчет отправлен.";

    public static final String MESSAGE_RETURN = "Привет! Рад Вас снова видеть! Выберите команду:";
    public static final String MESSAGE_CHOOSE_SHELTERS = "Информацию о каком приюте Вы хотели бы получить?";
    public static final String MESSAGE_CHOOSE_SHELTERS_FOR_VOLUNTEER = "К волонтеру из какого приюта Вы бы хотели обратиться?";
    public static final String MESSAGE_INFORMATION_ABOUT_SHELTER = "Здесь вы можете получить следующую информацию:";
    public static final String MESSAGE_INFORMATION_ABOUT_PETS = "Здесь я помогу вам разобраться с бюрократическими и бытовыми вопросами. Что вас интересует?";
    public static final String MESSAGE_CONNECTION_WITH_VOLUNTEER = "В ближайшее время он с вами свяжется";

    // LocalDate converted
    public static final String FORMAT_REPORT_DATE = "dd-MM-yyyy";
    public static final DateTimeFormatter REPORT_DATE_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_REPORT_DATE);

    // Show shelter`s animals
    public static final String SHOW_ANIMAL_PREVIOUS_BUTTON = "SHOW_ANIMAL_PREVIOUS_BUTTON";
    public static final String SHOW_ANIMAL_NEXT_BUTTON = "SHOW_ANIMAL_NEXT_BUTTON";
    public static final String SHOW_ANIMAL_CREATE_ADOPTION_BUTTON = "SHOW_ANIMAL_CREATE_ADOPTION_BUTTON";
    public static final String SHOW_ANIMAL_RETURN_BUTTON = "SHOW_ANIMAL_RETURN_BUTTON";
}
