package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.UserEntityDomain;

public interface CreateUserGateway {
    UserEntityDomain criarUsuario(UserEntityDomain usuario);
}
