package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.CamisetaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamisetaRepository extends JpaRepository<CamisetaEntityJpa, Long> {
}
