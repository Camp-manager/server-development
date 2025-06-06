package com.camp.manager.infra.http.request.pessoa;

import java.util.List;

public record AtribuirCarteirinhaRequest(
        Long idAcampamento,
        List<Long> listaIdsPessoas
) {
}
