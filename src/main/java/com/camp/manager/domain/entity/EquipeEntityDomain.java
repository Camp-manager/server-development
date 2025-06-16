package com.camp.manager.domain.entity;

import java.util.List;

public record EquipeEntityDomain(
        Long id,
        String nome,
        String tipoEquipe,
        List<CronogramaEquipeEntityDomain> cronograma,
        AcampamentoEntityDomain acampamento,
        List<CampistaEntityDomain> campistasNaEquipe,
        List<FuncionarioEntityDomain> funcionariosNaEquipe,
        FuncionarioEntityDomain liderDaEquipe,
        List<EquipeDiaFuncaoEntityDomain> diasDaFuncao) {
}
