package com.camp.manager.infra.persistence.repository;

import com.camp.manager.domain.enums.LocalEstoque;
import com.camp.manager.infra.persistence.entity.EstoqueEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntityJpa, Long> {
    boolean existsByid(Long idinformado);
    List<EstoqueEntityJpa> findAllByLocalEstoque(LocalEstoque tipoEstoque);
}
