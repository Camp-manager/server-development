package com.camp.manager.application.gateway;

import com.camp.manager.infra.persistence.entity.UserEntityJpa;

public interface TokenGateway {
    String gerarToken(UserEntityJpa login);
    String validarToken(String token);
}
