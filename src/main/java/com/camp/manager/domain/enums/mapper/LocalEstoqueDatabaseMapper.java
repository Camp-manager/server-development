package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.LocalEstoque;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocalEstoqueDatabaseMapper implements AttributeConverter<LocalEstoque, String> {

    @Override
    public String convertToDatabaseColumn(LocalEstoque localEstoque) {
        return localEstoque != null ? localEstoque.getValor() : null;
    }

    @Override
    public LocalEstoque convertToEntityAttribute(String dbData) {
        return dbData != null ? LocalEstoque.valueOf(dbData) : null;
    }
}
