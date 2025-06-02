package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.Sexo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoDatabaseMapper implements AttributeConverter<Sexo, String> {

    @Override
    public String convertToDatabaseColumn(Sexo sexo) {
        return sexo != null ? sexo.getValor() : null;
    }

    @Override
    public Sexo convertToEntityAttribute(String dbData) {
        return dbData != null ? Sexo.fromValor(dbData) : null;
    }
}
