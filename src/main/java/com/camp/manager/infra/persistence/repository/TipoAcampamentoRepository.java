package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.TipoAcampamentoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAcampamentoRepository extends JpaRepository<TipoAcampamentoEntityJpa, Long> {
    boolean existsByDescricao(String descricaoDoTipoAcampamento);
}
