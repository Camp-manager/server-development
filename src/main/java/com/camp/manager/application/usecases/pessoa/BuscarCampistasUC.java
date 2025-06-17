package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.gateway.PessoaMedicamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.domain.entity.PessoaMedicamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.medicamento.MedicamentoDTO;
import com.camp.manager.infra.http.dto.pessoa.CampistaBasicoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BuscarCampistasUC implements UseCase<Long, MethodResponse<List<CampistaBasicoDTO>>> {

    private final CampistaGateway campistaGateway;
    private final AcampamentoGateway acampamentoGateway;
    private final PessoaMedicamentoGateway pessoaMedicamentoGateway;

    @Autowired
    public BuscarCampistasUC(CampistaGateway campistaGateway,
                             AcampamentoGateway acampamentoGateway,
                             PessoaMedicamentoGateway pessoaMedicamentoGateway) {
        this.campistaGateway = campistaGateway;
        this.acampamentoGateway = acampamentoGateway;
        this.pessoaMedicamentoGateway = pessoaMedicamentoGateway;
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

        List<CampistaBasicoDTO> campistaBasicoDTOS = this.montarListCampistaBasicoDTO(listaDeCampistas);

        return new MethodResponse<>(200, "Lista de campistas obtida com sucesso!", campistaBasicoDTOS);
    }

    private List<CampistaBasicoDTO> montarListCampistaBasicoDTO( List<CampistaEntityDomain> campistas){
        List<CampistaBasicoDTO> campistaBasicoDTOS = new ArrayList<>();

        Stream<List<CampistaBasicoDTO>> almir = Stream.of(campistaBasicoDTOS);

        campistas.forEach(campista -> {
            CampistaBasicoDTO dtoCampistaCriado = new CampistaBasicoDTO(campista);

            List<PessoaMedicamentoEntityDomain > medicamentosAlergicos = this.pessoaMedicamentoGateway
                    .buscarMedicamentosPorPessoa(campista.pessoa());
            if(!medicamentosAlergicos.isEmpty()){
                List<MedicamentoEntityDomain> medicamentosAlergicosDomain = medicamentosAlergicos
                        .stream()
                        .map(PessoaMedicamentoEntityDomain::medicamento)
                        .toList();

                dtoCampistaCriado.setMedicamentosAlergicos(MedicamentoDTO.converter(medicamentosAlergicosDomain));
            }

            campistaBasicoDTOS.add(dtoCampistaCriado);
        });

        return campistaBasicoDTOS;
    }
}
