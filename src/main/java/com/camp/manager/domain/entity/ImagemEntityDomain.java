package com.camp.manager.domain.entity;

import java.nio.file.Path;
import java.time.LocalDate;

public record ImagemEntityDomain(
        Long id,
        byte[] arquivoImagem,
        Path pathLocalizacao,
        LocalDate data,
        String nomeAcampamento) {
}
