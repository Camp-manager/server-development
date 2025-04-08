package com.camp.manager.infra.persistence.mapper;

import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.domain.enums.UserRole;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;

public class UserMapper {
    public static UserEntityJpa toEntity(UserEntityDomain userDomain) {
        String role = userDomain.role();
        return new UserEntityJpa(null ,userDomain.login(), userDomain.password(), UserRole.valueOf(role));
    }

    public static UserEntityDomain toModel(UserEntityJpa userJpa) {
        return new UserEntityDomain(userJpa.getLogin(), userJpa.getPassword(), userJpa.getRole().name());
    }

}
