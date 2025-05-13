package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<ImagemEntityJpa, Long> {
}
