package com.camp.manager.infra.http.dto.medicamento;

import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MedicamentoDTO {
    private Long id;
    private String nome;
    private String quantidade;
    private String tipo;

    public MedicamentoDTO(MedicamentoEntityDomain entityDomain) {
        this.id = entityDomain.id();
        this.nome = entityDomain.nome();
        this.quantidade = entityDomain.quantidade();
        this.tipo = entityDomain.tipo();
    }

    public static List<MedicamentoDTO> converter(List<MedicamentoEntityDomain> medicamentoEntityDomains) {
        return medicamentoEntityDomains.stream()
                .map(MedicamentoDTO::new)
                .collect(Collectors.toList());
    }
}
