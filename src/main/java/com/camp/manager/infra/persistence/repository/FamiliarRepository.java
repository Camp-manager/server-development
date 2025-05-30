package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.FamiliarEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends JpaRepository<FamiliarEntityJpa, Long> {
}
