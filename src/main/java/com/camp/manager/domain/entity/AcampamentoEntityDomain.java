package com.camp.manager.domain.entity;

public record AcampamentoEntityDomain(
        Long id,
        Long limiteCampistas,
        Long limiteFuncionario,
        TemaEntityDomain tema,
        TipoAcampamentoEntityDomain tipoAcampamento) {
}
