package com.camp.manager.infra.persistence.repository;

import com.camp.manager.domain.enums.TipoMedicamento;
import com.camp.manager.infra.persistence.entity.MedicamentoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntityJpa, Long> {

    List<MedicamentoEntityJpa> findAllByOrderByNomeAsc();
    boolean existsById(Long idMedicamento);

}
