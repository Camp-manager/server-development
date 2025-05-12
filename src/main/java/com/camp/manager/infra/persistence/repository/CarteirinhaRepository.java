package com.camp.manager.infra.persistence.repository;

import com.camp.manager.infra.persistence.entity.CarteirinhaEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteirinhaRepository extends JpaRepository<CarteirinhaEntityJpa, Long> {
}
