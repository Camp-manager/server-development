package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;

public class UserMapper {

    public static UserEntityJpa toEntity(UserEntityDomain userDomain) {
        return new UserEntityJpa(null ,userDomain.login(), userDomain.password(), userDomain.role());
    }

    public static UserEntityDomain toModel(UserEntityJpa userJpa) {
        return new UserEntityDomain(userJpa.getLogin(), userJpa.getPassword(), userJpa.getRole());
    }

}
