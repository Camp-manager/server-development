package com.camp.manager.application.gateway;

public interface PasswordEncoderAdapter {
    String encode(String password);
    boolean compare(String rawPassword, String encodedPassword);
}
