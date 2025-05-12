package com.camp.manager.domain.enums.mapper;

import com.camp.manager.domain.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleDatabaseMapper implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole != null ? userRole.getRole() : null;
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return dbData != null ? UserRole.valueOf(dbData) : null;
    }
}

