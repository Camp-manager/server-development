package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.CategoriaTipoAcampamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoriaTipoAcampamentoDatabaseMapper implements AttributeConverter<CategoriaTipoAcampamento, String> {
    @Override
    public String convertToDatabaseColumn(CategoriaTipoAcampamento categoriaTipoAcampamento) {
        return categoriaTipoAcampamento != null ? categoriaTipoAcampamento.getValor() : null;
    }

    @Override
    public CategoriaTipoAcampamento convertToEntityAttribute(String dbData) {
        return dbData != null ? CategoriaTipoAcampamento.fromValor(dbData) : null;
    }
}
