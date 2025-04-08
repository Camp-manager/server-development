package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.UserEntityDomain;

public interface TokenEncoderAdapter {
    String gerar(UserEntityDomain usuario);
    String validar(String token);
}
