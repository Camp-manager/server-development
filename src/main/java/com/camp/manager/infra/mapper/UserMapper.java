package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.domain.enums.UserRole;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper implements Mapper<UserEntityJpa, UserEntityDomain> {

    @Override
    public UserEntityJpa toEntity(UserEntityDomain userDomain) {
        String role = userDomain.role();
        return new UserEntityJpa(
                UUID.fromString(userDomain.id()),
                userDomain.username(),
                userDomain.login(),
                userDomain.password(),
                UserRole.valueOf(role));
    }

    @Override
    public UserEntityDomain toDomain(UserEntityJpa userJpa) {
        return new UserEntityDomain(
                userJpa.getId().toString(),
                userJpa.getUsername(),
                userJpa.getLogin(),
                userJpa.getPassword(),
                userJpa.getRole().name());
    }
}
