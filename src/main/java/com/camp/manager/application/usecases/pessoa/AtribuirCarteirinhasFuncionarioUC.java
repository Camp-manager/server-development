package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CarteirinhaEntityDomain;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.pessoa.AtribuirCarteirinhaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtribuirCarteirinhasFuncionarioUC implements UseCase<AtribuirCarteirinhaRequest, MethodResponse<Void>>{

    private final FuncionarioGateway funcionarioGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AtribuirCarteirinhasFuncionarioUC(FuncionarioGateway funcionarioGateway,
                                             AcampamentoGateway acampamentoGateway) {
        this.funcionarioGateway = funcionarioGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(AtribuirCarteirinhaRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input.idAcampamento());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com id [" + input.idAcampamento() + "] não existe!");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input.idAcampamento());
        TemaEntityDomain temaEncontrado = acampamentoEncontrado.tema();

        input.listaIdsPessoas().forEach(idFuncionario -> {
            boolean funcionarioEhExistente = this.funcionarioGateway.funcionarioEhExistentePorId(idFuncionario);
            if(!funcionarioEhExistente) throw new NotFoundException("O funcionário com id [" + idFuncionario + "] não existe!");
            FuncionarioEntityDomain funcionario = this.funcionarioGateway.buscarFuncionarioPorId(idFuncionario);

            this.atribuirCarteirinhaParaFuncionario(funcionario, temaEncontrado);
        });

        return new MethodResponse<>(200, "Carteirinhas atribuídas com sucesso!", null);
    }

    private void atribuirCarteirinhaParaFuncionario(FuncionarioEntityDomain funcionario, TemaEntityDomain temaEncontrado ) {
        FuncionarioEntityDomain funcionarioComCarteirinha = new FuncionarioEntityDomain(
                funcionario.id(),
                funcionario.nome(),
                funcionario.cpf(),
                funcionario.telefone(),
                funcionario.email(),
                funcionario.codigoRegistro(),
                funcionario.habilidade(),
                funcionario.camiseta(),
                funcionario.equipe(),
                new CarteirinhaEntityDomain(
                        null,
                        funcionario.nome(),
                        temaEncontrado
                )
        );

        this.funcionarioGateway.inserirFuncionario(funcionarioComCarteirinha);
    }
}
