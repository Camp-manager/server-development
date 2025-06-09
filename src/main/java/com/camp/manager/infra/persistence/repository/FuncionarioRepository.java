package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.FuncionarioEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntityJpa, Long> {
    boolean existsByCpf(String cpf);
    List<FuncionarioEntityJpa> findAllByEquipe_IdOrderByNome(Long equipeId);
    List<FuncionarioEntityJpa> findAllByCodigoRegistroOrderByNome(String codigoRegistro);
    FuncionarioEntityJpa findByCpf(String input);
}
