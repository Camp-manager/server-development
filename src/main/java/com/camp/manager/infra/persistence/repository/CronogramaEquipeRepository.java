package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.CronogramaEquipeEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaEquipeRepository extends JpaRepository<CronogramaEquipeEntityJpa, Long> {
}
