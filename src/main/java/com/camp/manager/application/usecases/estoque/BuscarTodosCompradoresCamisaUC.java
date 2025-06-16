package com.camp.manager.application.usecases.estoque;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.gateway.EstoqueGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.estoque.CampistaUltraBasicoDTO;
import com.camp.manager.infra.http.dto.estoque.CompradoresCamisetaDTO;
import com.camp.manager.infra.http.dto.estoque.FuncionarioUltraBasicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BuscarTodosCompradoresCamisaUC implements UseCase<Long, MethodResponse<CompradoresCamisetaDTO>> {

    private final EstoqueGateway estoqueGateway;
    private final FuncionarioGateway funcionarioGateway;
    private final CampistaGateway campistaGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarTodosCompradoresCamisaUC(EstoqueGateway estoqueGateway,
                                          FuncionarioGateway funcionarioGateway,
                                          CampistaGateway campistaGateway,
                                          AcampamentoGateway acampamentoGateway) {
        this.estoqueGateway = estoqueGateway;
        this.funcionarioGateway = funcionarioGateway;
        this.campistaGateway = campistaGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<CompradoresCamisetaDTO> execute(Long input) {
        boolean acampamentoExiste = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!acampamentoExiste) throw new NotFoundException("Acampamento com id [" + input + "] não encontrado.");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input);

        BigDecimal valorCamisa = acampamentoEncontrado.tema().precoCamiseta();

        List<FuncionarioEntityDomain> funcionariosCompradores = this.funcionarioGateway.buscarFuncionariosCompradoresCamisa(acampamentoEncontrado.codigoRegistro());
        List<CampistaEntityDomain> campistasCompradores = this.campistaGateway.buscarCampistasCompradoresCamisa(acampamentoEncontrado.codigoRegistro());

        long totalCompradores = funcionariosCompradores.size() + campistasCompradores.size();
        long totalFuncionarios = funcionariosCompradores.size();
        long totalCampistas = campistasCompradores.size();

        Double valorArrecadado = totalCompradores * valorCamisa.doubleValue();

        return new MethodResponse<>(200, "Compradores de camisetas encontrados com sucesso!",
                new CompradoresCamisetaDTO(valorArrecadado, totalCompradores,totalCampistas,totalFuncionarios, CampistaUltraBasicoDTO.converter(campistasCompradores), FuncionarioUltraBasicoDTO.converter(funcionariosCompradores)));
    }
}
