package com.camp.manager.utils.security;

import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import com.camp.manager.infra.mapper.Mapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenEncoderAdapter tokenEncoderAdapter;
    private final UsuarioGateway usuarioGateway;
    private final Mapper<UserEntityJpa, UserEntityDomain> userEntityJpaMapper;

    @Autowired
    public SecurityFilter(TokenEncoderAdapter tokenEncoderAdapter, UsuarioGateway usuarioGateway, Mapper<UserEntityJpa, UserEntityDomain> userEntityJpaMapper) {
        this.tokenEncoderAdapter = tokenEncoderAdapter;
        this.usuarioGateway = usuarioGateway;
        this.userEntityJpaMapper = userEntityJpaMapper;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);
        if (token != null) {
            String tokenValidado = this.tokenEncoderAdapter.validar(token);
            UserDetails userDetails = userEntityJpaMapper.toEntity(this.usuarioGateway.findUserByLogin(tokenValidado)
                    .orElse(null));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.substring(7);
        }
        return null;
    }
}
