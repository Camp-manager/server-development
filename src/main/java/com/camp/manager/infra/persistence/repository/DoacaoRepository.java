package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.DoacaoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoEntityJpa, Long> {
}
