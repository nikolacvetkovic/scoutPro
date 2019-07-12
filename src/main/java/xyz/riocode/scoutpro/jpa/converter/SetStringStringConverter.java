package xyz.riocode.scoutpro.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetStringStringConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        return strings.stream().collect(Collectors.joining());
    }

    @Override
    public Set<String> convertToEntityAttribute(String s) {
        return new HashSet<>(Arrays.asList(s));
    }
}
