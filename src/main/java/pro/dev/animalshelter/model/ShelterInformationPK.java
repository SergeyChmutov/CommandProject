package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import pro.dev.animalshelter.converter.ShelterInformationPropertyConverter;
import pro.dev.animalshelter.enums.ShelterInformationProperty;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShelterInformationPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "shelter_id", insertable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_shelter_id"))
    private Shelter shelter;
    @Convert(converter = ShelterInformationPropertyConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "property_name", insertable = false, updatable = false)
    private ShelterInformationProperty shelterInformationProperty;

    private ShelterInformationPK() {
    }

    public ShelterInformationPK(Shelter shelter, ShelterInformationProperty shelterInformationProperty) {
        this.shelter = shelter;
        this.shelterInformationProperty = shelterInformationProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterInformationPK that = (ShelterInformationPK) o;
        return Objects.equals(shelter, that.shelter)
                && shelterInformationProperty == that.shelterInformationProperty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelter, shelterInformationProperty);
    }
}
