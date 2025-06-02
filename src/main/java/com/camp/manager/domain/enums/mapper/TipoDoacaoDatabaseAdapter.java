package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.TipoDoacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoDoacaoDatabaseAdapter implements AttributeConverter<TipoDoacao, String> {
    @Override
    public String convertToDatabaseColumn(TipoDoacao tipoDoacao) {
        return tipoDoacao != null ? tipoDoacao.getValor() : null;
    }

    @Override
    public TipoDoacao convertToEntityAttribute(String dbData) {
        return dbData != null ? TipoDoacao.fromValor(dbData) : null;
    }
}
