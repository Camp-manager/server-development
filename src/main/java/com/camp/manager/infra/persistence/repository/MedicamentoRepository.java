package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.MedicamentoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<MedicamentoEntityJpa, Long> {
}
