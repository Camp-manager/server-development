package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.UserEntityDomain;

public interface TokenGateway {

    String gerarToken(UserEntityDomain login);
    String validarToken(String token);
}
