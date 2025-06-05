package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UserRepository userRepository;
    private final Mapper<UserEntityJpa, UserEntityDomain> mapper;

    @Autowired
    public UsuarioGatewayImpl(UserRepository userRepository,
                              Mapper<UserEntityJpa, UserEntityDomain> mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserEntityDomain> findUserByLogin(String login) {
        Optional<UserEntityJpa> userEntityJpa = this.userRepository.findByLogin(login);
        return userEntityJpa.map(mapper::toDomain);
    }

    @Override
    public boolean existsUserByLogin(String login) {
        return this.userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public void salvarNovoUsuario(UserEntityDomain user) {
        this.userRepository.save(mapper.toEntity(user));
    }
}
