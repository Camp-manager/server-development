package com.camp.manager.infra.security;

import com.camp.manager.application.gateway.PasswordEncoderAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptPasswordEncoderAdapterImpl implements PasswordEncoderAdapter {
    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean compare(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
