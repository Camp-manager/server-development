package com.camp.manager.infra.http.request.pessoa;

public record BuscarPessoaCpfRequest(
        String cpf,
        Long idAcampamento
) {
}
