package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.EstoqueEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntityJpa, Long> {
    boolean existsById(Long id);
    List<EstoqueEntityJpa> findAllByLocalEstoque_descricao(String tipoEstoque);
}
