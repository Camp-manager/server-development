package com.camp.manager.utils.config.bean;

import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.utils.security.SecurityConfiguration;
import com.camp.manager.utils.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfiguration {
    @Bean
    protected AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilter securityFilter(TokenEncoderAdapter tokenEncoderAdapter, UsuarioGateway usuarioGateway, Mapper<UserEntityJpa, UserEntityDomain> userMapper) {
       return new SecurityFilter(tokenEncoderAdapter, usuarioGateway, userMapper);
    }

    @Bean
    public SecurityConfiguration securityConfiguration(SecurityFilter securityFilter) {
        return new SecurityConfiguration(securityFilter);
    }
}
