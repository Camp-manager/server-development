package com.camp.manager.infra.http.request.imagens;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

public record InserirImagemRequest(
        @NotNull(message = "Id do acampamento não pode ser nulo!")
        @Positive(message = "O id do acampamento deve ser maior que zero")
        Long idAcampamento,

        MultipartFile zipComAsImagens,

        @NotNull(message = "Ano não pode ser nulo!")
        @Positive(message = "Ano deve ser maior ou igual a zero")
        Long anoDasImagens) {
}
