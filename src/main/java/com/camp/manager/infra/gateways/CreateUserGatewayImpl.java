package com.camp.manager.infra.gateways;

import com.camp.manager.application.gateway.CreateUserGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.mapper.UserMapper;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import com.camp.manager.infra.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserGatewayImpl implements CreateUserGateway {

    private final UserRepository userRepository;

    public CreateUserGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntityDomain criarUsuario(UserEntityDomain usuario) {
        UserEntityJpa userCreated = this.userRepository.save(UserMapper.toEntity(usuario));
        return UserMapper.toModel(userCreated);
    }
}
