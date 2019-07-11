package xyz.riocode.scoutpro.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@Converter
public class SetStringConverter implements AttributeConverter<Set<String>, String>{

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return String.join(",", attribute);
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return new HashSet<>(Arrays.asList(dbData.split(",")));
    }

}
