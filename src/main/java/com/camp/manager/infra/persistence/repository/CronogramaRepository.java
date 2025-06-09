package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.CronogramaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CronogramaRepository extends JpaRepository<CronogramaEntityJpa, Long> {
}
