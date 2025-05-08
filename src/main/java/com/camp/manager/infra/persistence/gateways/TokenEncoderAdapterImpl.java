package com.camp.manager.infra.persistence.gateways;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.domain.entity.UserEntityDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenEncoderAdapterImpl implements TokenEncoderAdapter {

    @Value("${api.security.token-secret}")
    private String secret;

    @Override
    public String gerar(UserEntityDomain usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("login", usuario.login())
                    .withClaim("username", usuario.username())
                    .withClaim("role", usuario.role())
                    .withIssuer("Spring Security Project By: Uryel_0910")
                    .withSubject("Token de autenticação do usuário")
                    .withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token do usuário!!",exception);
        }
    }

    @Override
    public String validar(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("Spring Security Project By: Uryel_0910")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
