package pro.dev.animalshelter.exception;

public class RecommendationInformationNotFoundException extends RuntimeException {
    public RecommendationInformationNotFoundException(String typeName) {
        super("Не найдена рекомендация с типом " + typeName);
    }
}
