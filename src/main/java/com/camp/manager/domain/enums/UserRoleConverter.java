package com.camp.manager.domain.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole != null ? userRole.getRole() : null;
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return dbData != null ? UserRole.valueOf(dbData) : null;
    }
}

/*
@Converter(autoApply = true)
public class AbonoTipoConversao implements AttributeConverter<AbonoTipo, Integer>{

    private ConvertEnum<AbonoTipo, Integer> convertEnum = new ConvertEnumMaybeNull<>();
    @Override
    public Integer convertToDatabaseColumn(AbonoTipo abonoTipo) {
        return convertEnum.getRepresentacaoValorEnumParaBancoDeDados(abonoTipo);
    }

    @Override
    public AbonoTipo convertToEntityAttribute(Integer dbData) {
        return convertEnum.getEnum(AbonoTipo.values(), dbData);
    }
}

 */
