package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.*;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.domain.exception.custom.LimitOverflowException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.pessoa.CriarFuncionarioRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarFuncionarioUC implements UseCase<CriarFuncionarioRequest, MethodResponse<Void>> {

    private final FuncionarioGateway funcionarioGateway;
    private final AcampamentoGateway acampamentoGateway;
    private final UsuarioGateway usuarioGateway;

    @Autowired
    public AdicionarFuncionarioUC(FuncionarioGateway funcionarioGateway,
                                  AcampamentoGateway acampamentoGateway,
                                  UsuarioGateway usuarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
        this.acampamentoGateway = acampamentoGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(CriarFuncionarioRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorCodigoRegistro(input.getCodigoRegistro());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com código de registro [" + input.getCodigoRegistro() + "] não existe!");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorCodigoRegistro(input.getCodigoRegistro());
        this.validarQuantidadeDeFuncionarios(acampamentoEncontrado);

        boolean funcionarioEhExistente = this.funcionarioGateway.funcionarioEhExistentePorCpf(input.getCpf());
        if(funcionarioEhExistente) throw new EntityFoundException("O funcionário com CPF [" + input.getCpf() + "] já existe!");

        FuncionarioEntityDomain funcionarioCriado = this.converterRequestParaDomain(input, acampamentoEncontrado);

        this.funcionarioGateway.inserirFuncionario(funcionarioCriado);
        this.inserirUsuarioParaLogin(funcionarioCriado);

        return new MethodResponse<>(201, "Funcionário adicionado com sucesso!", null);
    }

    private FuncionarioEntityDomain converterRequestParaDomain(CriarFuncionarioRequest input, AcampamentoEntityDomain acampamentoEncontrado) {
        return new FuncionarioEntityDomain(
                null,
                input.getNome(),
                input.getCpf(),
                input.getTelefone(),
                input.getEmail(),
                input.getCodigoRegistro(),
                input.getHabilidade(),
                new CamisetaEntityDomain(null, input.getTamanhoCamisa(), acampamentoEncontrado.tema()),
                null,
                null
        );
    }
    private void validarQuantidadeDeFuncionarios(AcampamentoEntityDomain acampamentoEncontrado) {
        long quantidadeDeFuncionariosAtual = acampamentoEncontrado
                .equipesDoAcampamento()
                .stream()
                .filter(equipe -> {
                    TipoEquipe tipo = TipoEquipe.fromDescricao(equipe.tipoEquipe());
                    return TipoEquipe.TRABALHO.equals(tipo);
                })
                .mapToLong(equipe -> this.funcionarioGateway
                        .buscarTodosOsFuncionariosComBaseNaEquipe(equipe.id())
                        .size())
                .sum();

        if (acampamentoEncontrado.limiteFuncionario() <= quantidadeDeFuncionariosAtual) {
            throw new LimitOverflowException("O acampamento com código de registro ["
                    + acampamentoEncontrado.codigoRegistro()
                    + "] já atingiu o limite de campistas permitidos!");
        }
    }
    private void inserirUsuarioParaLogin(FuncionarioEntityDomain funcionarioCriado) {
        String senhaPadrao = funcionarioCriado.cpf().substring(0, 3);
        UserEntityDomain usuarioCriado = new UserEntityDomain(null,
                funcionarioCriado.nome(),
                funcionarioCriado.email(),
                senhaPadrao,
                "FUNCIONARIO");
        this.usuarioGateway.salvarNovoUsuario(usuarioCriado);
    }
}
