package com.camp.manager.infra.http.dto.acampamento;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.infra.http.dto.tema.TemaDTO;
import com.camp.manager.infra.http.dto.tipoacampamento.TipoAcampamentoDTO;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class AcampamentoBasicoDTO {
    private final Long idAcampamento;
    private final String nomeAcampamento;
    private final String codigoRegistro;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final Long limiteFuncionarios;
    private final Long limiteCampistas;
    private final TipoAcampamentoDTO tipoAcampamento;
    private final TemaDTO tema;

    public AcampamentoBasicoDTO(AcampamentoEntityDomain acampamentoEntityDomain){
        this.idAcampamento = acampamentoEntityDomain.id();
        this.nomeAcampamento = acampamentoEntityDomain.nome();
        this.codigoRegistro = acampamentoEntityDomain.codigoRegistro();
        this.dataInicio = acampamentoEntityDomain.cronograma().dataInicio();
        this.dataFim = acampamentoEntityDomain.cronograma().dataFinal();
        this.limiteFuncionarios = acampamentoEntityDomain.limiteFuncionario();
        this.limiteCampistas = acampamentoEntityDomain.limiteCampistas();
        this.tipoAcampamento = new TipoAcampamentoDTO(acampamentoEntityDomain.tipoAcampamento());
        this.tema = new TemaDTO(acampamentoEntityDomain.tema());
    }

    public static List<AcampamentoBasicoDTO> converter(List<AcampamentoEntityDomain> acampamentos) {
        return acampamentos.stream()
                .map(AcampamentoBasicoDTO::new)
                .toList();
    }
}
