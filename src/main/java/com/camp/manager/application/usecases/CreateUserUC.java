package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.CreateUserGateway;
import com.camp.manager.domain.entity.UserEntityDomain;

public class CreateUserUC {

    private final CreateUserGateway createUserGateway;

    public CreateUserUC(CreateUserGateway createUserGateway) {
        this.createUserGateway = createUserGateway;
    }

    public UserEntityDomain criarNovoUsuario(UserEntityDomain usuario) {
        return this.createUserGateway.criarUsuario(usuario);
    }

}
