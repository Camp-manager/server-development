package com.camp.manager.infra.http.request.tipoacampamento;

import jakarta.validation.constraints.NotBlank;

public record CriarTipoAcampamentoRequest(
        @NotBlank(message = "Descrição do tipo de acampamento não pode ser vazia")
        String descricao,

        @NotBlank(message = "Categoria do acampamento não pode ser vazia")
        String categoriaDoAcampamento) {
}
