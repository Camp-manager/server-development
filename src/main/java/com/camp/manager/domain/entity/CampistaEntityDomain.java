package com.camp.manager.domain.entity;

import java.util.List;

public record CampistaEntityDomain(
        Long id,
        Boolean usaMedicamento,
        Boolean temAlergia,
        List<String> alergias,
        String codigoRegistro,
        Boolean jaFezAcampamento,
        List<String> acampamentosFeitos,
        Boolean temBarraca,
        CamisetaEntityDomain camiseta,
        PessoaEntityDomain pessoa,
        EquipeEntityDomain equipe) {
}
