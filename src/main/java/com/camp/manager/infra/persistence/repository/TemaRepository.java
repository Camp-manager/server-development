package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaEntityJpa, Long> {

    boolean existsByDescricao(String descricao);
}
