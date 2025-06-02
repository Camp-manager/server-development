package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.TipoMedicamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoMedicamentoDatabaseMapper implements AttributeConverter<TipoMedicamento, String> {

    @Override
    public String convertToDatabaseColumn(TipoMedicamento tipoMedicamento) {
        return tipoMedicamento != null ? tipoMedicamento.getValor() : null;
    }

    @Override
    public TipoMedicamento convertToEntityAttribute(String dbData) {
        return dbData != null ? TipoMedicamento.fromValor(dbData) : null;
    }
}
