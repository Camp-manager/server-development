package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.Parentesco;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ParentescoDatabaseMapper implements AttributeConverter<Parentesco, String> {
    @Override
    public String convertToDatabaseColumn(Parentesco parentesco) {
        return parentesco != null ? parentesco.getValor() : null;
    }

    @Override
    public Parentesco convertToEntityAttribute(String dbData) {
        return dbData != null ? Parentesco.valueOf(dbData) : null;
    }
}
