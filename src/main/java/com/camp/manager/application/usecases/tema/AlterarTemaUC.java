package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.tema.AtualizarTemaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AlterarTemaUC implements UseCase<AtualizarTemaRequest, MethodResponse<Void>> {

    private final TemaGateway temaGateway;

    @Autowired
    public AlterarTemaUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    public MethodResponse<Void> execute(AtualizarTemaRequest input) {
        boolean temaEhExistente = this.temaGateway.temaEhExistentePorId(input.id());
        if(!temaEhExistente) throw new NotFoundException("Tema não encontrado com o id [" + input.id() + "]!");

        boolean descricaoEhExistente = this.temaGateway.temaEhExistentePorDescricao(input.descricao());
        if(descricaoEhExistente) throw new NotFoundException("Tema já cadastrado com a descrição [" + input.descricao() + "]!");

        TemaEntityDomain temaDomain = this.temaGateway.buscarTemaPorId(input.id());

        boolean houveAlteracao = this.verificarAlteracaoDeCampos(input, temaDomain);

        if(!houveAlteracao) {
            return new MethodResponse<>(304L, "Nenhuma alteração foi realizada!", null);
        }

        this.realizarAlteracaoTema(input, temaDomain);

        return new MethodResponse<>(200L, "Tema atualizado com sucesso!", null);
    }

    private boolean verificarAlteracaoDeCampos(AtualizarTemaRequest input, TemaEntityDomain temaDomain) {
        boolean alteracao = !input.descricao().equals(temaDomain.descricao());

        if( ! BigDecimal.valueOf(input.precoCamiseta()).equals(temaDomain.precoCamiseta())) {
            alteracao = true;
        }

        return alteracao;
    }
    private void realizarAlteracaoTema(AtualizarTemaRequest input, TemaEntityDomain temaDomain) {
        TemaEntityDomain novoTema = new TemaEntityDomain(
                temaDomain.id(),
                input.descricao(),
                temaDomain.imagemDesign(),
                BigDecimal.valueOf(input.precoCamiseta()),
                temaDomain.precoAcampamento()
        );

        this.temaGateway.atualizarTema(novoTema);
    }
}
