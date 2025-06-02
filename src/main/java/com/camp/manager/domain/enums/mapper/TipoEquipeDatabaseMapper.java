package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.TipoEquipe;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoEquipeDatabaseMapper implements AttributeConverter<TipoEquipe, String> {
    @Override
    public String convertToDatabaseColumn(TipoEquipe tipoEquipe) {
        return tipoEquipe != null ? tipoEquipe.getValor() : null;
    }

    @Override
    public TipoEquipe convertToEntityAttribute(String dbData) {
        return dbData != null ? TipoEquipe.fromValor(dbData) : null;
    }
}
