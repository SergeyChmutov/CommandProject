package pro.dev.animalshelter.model;

import jakarta.persistence.*;
import pro.dev.animalshelter.converter.ShelterInformationPropertyConverter;
import pro.dev.animalshelter.enums.ShelterInformationProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    @CollectionTable(
            name = "shelter_information_mapping",
            joinColumns = {@JoinColumn(name = "shelter_id", referencedColumnName = "id")}
    )
    @MapKeyEnumerated(EnumType.STRING)
    @Convert(converter = ShelterInformationPropertyConverter.class,attributeName = "key")
    @Column(name = "property_value")
    private Map<ShelterInformationProperty, String> informationProperties = new HashMap<>();

    public Shelter() {
    }

    public Shelter(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<ShelterInformationProperty, String> getInformationProperties() {
        return informationProperties;
    }

    public void setInformationProperties(Map<ShelterInformationProperty, String> informationProperties) {
        this.informationProperties = informationProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id)
                && Objects.equals(name, shelter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
