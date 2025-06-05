package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.pessoa.CampistaBasicoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarCampistasUC implements UseCase<Long, MethodResponse<List<CampistaBasicoDTO>>> {

    private final CampistaGateway campistaGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarCampistasUC(CampistaGateway campistaGateway,
                             AcampamentoGateway acampamentoGateway) {
        this.campistaGateway = campistaGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<List<CampistaBasicoDTO>> execute(Long input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!acampamentoEhExistente) throw new EntityNotFoundException("O acampamento com id [" + input + "] não existe!");

        String codigoRegistroDoAcampamento = this.acampamentoGateway
                .buscarAcampamentoPorId(input)
                .codigoRegistro();

        List<CampistaEntityDomain> listaDeCampistas = this.campistaGateway
                .buscarTodosOsCampistasComBaseNoCodigoRegistro(codigoRegistroDoAcampamento);

        return new MethodResponse<>(200, "Lista de campistas obtida com sucesso!", CampistaBasicoDTO.converter(listaDeCampistas));
    }
}
