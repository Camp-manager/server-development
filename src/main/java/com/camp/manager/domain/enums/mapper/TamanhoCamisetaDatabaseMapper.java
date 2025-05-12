package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.TamanhoCamiseta;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TamanhoCamisetaDatabaseMapper implements AttributeConverter<TamanhoCamiseta, String> {

    @Override
    public String convertToDatabaseColumn(TamanhoCamiseta tamanhoCamiseta) {
        return tamanhoCamiseta != null ? tamanhoCamiseta.getValor() : null;
    }

    @Override
    public TamanhoCamiseta convertToEntityAttribute(String dbData) {
        return dbData != null ? TamanhoCamiseta.valueOf(dbData) : null;
    }
}
