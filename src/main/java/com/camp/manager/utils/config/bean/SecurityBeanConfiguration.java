package com.camp.manager.utils.config.bean;

import com.camp.manager.application.gateway.TokenGateway;
import com.camp.manager.infra.persistence.repository.UserRepository;
import com.camp.manager.utils.security.SecurityConfiguration;
import com.camp.manager.utils.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfiguration {

    private final UserDetailsService userDetailsService;

    public SecurityBeanConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(this.passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilter securityFilter(TokenGateway tokenGateway, UserRepository userRepository) {
       return new SecurityFilter( tokenGateway, userRepository);
    }

    @Bean
    public SecurityConfiguration securityConfiguration(SecurityFilter securityFilter) {
        return new SecurityConfiguration(securityFilter);
    }
}
