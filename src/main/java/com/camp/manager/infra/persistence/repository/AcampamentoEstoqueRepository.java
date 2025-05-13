package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.AcampamentoEstoqueControllerEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcampamentoEstoqueRepository extends JpaRepository<AcampamentoEstoqueControllerEntityJpa, Long> {
}
