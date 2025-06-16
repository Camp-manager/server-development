package com.camp.manager.infra.http.dto.pessoa;

import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.enums.Resposta;
import com.camp.manager.infra.http.dto.medicamento.MedicamentoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampistaBasicoDTO {

    private final Long id;
    private final String nomeCompleto;
    private final String telefone;
    private final String sexo;
    private final Double peso;
    private final Double altura;
    private final Resposta foiBatizado;
    private final Resposta temPrimeiraComunhao;
    private final Resposta jaFezAcampamento;
    private final Resposta temBarraca;
    private final Resposta temAlegia;
    private List<MedicamentoDTO> medicamentosAlergicos;
    private final List<String> alergias;

    public CampistaBasicoDTO(CampistaEntityDomain campista) {
        this.id = campista.id();
        this.nomeCompleto = campista.pessoa().nome() + " " + campista.pessoa().sobrenome();
        this.telefone = campista.pessoa().telefone();
        this.sexo = campista.pessoa().sexo();
        this.peso = campista.pessoa().peso();
        this.altura = campista.pessoa().altura();
        this.foiBatizado = Resposta.fromBoolean(campista.pessoa().foiBatizado());
        this.temPrimeiraComunhao = Resposta.fromBoolean(campista.pessoa().temPrimeiraComunhao());
        this.jaFezAcampamento = Resposta.fromBoolean(campista.jaFezAcampamento());
        this.temBarraca = Resposta.fromBoolean(campista.temBarraca());
        this.temAlegia = Resposta.fromBoolean(campista.temAlergia());
        this.alergias = campista.alergias();
    }

    public static List<CampistaBasicoDTO> converter(List<CampistaEntityDomain> listaDeCampistas) {
        return listaDeCampistas.stream().map(CampistaBasicoDTO::new).toList();
    }
}
