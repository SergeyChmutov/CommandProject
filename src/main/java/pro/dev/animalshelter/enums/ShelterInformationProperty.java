package pro.dev.animalshelter.enums;

import java.util.stream.Stream;

public enum ShelterInformationProperty {
    ABOUT("ABOUT"),
    ADDRESS("ADDRESS"),
    PASS("PASS"),
    SAFETY("SAFETY");

    private final String propertyName;

    ShelterInformationProperty(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public static ShelterInformationProperty getPropertyByName(String name) {
        return Stream.of(ShelterInformationProperty.values())
                .filter(c -> c.getPropertyName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
