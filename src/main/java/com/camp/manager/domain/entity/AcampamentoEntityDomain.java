package com.camp.manager.domain.entity;

public record AcampamentoEntityDomain(
        Long id,
        String nome,
        Long limiteCampistas,
        Long limiteFuncionario,
        String codigoRegistro,
        TemaEntityDomain tema,
        TipoAcampamentoEntityDomain tipoAcampamento,
        CronogramaEntityDomain cronograma){
}
