package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.pessoa.FuncionarioBasicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarFuncionariosUC implements UseCase<Long, MethodResponse<List<FuncionarioBasicoDTO>>> {

    private final FuncionarioGateway funcionarioGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarFuncionariosUC(FuncionarioGateway funcionarioGateway,
                                AcampamentoGateway acampamentoGateway) {
        this.funcionarioGateway = funcionarioGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<List<FuncionarioBasicoDTO>> execute(Long input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com id [" + input + "] não existe!");

        String codigoRegistroDoAcampamento = this.acampamentoGateway
                .buscarAcampamentoPorId(input)
                .codigoRegistro();

        List<FuncionarioEntityDomain> listaDeFuncionarios = this.funcionarioGateway
                .buscarTodosOsFuncionariosComBaseNoCodigoRegistro(codigoRegistroDoAcampamento);

        return new MethodResponse<>(200, "Funcionários encontrados com sucesso!", FuncionarioBasicoDTO.converter(listaDeFuncionarios));
    }
}
