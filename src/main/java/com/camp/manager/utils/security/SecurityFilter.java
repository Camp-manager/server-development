package com.camp.manager.utils.security;

import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.infra.persistence.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenEncoderAdapter tokenEncoderAdapter;
    private final UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenEncoderAdapter tokenEncoderAdapter, UserRepository userRepository) {
        this.tokenEncoderAdapter = tokenEncoderAdapter;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);
        if (token != null) {
            String tokenValidado = this.tokenEncoderAdapter.validar(token);
           UserDetails userDetails = this.userRepository.findByLogin(tokenValidado)
                   .orElse(null);
           UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails != null ? userDetails.getAuthorities() : null);
           SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
