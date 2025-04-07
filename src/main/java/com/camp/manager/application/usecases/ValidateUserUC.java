package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.ValidateUserGateway;

public class ValidateUserUC {

    private final ValidateUserGateway validateUserGateway;

    public ValidateUserUC(ValidateUserGateway validateUserGateway) {
        this.validateUserGateway = validateUserGateway;
    }

    public String validarUsuario(String login, String senha) {
        return this.validateUserGateway.validarUsuario(login, senha);
    }
}
