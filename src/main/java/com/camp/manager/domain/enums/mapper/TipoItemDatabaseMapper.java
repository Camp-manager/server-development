package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.TipoItem;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoItemDatabaseMapper implements AttributeConverter<TipoItem, String> {
    @Override
    public String convertToDatabaseColumn(TipoItem tipoItem) {
        return tipoItem != null ? tipoItem.getValor() : null;
    }

    @Override
    public TipoItem convertToEntityAttribute(String dbData) {
        return dbData != null ? TipoItem.fromValor(dbData) : null;
    }
}
