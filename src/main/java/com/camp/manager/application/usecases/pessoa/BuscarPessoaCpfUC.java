package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
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

import java.util.Objects;

@Service
public class BuscarPessoaCpfUC implements UseCase<BuscarPessoaCpfRequest, MethodResponse<Object>> {

    private final CampistaGateway campistaGateway;
    private final FuncionarioGateway funcionarioGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarPessoaCpfUC(CampistaGateway campistaGateway,
                             FuncionarioGateway funcionarioGateway,
                             AcampamentoGateway acampamentoGateway) {
        this.campistaGateway = campistaGateway;
        this.funcionarioGateway = funcionarioGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Object> execute(BuscarPessoaCpfRequest input) {
        boolean campistaehExistentePorCpf = this.campistaGateway.campistaEhExistentePorCpf(input.cpf());
        boolean funcionarioEhExistentePorCpf = this.funcionarioGateway.funcionarioEhExistentePorCpf(input.cpf());
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input.idAcampamento());

        if(!acampamentoEhExistente) {
            throw new NotFoundException("O acampamento com id [" + input.idAcampamento() + "] não existe!");
        }

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input.idAcampamento());

        Object objetoDeRetorno = null;

        if(campistaehExistentePorCpf && funcionarioEhExistentePorCpf) {
            throw new EntityFoundException("O cpf [" + input.cpf() + "] repete para funcionário e campista no acampamento com id [" + input.idAcampamento() + "]!");
        }

        if(!campistaehExistentePorCpf && !funcionarioEhExistentePorCpf) {
            throw new NotFoundException("O cpf [" + input.cpf() + "] não existe em nenhum cadastro de pessoa!");
        }

        if(campistaehExistentePorCpf) {
            CampistaEntityDomain campistaEncontrado = this.campistaGateway.buscarCampistaPorCpf(input.cpf());
            if(!Objects.equals(acampamentoEncontrado.codigoRegistro(), campistaEncontrado.codigoRegistro())) throw new EntityFoundException("O cpf [" + input.cpf() + "] não pertence ao acampamento com id [" + input.idAcampamento() + "]!");
            objetoDeRetorno = new CampistaBasicoDTO(campistaEncontrado);
        }

        if(funcionarioEhExistentePorCpf) {
            FuncionarioEntityDomain funcionarioEncontrado = this.funcionarioGateway.buscarFuncionarioPorCpf(input.cpf());
            if(!Objects.equals(acampamentoEncontrado.codigoRegistro(), funcionarioEncontrado.codigoRegistro())) throw new EntityFoundException("O cpf [" + input.cpf() + "] não pertence ao acampamento com id [" + input.idAcampamento() + "]!");
            objetoDeRetorno = new FuncionarioBasicoDTO(funcionarioEncontrado);
        }

        return new MethodResponse<>(200, "O cpf [" + input + "] pertence a um funcionario!", objetoDeRetorno);
    }
}
