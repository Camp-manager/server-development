package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.pessoa.CriarFuncionarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarFuncionarioUC implements UseCase<CriarFuncionarioRequest, MethodResponse<Void>> {

    private final FuncionarioGateway funcionarioGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AdicionarFuncionarioUC(FuncionarioGateway funcionarioGateway,
                                  AcampamentoGateway acampamentoGateway) {
        this.funcionarioGateway = funcionarioGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarFuncionarioRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorCodigoRegistro(input.getCodigoRegistro());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com código de registro [" + input.getCodigoRegistro() + "] não existe!");

        boolean funcionarioEhExistente = this.funcionarioGateway.funcionarioEhExistentePorCpf(input.getCpf());
        if(funcionarioEhExistente) throw new EntityFoundException("O funcionário com CPF [" + input.getCpf() + "] já existe!");




        return null;
    }
}
