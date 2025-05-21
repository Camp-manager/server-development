package com.camp.manager.domain.entity;

public record AcampamentoEntityDomain(
        Long id,
        Long limiteCampistas,
        Long limiteFuncionario,
        String codigoRegistro,
        TemaEntityDomain tema,
        TipoAcampamentoEntityDomain tipoAcampamento) {
}
