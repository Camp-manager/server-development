package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcampamentoRepository extends JpaRepository<AcampamentoEntityJpa, Long> {
    AcampamentoEntityJpa findByCodigoRegistro(String codigo);
    boolean existsByCodigoRegistro(String codigo);
}
