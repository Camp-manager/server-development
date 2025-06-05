package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.FuncionarioEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntityJpa, Long> {
    boolean existsByCpf(String cpf);
}
