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
            Для того, чтобы волонтер мог с вами связаться, оставьте ваши контактные данные для связи
            в формате "+7-9XX-XXX-XX-XX Имя" (например, +7-912-345-67-89 Иван) и они будут переданы волонтеру
            """;

    // Adoption Report
    public static final String MESSAGE_SEND_REPORT_HELP = """
            Заполнение и отправка отчета состоит из 4-х простых шагов:
            1. Прислать фото питомца
            2. Прислать информацию о рационе
            3. Описать общее самочувствие питомца
            4. Сообщить есть ли изменения в поведении питомца
            После выполнения всех шагов Вы автоматически будете перенаправлены в основное меню.
            """;
    public static final String MESSAGE_REPORT_PHOTO_INPUT = "Пришлите фото питомца";
    public static final String MESSAGE_REPORT_RATION_INPUT = "Опишите рацион животного";
    public static final String MESSAGE_WELL_BEING_INPUT = "Опишите общее самочувствие животного и как он привыкает к новому месту";
    public static final String MESSAGE_BEHAVIOR_INPUT = "Есть ли изменение в поведении животного: отказ от старых привычек, приобретение новых?";
    public static final String MESSAGE_REPORT_DONE = "Спасибо за предоставленную информацию. Отчет отправлен.";
    public static final String MESSAGE_REPORT_APPROVED_ADOPTION_NOT_FOUND = "Уважаемый пользователь! У Вас нет одобренной заявки на усыновление, а значит Вам не нужно присылать отчет.";
    public static final String MESSAGE_REPORT_BAD_FILED = """
            Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо.
            Пожалуйста, подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного
            """;
    public static final String MESSAGE_REPORT_NOT_FILED = """
            Дорогой усыновитель! Вы не предоставили отчет за вчерашний день о состоянии питомца.
            Пожалуйста, подойди ответственнее к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного
            """;

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

    // Adoptions status
    public static final String MESSAGE_STATUS_APPROVED = """
            Уважаемый пользователь! Поздравляю Вас с одобрением заявки на усыновление c номером %d.
            Теперь Ваша задача забрать животное из приюта и начать присылать нам ежедневный отчет о состоянии питомца. 
            """;
    public static final String MESSAGE_STATUS_REJECTED = """
            Уважаемый пользователь! К сожалению Ваша заявка на усыновление не была одобрена.
            Попробуйте еще раз после того как ознакомитесь с правилами усыновления и сможете подготовить все необходимое для усыновления.
            """;
    public static final String MESSAGE_STATUS_REJECTED_DURING_TRIAL_DATE = """
            Уважаемый пользователь! К сожалению Ваш процесс усыновления остановлен до окончания испытательного периода.
            Просим Вас подготовить животное к транспортировке - в ближайшее время представитель приюта должен будет забрать его.  
            """;
    public static final String MESSAGE_CHANGE_TRIAL_PERIOD = "Уважаемый усыновитель! Вам продлен испытательный срок на %d дней.";
    public static final String MESSAGE_TRIAL_PERIOD_END = """
            Уважаемый усыновитель! Поздравляем Вас! Вы успрешно прошли испытательный срок и мы рады,
            что наш питомец нашел своего друга в Вашем лице. Спасибо Вам!
            """;

    // Adoptions trial periods
    public static final Integer ADOPTION_TRIAL_PERIOD_THIRTY_DAYS = 30;
}
