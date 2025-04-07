package com.camp.manager.infra.gateways;

import com.camp.manager.application.gateway.TokenGateway;
import com.camp.manager.application.gateway.ValidateUserGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import com.camp.manager.infra.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserGatewayImpl implements ValidateUserGateway {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenGateway tokenGateway;

    @Autowired
    public ValidateUserGatewayImpl(UserRepository userRepository, AuthenticationManager authenticationManager, TokenGateway tokenGateway) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenGateway = tokenGateway;
    }

    @Override
    public String validarUsuario(String login, String senha) {
        UsernamePasswordAuthenticationToken tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(login, senha);
        try {
            Authentication authentication = this.authenticationManager.authenticate(tokenDeAutenticacao);
            String tokenDoUsuario = this.tokenGateway.gerarToken((UserEntityDomain) authentication.getPrincipal());
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao validar usuario: " + e.getMessage());
        }
        return null;
    }
}
