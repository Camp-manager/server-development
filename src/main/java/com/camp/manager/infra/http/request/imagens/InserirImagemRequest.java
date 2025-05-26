package com.camp.manager.infra.http.request.imagens;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record InserirImagemRequest(
        @NotNull(message = "Id do acampamento não pode ser nulo!")
        Long idAcampamento,

        MultipartFile zipComAsImagens,

        @NotNull(message = "Ano não pode ser nulo!")
        Long anoDasImagens) {
}
