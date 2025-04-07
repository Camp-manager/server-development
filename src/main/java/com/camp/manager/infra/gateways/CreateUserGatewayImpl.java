package com.camp.manager.infra.gateways;

import com.camp.manager.application.gateway.CreateUserGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.mapper.UserMapper;
import com.camp.manager.infra.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserGatewayImpl implements CreateUserGateway {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void criarUsuario(UserEntityDomain usuario) {
        this.userRepository.save(UserMapper.toEntity(usuario));
    }
}
