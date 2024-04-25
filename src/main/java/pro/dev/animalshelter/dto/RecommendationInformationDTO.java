package pro.dev.animalshelter.dto;

import pro.dev.animalshelter.enums.RecommendationType;

import java.util.Objects;

public class RecommendationInformationDTO {
    private RecommendationType type;
    private String information;

    public RecommendationInformationDTO() {
    }

    public RecommendationInformationDTO(RecommendationType type, String information) {
        this.type = type;
        this.information = information;
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationInformationDTO that = (RecommendationInformationDTO) o;
        return type == that.type && Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, information);
    }

    @Override
    public String toString() {
        return "RecommendationInformationDTO{" +
                "type=" + type +
                ", information='" + information + '\'' +
                '}';
    }
}
