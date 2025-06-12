package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.CampistaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcampamentoRepository extends JpaRepository<AcampamentoEntityJpa, Long> {
    AcampamentoEntityJpa findByCodigoRegistro(String codigo);
    boolean existsByCodigoRegistro(String codigo);
    List<AcampamentoEntityJpa> findAllByTema_Id(Long temaId);
}
