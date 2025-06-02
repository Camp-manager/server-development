package com.camp.manager.infra.http.dto.acampamento;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.infra.http.dto.tipoacampamento.TipoAcampamentoDTO;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class AcampamentoDTO {
    private final Long idAcampamento;
    private final String nomeAcampamento;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final Long limiteFuncionarios;
    private final Long limiteCampistas;
    private final TipoAcampamentoDTO tipoAcampamento;

    public AcampamentoDTO (AcampamentoEntityDomain acampamentoEntityDomain){
        this.idAcampamento = acampamentoEntityDomain.id();
        this.nomeAcampamento = acampamentoEntityDomain.nome();
        this.dataInicio = acampamentoEntityDomain.cronograma().dataInicio();
        this.dataFim = acampamentoEntityDomain.cronograma().dataFinal();
        this.limiteFuncionarios = acampamentoEntityDomain.limiteFuncionario();
        this.limiteCampistas = acampamentoEntityDomain.limiteCampistas();
        this.tipoAcampamento = new TipoAcampamentoDTO(acampamentoEntityDomain.tipoAcampamento());
    }

    public static List<AcampamentoDTO> converter(List<AcampamentoEntityDomain> acampamentos) {
        return acampamentos.stream()
                .map(AcampamentoDTO::new)
                .toList();
    }
}
