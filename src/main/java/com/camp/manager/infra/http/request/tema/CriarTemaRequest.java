package com.camp.manager.infra.http.request.tema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CriarTemaRequest(
        @NotBlank(message = "Descrição do tema não pode ser nulo ou em branco!")
        String descricao,

        @NotNull(message = "Preço da camiseta não pode ser nulo!")
        Double precoCamiseta,

        @NotNull(message = "Preço do acampamento não pode ser nulo!")
        Double precoAcampamento,

        MultipartFile arquivoImagemTema) {
}
