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
import com.camp.manager.infra.http.request.pessoa.BuscarPessoaCpfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPessoaCpfUC implements UseCase<BuscarPessoaCpfRequest, MethodResponse<Object>> {

    private final CampistaGateway campistaGateway;
    private final FuncionarioGateway funcionarioGateway;

    @Autowired
    public BuscarPessoaCpfUC(CampistaGateway campistaGateway,
                             FuncionarioGateway funcionarioGateway) {
        this.campistaGateway = campistaGateway;
        this.funcionarioGateway = funcionarioGateway;
    }

    @Override
    public MethodResponse<Object> execute(BuscarPessoaCpfRequest input) {
        boolean campistaEhExistentePorCpfNoAcampamento = this.campistaGateway.campistaEhExistenteNoAcampamentoPorCpf(input.idAcampamento(), input.cpf());
        boolean funcionarioEhExistentePorCpfNoAcampamento = this.funcionarioGateway.funcionarioEhExistenteNoAcampamentoPorCpf(input.idAcampamento(), input.cpf());

        Object objetoDeRetorno = null;

        if(campistaEhExistentePorCpfNoAcampamento && funcionarioEhExistentePorCpfNoAcampamento) {
            throw new EntityFoundException("O cpf [" + input + "] repete para funcionário e campista no acampamento com id [" + input.idAcampamento() + "]!");
        }

        if(!campistaEhExistentePorCpfNoAcampamento && !funcionarioEhExistentePorCpfNoAcampamento) {
            throw new NotFoundException("O cpf [" + input + "] não existe em nenhum cadastro de pessoa!");
        }

        if(campistaEhExistentePorCpfNoAcampamento) {
            CampistaEntityDomain campistaEncontrado = this.campistaGateway.buscarCampistaNoAcampamentoPorCpf(input.idAcampamento(), input.cpf());
            objetoDeRetorno = new CampistaBasicoDTO(campistaEncontrado);
        }

        if(funcionarioEhExistentePorCpfNoAcampamento) {
            FuncionarioEntityDomain funcionarioEncontrado = this.funcionarioGateway.buscarFuncionarioNoAcampamentoPorCpf(input.idAcampamento(), input.cpf());
            objetoDeRetorno = new FuncionarioBasicoDTO(funcionarioEncontrado);
        }

        return new MethodResponse<>(200, "O cpf [" + input + "] pertence a um funcionario!", objetoDeRetorno);
    }
}
