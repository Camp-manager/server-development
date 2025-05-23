package com.camp.manager.infra.http.request.tema;

import org.springframework.web.multipart.MultipartFile;

public record CriarTemaRequest(
        String descricao,
        Double precoCamiseta,
        Double precoAcampamento,
        MultipartFile arquivoImagemTema
) {
}
