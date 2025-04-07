package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.domain.enums.UserRole;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    public static UserEntityJpa toEntity(UserEntityDomain userDomain) {
        String role = userDomain.role();
        String senhaEncriptada = new BCryptPasswordEncoder().encode(userDomain.password());
        return new UserEntityJpa(null ,userDomain.login(), senhaEncriptada, UserRole.valueOf(role));
    }

    public static UserEntityDomain toModel(UserEntityJpa userJpa) {
        return new UserEntityDomain(userJpa.getLogin(), userJpa.getPassword(), userJpa.getRole().getRole());
    }

}
