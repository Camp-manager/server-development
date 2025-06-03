package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.EquipeEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<EquipeEntityJpa, Long> {
}
