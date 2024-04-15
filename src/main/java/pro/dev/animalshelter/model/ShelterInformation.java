package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import pro.dev.animalshelter.enums.ShelterInformationProperty;

import java.util.Objects;

@Entity
@IdClass(ShelterInformation.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueShelterAndInformationProperty",
                columnNames = { "shelter_id", "shelter_information_property" })
})
public class ShelterInformation {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;
    @Id
    @Column(name = "shelter_information_property")
    private ShelterInformationProperty shelterInformationProperty;
    private String description;

    public ShelterInformation() {
    }

    public ShelterInformation(Shelter shelter, ShelterInformationProperty shelterInformationProperty) {
        this.shelter = shelter;
        this.shelterInformationProperty = shelterInformationProperty;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public ShelterInformationProperty getShelterInformationProperty() {
        return shelterInformationProperty;
    }

    public void setShelterInformationProperty(ShelterInformationProperty shelterInformationProperty) {
        this.shelterInformationProperty = shelterInformationProperty;
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
        ShelterInformation that = (ShelterInformation) o;
        return Objects.equals(shelter, that.shelter)
                && shelterInformationProperty == that.shelterInformationProperty
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelter, shelterInformationProperty, description);
    }
}
