package com.camp.manager.domain.entity;

import java.time.LocalDate;

public record ImagemEntityDomain(
        Long id,
        byte[] arquivoImagem,
        LocalDate data,
        String nomeAcampamento) {
}
