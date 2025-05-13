package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.EquipeDiaFuncaoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeDiaFuncaoRepository extends JpaRepository<EquipeDiaFuncaoEntityJpa, Long> {
}
