package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.pessoa.CampistaBasicoDTO;
import com.camp.manager.infra.http.dto.pessoa.FuncionarioBasicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPessoaCpfUC implements UseCase<String, MethodResponse<Object>> {

    private final CampistaGateway campistaGateway;
    private final FuncionarioGateway funcionarioGateway;

    @Autowired
    public BuscarPessoaCpfUC(CampistaGateway campistaGateway,
                             FuncionarioGateway funcionarioGateway) {
        this.campistaGateway = campistaGateway;
        this.funcionarioGateway = funcionarioGateway;
    }

    @Override
    public MethodResponse<Object> execute(String input) {
        boolean campistaEhExistentePorCpf = this.campistaGateway.campistaEhExistentePorCpf(input);
        boolean funcionarioEhExistentePorCpf = this.funcionarioGateway.funcionarioEhExistentePorCpf(input);

        Object objetoDeRetorno = null;

        if(campistaEhExistentePorCpf && funcionarioEhExistentePorCpf) {
            throw new EntityFoundException("O cpf [" + input + "] repete em campista e funcionario!");
        }

        if(!campistaEhExistentePorCpf && !funcionarioEhExistentePorCpf) {
            throw new NotFoundException("O cpf [" + input + "] não existe em nenhum cadastro de pessoa!");
        }

        if(campistaEhExistentePorCpf) {
            CampistaEntityDomain campistaEncontrado = this.campistaGateway.buscarCampistaPorCpf(input);
            objetoDeRetorno = new CampistaBasicoDTO(campistaEncontrado);
        }

        if(funcionarioEhExistentePorCpf) {
            FuncionarioEntityDomain funcionarioEncontrado = this.funcionarioGateway.buscarFuncionarioPorCpf(input);
            objetoDeRetorno = new FuncionarioBasicoDTO(funcionarioEncontrado);
        }

        return new MethodResponse<>(200, "O cpf [" + input + "] pertence a um funcionario!", objetoDeRetorno);
    }
}
