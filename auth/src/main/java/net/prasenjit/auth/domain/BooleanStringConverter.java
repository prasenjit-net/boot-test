package net.prasenjit.auth.domain;

import javax.persistence.AttributeConverter;

/**
 * Created by PRASEN on 4/3/2016.
 */
public class BooleanStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        return "Y".equals(s) ? true : false;
    }
}
