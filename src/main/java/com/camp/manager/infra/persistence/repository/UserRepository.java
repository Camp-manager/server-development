package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.UserEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntityJpa, UUID> {
     Optional<UserEntityJpa> findByLogin(String login);
     boolean existsByLogin(String login);
}
