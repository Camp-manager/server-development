package com.camp.manager.infra.http.request.user;

import com.camp.manager.domain.entity.UserEntityDomain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRequestMapper {

    public static UserEntityDomain convert(CreateUserRequest request) {
        return new UserEntityDomain(
                request.login(),
                new BCryptPasswordEncoder().encode(request.password()),
                request.roleUser()
        );
    }
}
