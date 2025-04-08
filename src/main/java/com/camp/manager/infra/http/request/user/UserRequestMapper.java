package com.camp.manager.infra.http.request.user;

import com.camp.manager.application.CreateUserRequest;
import com.camp.manager.domain.entity.UserEntityDomain;

public class UserRequestMapper {
    public static UserEntityDomain convert(CreateUserRequest request) {
        return new UserEntityDomain(
                request.login(),
                request.password(),
                request.roleUser()
        );
    }
}
