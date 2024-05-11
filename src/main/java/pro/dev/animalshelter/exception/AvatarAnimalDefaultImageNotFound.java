package pro.dev.animalshelter.exception;

public class AvatarAnimalDefaultImageNotFound extends RuntimeException {
    public AvatarAnimalDefaultImageNotFound() {
        super("Не найден файл ресурса отсутствующего изображения питомца.");
    }
}
