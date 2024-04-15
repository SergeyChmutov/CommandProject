package pro.dev.animalshelter.enums;

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
}
