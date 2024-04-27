package pro.dev.animalshelter.enums;

import pro.dev.animalshelter.exception.RecommendationTypeErrorNameException;

import java.util.Collection;
import java.util.stream.Stream;

public enum RecommendationType {
    DATING_RULES("DATING-RULES"),
    DOCUMENTS("DOCUMENTS"),
    TRANSPORTATIONS("TRANSPORTATIONS"),
    PUPPY_HOME("PUPPY-HOME"),
    DOG_HOME("DOG-HOME"),
    DISABILITIES("DISABILITIES"),
    DOG_HANDLER("DOG-HANDLER"),
    ADVICES_DOG_HANDLER("ADVICES-DOG-HANDLER"),
    REASONS_REFUSAL("REASONS-REFUSAL");

    private final String typeName;

    RecommendationType(String propertyName) {
        this.typeName = propertyName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static RecommendationType getTypeByName(String name) {
        return Stream.of(RecommendationType.values())
                .filter(c -> c.getTypeName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RecommendationTypeErrorNameException("Ошибка указания имени рекомендации"));
    }

    public static Collection<String> getTypesList() {
        return Stream.of(RecommendationType.values())
                .map(RecommendationType::getTypeName)
                .toList();
    }
}
