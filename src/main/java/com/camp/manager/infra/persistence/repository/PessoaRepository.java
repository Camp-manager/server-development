package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.PessoaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntityJpa, Long> {
}
