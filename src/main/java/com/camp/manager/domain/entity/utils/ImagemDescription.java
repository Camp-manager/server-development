package com.camp.manager.domain.entity.utils;

public record ImagemDescription(
        String nomeDoArquivo,
        byte[] bytesDaImagem,
        String extensaoDaImagem) {
}
