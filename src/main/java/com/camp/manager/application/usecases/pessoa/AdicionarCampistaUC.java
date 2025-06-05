package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.domain.exception.custom.LimitOverflowException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.pessoa.CriarCampistaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarCampistaUC implements UseCase<CriarCampistaRequest, MethodResponse<Void>>{

    private final CampistaGateway campistaGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AdicionarCampistaUC(CampistaGateway campistaGateway,
                               AcampamentoGateway acampamentoGateway) {
        this.campistaGateway = campistaGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarCampistaRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorCodigoRegistro(input.getCodigoRegistro());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com código de registro [" + input.getCodigoRegistro() + "] não existe!");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorCodigoRegistro(input.getCodigoRegistro());
        this.validarQuantidadeDeCampistas(acampamentoEncontrado);

        boolean campistaEhExistente = this.campistaGateway.campistaEhExistentePorCpf();

        return null;
    }

    private void validarQuantidadeDeCampistas(AcampamentoEntityDomain acampamentoEncontrado) {
        long quantidadeDeCampistasAtuais = acampamentoEncontrado
                .equipesDoAcampamento()
                .stream()
                .filter(equipe -> {
                    TipoEquipe tipo = TipoEquipe.fromDescricao(equipe.tipoEquipe());
                    return TipoEquipe.CAMPISTA.equals(tipo);
                })
                .mapToLong(equipe -> this.campistaGateway
                        .buscarTodosOsCampistasComBaseNaEquipe(equipe.id())
                        .size())
                .sum();
        if (acampamentoEncontrado.limiteFuncionario() <= quantidadeDeCampistasAtuais) {
            throw new LimitOverflowException("O acampamento com código de registro ["
                    + acampamentoEncontrado.codigoRegistro()
                    + "] já atingiu o limite de funcionários permitidos!");
        }
    }
}
