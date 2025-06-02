package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.TipoAtividade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAtividadeDatabaseMapper implements AttributeConverter<TipoAtividade, String> {
    @Override
    public String convertToDatabaseColumn(TipoAtividade tipoAtividade) {
        return tipoAtividade != null ? tipoAtividade.getValor() : null;
    }

    @Override
    public TipoAtividade convertToEntityAttribute(String dbData) {
        return dbData != null ? TipoAtividade.fromValor(dbData) : null;
    }
}
