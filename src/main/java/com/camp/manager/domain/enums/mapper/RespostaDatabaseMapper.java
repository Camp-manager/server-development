package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.Resposta;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RespostaDatabaseMapper implements AttributeConverter<Resposta, String> {
    @Override
    public String convertToDatabaseColumn(Resposta resposta) {
        return resposta != null ? resposta.getValor() : null;
    }

    @Override
    public Resposta convertToEntityAttribute(String dbData) {
        return dbData != null ? Resposta.valueOf(dbData) : null;
    }
}
