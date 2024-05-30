package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import pro.dev.animalshelter.enums.ShelterInformationProperty;

import java.util.Objects;

@Entity
public class ShelterInformation {
    @EmbeddedId
    private ShelterInformationPK pk;
    @Column(name = "description")
    private String description;

    private ShelterInformation() {
    }

    public ShelterInformation(String description) {
        this.description = description;
    }

    public ShelterInformation(
            Shelter shelter,
            ShelterInformationProperty shelterInformationProperty,
            String description
    ) {
        this.pk = new ShelterInformationPK(shelter, shelterInformationProperty);
        this.description = description;
    }

    public ShelterInformationPK getPk() {
        return pk;
    }

    public void setPk(ShelterInformationPK pk) {
        this.pk = pk;
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
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
