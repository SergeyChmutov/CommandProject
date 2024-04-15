package pro.dev.animalshelter.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pro.dev.animalshelter.enums.ShelterInformationProperty;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ShelterInformationPropertyConverter implements AttributeConverter<ShelterInformationProperty, String> {

    @Override
    public String convertToDatabaseColumn(ShelterInformationProperty property) {
        if (property == null) {
            return null;
        }
        return property.getPropertyName();
    }

    @Override
    public ShelterInformationProperty convertToEntityAttribute(String propertyName) {
        if (propertyName == null) {
            return null;
        }

        return Stream.of(ShelterInformationProperty.values())
                .filter(c -> c.getPropertyName().equals(propertyName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
