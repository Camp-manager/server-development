package com.camp.manager.infra.security;

import com.camp.manager.domain.enums.UserRole;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import com.camp.manager.infra.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntityJpa user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario com username: [" + username + "] não encontrado!"));
        return UserEntityJpa.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
