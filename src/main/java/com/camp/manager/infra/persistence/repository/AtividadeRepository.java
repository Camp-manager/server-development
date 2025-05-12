package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.AtividadeEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntityJpa, Long> {
}
