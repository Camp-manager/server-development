package com.camp.manager.domain.entity;

import java.util.List;

public record AcampamentoEntityDomain(
        Long id,
        String nome,
        Long limiteCampistas,
        Long limiteFuncionario,
        String codigoRegistro,
        TemaEntityDomain tema,
        TipoAcampamentoEntityDomain tipoAcampamento,
        CronogramaEntityDomain cronograma,
        List<ImagemEntityDomain> imagensDoAcampamento,
        List<EquipeEntityDomain> equipesDoAcampamento) {
}
