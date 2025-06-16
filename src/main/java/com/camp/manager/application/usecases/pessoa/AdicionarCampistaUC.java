package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.*;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.*;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.domain.exception.custom.LimitOverflowException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.pessoa.CriarCampistaRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarCampistaUC implements UseCase<CriarCampistaRequest, MethodResponse<Void>>{

    private final CampistaGateway campistaGateway;
    private final AcampamentoGateway acampamentoGateway;
    private final PasswordEncoderAdapter passwordEncoderAdapter;
    private final UsuarioGateway usuarioGateway;
    private final MedicamentoGateway medicamentoGateway;
    private final PessoaMedicamentoGateway pessoaMedicamentoGateway;

    @Autowired
    public AdicionarCampistaUC(CampistaGateway campistaGateway,
                               AcampamentoGateway acampamentoGateway,
                               PasswordEncoderAdapter passwordEncoderAdapter,
                               UsuarioGateway usuarioGateway,
                               MedicamentoGateway medicamentoGateway,
                               PessoaMedicamentoGateway pessoaMedicamentoGateway) {
        this.campistaGateway = campistaGateway;
        this.acampamentoGateway = acampamentoGateway;
        this.passwordEncoderAdapter = passwordEncoderAdapter;
        this.usuarioGateway = usuarioGateway;
        this.medicamentoGateway = medicamentoGateway;
        this.pessoaMedicamentoGateway = pessoaMedicamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarCampistaRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorCodigoRegistro(input.getCodigoRegistro());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com código de registro [" + input.getCodigoRegistro() + "] não existe!");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorCodigoRegistro(input.getCodigoRegistro());
        this.validarQuantidadeDeCampistas(acampamentoEncontrado);

        boolean campistaEhExistente = this.campistaGateway.campistaEhExistentePorCpf(input.getPessoa().cpf());
        if(campistaEhExistente) throw new EntityNotFoundException("Já existe um campista cadastrado com o CPF [" + input.getPessoa().cpf() + "]!");

        CampistaEntityDomain campistaCriado = this.converterRequestParaDomain(input, acampamentoEncontrado);

        this.inserirMedicamentos(input, campistaCriado);

        this.campistaGateway.inserirCampista(campistaCriado);
        this.inserirCampistaParaLogin(campistaCriado);

        return new MethodResponse<>(201, "Campista adicionado com sucesso!", null);
    }

    private void validarQuantidadeDeCampistas(AcampamentoEntityDomain acampamentoEncontrado) {
        long quantidadeDeCampistasAtuais = acampamentoEncontrado
                .equipesDoAcampamento()
                .stream()
                .filter(equipe -> {
                    TipoEquipe tipo = TipoEquipe.fromDescricao(equipe.tipoEquipe());
                    return TipoEquipe.CAMPISTA.equals(tipo);
                })
                .mapToLong(equipe -> this.campistaGateway
                        .buscarTodosOsCampistasComBaseNaEquipe(equipe.id())
                        .size())
                .sum();
        if (acampamentoEncontrado.limiteFuncionario() <= quantidadeDeCampistasAtuais) {
            throw new LimitOverflowException("O acampamento com código de registro ["
                    + acampamentoEncontrado.codigoRegistro()
                    + "] já atingiu o limite de funcionários permitidos!");
        }
    }
    private CampistaEntityDomain converterRequestParaDomain(CriarCampistaRequest input, AcampamentoEntityDomain acampamentoEncontrado) {
        return new CampistaEntityDomain(
                null,
                input.getUsaMedicamento(),
                input.getTemAlergia(),
                input.getAlergias(),
                input.getCodigoRegistro(),
                input.getJaFezAcampamento(),
                input.getAcampamentosFeitos(),
                input.getTemBarraca(),
                new CamisetaEntityDomain(null, input.getTamanhoCamisa(), acampamentoEncontrado.tema()),
                new PessoaEntityDomain(
                        null,
                        input.getPessoa().nomeCompleto().split(" ", 2)[0],
                        input.getPessoa().nomeCompleto().split(" ", 2)[1],
                        input.getPessoa().cpf(),
                        LocalDateConverterAPP.converterStringParaLocalDate(input.getPessoa().dataNascimento()),
                        input.getPessoa().telefone(),
                        input.getPessoa().sexo(),
                        input.getPessoa().peso(),
                        input.getPessoa().altura(),
                        input.getPessoa().alimentoPredileto(),
                        input.getPessoa().foiBatizado(),
                        input.getPessoa().temPrimeiraComunhao(),
                        new EnderecoEntityDomain(
                                null,
                                input.getPessoa().endereco().cep(),
                                input.getPessoa().endereco().rua(),
                                Long.valueOf(input.getPessoa().endereco().numero()),
                                input.getPessoa().endereco().cidade(),
                                input.getPessoa().endereco().bairro(),
                                input.getPessoa().endereco().pontoReferencia()
                        ),
                        new FamiliarEntityDomain(
                                null,
                                input.getPessoa().familiar().nome(),
                                input.getPessoa().familiar().parentesco(),
                                input.getPessoa().familiar().telefone(),
                                new EnderecoEntityDomain(
                                        null,
                                        input.getPessoa().familiar().endereco().cep(),
                                        input.getPessoa().familiar().endereco().rua(),
                                        Long.valueOf(input.getPessoa().familiar().endereco().numero()),
                                        input.getPessoa().familiar().endereco().cidade(),
                                        input.getPessoa().familiar().endereco().bairro(),
                                        input.getPessoa().familiar().endereco().pontoReferencia()
                                )
                        )
                ),
                null
        );
    }
    private void inserirCampistaParaLogin(CampistaEntityDomain campistaEntityDomain){
        String senhaPadrao = campistaEntityDomain.pessoa().cpf().substring(0, 3);

        String senhaHash = this.passwordEncoderAdapter.encode(senhaPadrao);

        UserEntityDomain usuarioCriado = new UserEntityDomain(null,
                campistaEntityDomain.pessoa().nome(),
                campistaEntityDomain.pessoa().telefone(),
                senhaHash,
                "CAMPISTA");
        this.usuarioGateway.salvarNovoUsuario(usuarioCriado);
    }
    private void inserirMedicamentos(CriarCampistaRequest input, CampistaEntityDomain campistaEncontrado){
        input.getIdsDeMedicamentos().forEach(medicamentoId -> {
            boolean medicamentoEhExistente = this.medicamentoGateway.medicamentoEhExistentePorId(medicamentoId);
            if(!medicamentoEhExistente) throw new NotFoundException("Medicamento com id [" + medicamentoId + "] não encontrado.");

            MedicamentoEntityDomain medicamentoEncontrado = this.medicamentoGateway.buscarMedicamentoPorId(medicamentoId);
            this.pessoaMedicamentoGateway.salvarPessoasMedicamento(campistaEncontrado.pessoa(), medicamentoEncontrado);
        });
    }
}
