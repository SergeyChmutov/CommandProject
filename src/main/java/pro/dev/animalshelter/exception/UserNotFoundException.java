package pro.dev.animalshelter.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Не найден пользователь с идентификатором " + id);
    }
}
