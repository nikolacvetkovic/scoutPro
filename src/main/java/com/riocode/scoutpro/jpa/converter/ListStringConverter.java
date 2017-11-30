package com.riocode.scoutpro.jpa.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Nikola Cvetkovic
 */

@Converter
public class ListStringConverter implements AttributeConverter<List<String>, String>{

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return new ArrayList<>(Arrays.asList(dbData.split(",")));
    }

}
