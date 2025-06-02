package com.camp.manager.infra.http.dto.acampamento;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.infra.http.dto.cronograma.CronogramaDTO;
import com.camp.manager.infra.http.dto.equipe.EquipeDTO;
import com.camp.manager.infra.http.dto.galeria.ImagemDTO;
import com.camp.manager.infra.http.dto.tema.TemaDTO;
import com.camp.manager.infra.http.dto.tipoacampamento.TipoAcampamentoDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class AcampamentoCompletoDTO {

    private final Long id;
    private final String nome;
    private final Long limiteCampistas;
    private final Long limiteFuncionario;
    private final String codigoRegistro;
    private final TemaDTO tema;
    private final TipoAcampamentoDTO tipoAcampamento;
    private final CronogramaDTO cronograma;
    private final List<ImagemDTO> listImagem;
    private final List<EquipeDTO> listEquipe;

    public AcampamentoCompletoDTO(AcampamentoEntityDomain acampamentoEntityDomain){
        this.id = acampamentoEntityDomain.id();
        this.nome = acampamentoEntityDomain.nome();
        this.limiteCampistas = acampamentoEntityDomain.limiteCampistas();
        this.limiteFuncionario = acampamentoEntityDomain.limiteFuncionario();
        this.codigoRegistro = acampamentoEntityDomain.codigoRegistro();
        this.tema = new TemaDTO(acampamentoEntityDomain.tema());
        this.tipoAcampamento = new TipoAcampamentoDTO(acampamentoEntityDomain.tipoAcampamento());
        this.cronograma = new CronogramaDTO(acampamentoEntityDomain.cronograma());
        this.listImagem = ImagemDTO.converter(acampamentoEntityDomain.imagensDoAcampamento());
        this.listEquipe = EquipeDTO.converter(acampamentoEntityDomain.equipesDoAcampamento());
    }
}
