package com.camp.manager.infra.http.request.tema;

import org.springframework.web.multipart.MultipartFile;

public record ImagemTemaRequest(
        Long idTema,
        MultipartFile arquivoImagemTema
) {
}
