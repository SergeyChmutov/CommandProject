package pro.dev.animalshelter.dto;

import java.util.Objects;

public class ShelterInformationDTO {
    private Long shelterId;
    private String propertyName;
    private String description;

    public ShelterInformationDTO(Long shelterId, String propertyName, String description) {

        this.shelterId = shelterId;
        this.propertyName = propertyName;
        this.description = description;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterInformationDTO that = (ShelterInformationDTO) o;
        return Objects.equals(shelterId, that.shelterId)
                && Objects.equals(propertyName, that.propertyName)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelterId, propertyName, description);
    }

    @Override
    public String toString() {
        return "ShelterInformationDTO{" +
                "shelterId=" + shelterId +
                ", propertyName='" + propertyName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
