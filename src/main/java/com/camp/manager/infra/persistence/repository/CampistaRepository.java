package com.camp.manager.infra.persistence.repository;

import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.infra.persistence.entity.CampistaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampistaRepository extends JpaRepository<CampistaEntityJpa, Long> {
    List<CampistaEntityJpa> findAllByEquipe_Id(Long equipeId);
    boolean existsByPessoa_Cpf(String cpf);

    List<CampistaEntityJpa> findAllByCodigoRegistroOrderByPessoa_NomeCompleto(String codigoRegistro);
    CampistaEntityJpa findByPessoa_Cpf(String cpf);
}
