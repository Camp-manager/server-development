package com.camp.manager.application.usecases.usuario;

import com.camp.manager.application.gateway.PasswordEncoderAdapter;
import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.domain.exception.custom.PasswordInvalidException;
import com.camp.manager.infra.http.request.user.AlterarSenhaUsuarioRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarSenhaUC implements UseCase<AlterarSenhaUsuarioRequest, MethodResponse<Void>> {

    private final UsuarioGateway usuarioGateway;
    private final TokenEncoderAdapter tokenEncoderAdapter;
    private final PasswordEncoderAdapter passwordEncoderAdapter;

    @Autowired
    public AlterarSenhaUC(UsuarioGateway usuarioGateway,
                          TokenEncoderAdapter tokenEncoderAdapter,
                          PasswordEncoderAdapter passwordEncoderAdapter) {
        this.usuarioGateway = usuarioGateway;
        this.tokenEncoderAdapter = tokenEncoderAdapter;
        this.passwordEncoderAdapter = passwordEncoderAdapter;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(AlterarSenhaUsuarioRequest input) {
        boolean usuarioEhExistente = this.usuarioGateway.existsUserByLogin(input.login());
        if(!usuarioEhExistente) {throw new NotFoundException("Usuário não cadastrado!!");}

        UserEntityDomain usuarioBuscado = this.usuarioGateway.findUserByLogin(input.login())
                .orElseThrow(NotFoundException::new);

        boolean senhaEhValida = this.passwordEncoderAdapter.compare(input.senhaAtual(), usuarioBuscado.password());
        if(!senhaEhValida) {throw new PasswordInvalidException();}

        String novaSenhaCriptografada = this.passwordEncoderAdapter.encode(input.novaSenha());

        this.usuarioGateway.salvar( new UserEntityDomain(
                usuarioBuscado.id(),
                usuarioBuscado.username(),
                usuarioBuscado.login(),
                novaSenhaCriptografada,
                usuarioBuscado.role()
        ));

        return new MethodResponse<>(202, "Senha alterada com sucesso!", null);
    }
}
