package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.UserEntityDomain;

import java.util.Optional;

public interface UsuarioGateway {
    Optional<UserEntityDomain> findUserByLogin(String login);
    boolean existsUserByLogin(String login);
    void salvar(UserEntityDomain user);
}
