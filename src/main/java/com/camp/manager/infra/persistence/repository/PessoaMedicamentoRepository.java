package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.PessoaMedicamentoControllerEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaMedicamentoRepository extends JpaRepository<PessoaMedicamentoControllerEntityJpa, Long> {
}
