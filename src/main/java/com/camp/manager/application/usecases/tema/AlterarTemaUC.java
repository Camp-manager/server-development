package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotAcceptableException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.tema.AtualizarTemaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AlterarTemaUC implements UseCase<AtualizarTemaRequest, MethodResponse<Void>> {

    private final TemaGateway temaGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AlterarTemaUC(TemaGateway temaGateway,
                         AcampamentoGateway acampamentoGateway) {
        this.temaGateway = temaGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(AtualizarTemaRequest input) {
        boolean temaEhExistente = this.temaGateway.temaEhExistentePorId(input.id());
        if(!temaEhExistente) throw new NotFoundException("Tema não encontrado com o id [" + input.id() + "]!");

        boolean descricaoEhExistente = this.temaGateway.temaEhExistentePorDescricao(input.descricao());
        if(descricaoEhExistente) throw new NotFoundException("Tema já cadastrado com a descrição [" + input.descricao() + "]!");

        TemaEntityDomain temaDomain = this.temaGateway.buscarTemaPorId(input.id());
        this.validarAtualizacaoDePrecoAcampamento(temaDomain);

        boolean houveAlteracao = this.verificarAlteracaoDeCampos(input, temaDomain);

        if(!houveAlteracao) {
            return new MethodResponse<>(304, "Nenhuma alteração foi realizada!", null);
        }

        this.realizarAlteracaoTema(input, temaDomain);

        return new MethodResponse<>(202, "Tema atualizado com sucesso!", null);
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
                BigDecimal.valueOf(input.precoAcampamento())
        );

        this.temaGateway.atualizarTema(novoTema);
    }
    private void validarAtualizacaoDePrecoAcampamento(TemaEntityDomain temaDomain) {
        List<AcampamentoEntityDomain> acampamentosEncontrados = this.acampamentoGateway.buscarTodosOsAcampamentosComBaseNoTema(temaDomain.id());

        boolean haAcampamentosNaoEncerrados = acampamentosEncontrados.stream()
                .anyMatch(t -> t.cronograma().dataFinal().isAfter(LocalDate.now()) || t.cronograma().dataFinal().isEqual(LocalDate.now()));

        if(haAcampamentosNaoEncerrados) {
            throw new NotAcceptableException("Não é possível atualizar o preço do acampamento, pois existem acampamentos não encerrados com base nesse tema!");
        }
    }
}
