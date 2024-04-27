package pro.dev.animalshelter.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pro.dev.animalshelter.enums.RecommendationType;

@Converter(autoApply = true)
public class RecommendationTypeConverter implements AttributeConverter<RecommendationType, String> {
    @Override
    public String convertToDatabaseColumn(RecommendationType property) {
        if (property == null) {
            return null;
        }
        return property.getTypeName();
    }

    @Override
    public RecommendationType convertToEntityAttribute(String propertyName) {
        if (propertyName == null) {
            return null;
        }

        return RecommendationType.getTypeByName(propertyName);
    }
}
