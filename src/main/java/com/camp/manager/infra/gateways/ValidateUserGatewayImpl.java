package com.camp.manager.infra.gateways;

import com.camp.manager.application.gateway.TokenGateway;
import com.camp.manager.application.gateway.ValidateUserGateway;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserGatewayImpl implements ValidateUserGateway {
    private final AuthenticationManager authenticationManager;
    private final TokenGateway tokenGateway;

    @Autowired
    public ValidateUserGatewayImpl(AuthenticationManager authenticationManager, TokenGateway tokenGateway) {
        this.authenticationManager = authenticationManager;
        this.tokenGateway = tokenGateway;
    }

    @Override
    public String validarUsuario(String login, String senha) {
        UsernamePasswordAuthenticationToken tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(login, senha);
        try {
            Authentication authentication = this.authenticationManager.authenticate(tokenDeAutenticacao);
            return this.tokenGateway.gerarToken((UserEntityJpa) authentication.getPrincipal());
        } catch (Exception e) {
            System.out.println("Erro ao validar usuario: " + e.getMessage());
        }
        return null;
    }
}
