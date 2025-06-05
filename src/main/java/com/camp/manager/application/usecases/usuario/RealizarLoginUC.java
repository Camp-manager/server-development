package com.camp.manager.application.usecases.usuario;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.domain.exception.custom.PasswordInvalidException;
import com.camp.manager.infra.http.dto.TokenResponseDTO;
import com.camp.manager.infra.http.request.user.LogarUsuarioRequest;
import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.application.gateway.PasswordEncoderAdapter;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealizarLoginUC implements UseCase<LogarUsuarioRequest, MethodResponse<TokenResponseDTO>> {
    private final UsuarioGateway usuarioGateway;
    private final TokenEncoderAdapter tokenEncoderAdapter;
    private final PasswordEncoderAdapter passwordEncoderAdapter;

    @Autowired
    public RealizarLoginUC(UsuarioGateway usuarioGateway,
                           TokenEncoderAdapter tokenEncoderAdapter,
                           PasswordEncoderAdapter passwordEncoderAdapter) {
        this.usuarioGateway = usuarioGateway;
        this.tokenEncoderAdapter = tokenEncoderAdapter;
        this.passwordEncoderAdapter = passwordEncoderAdapter;
    }

    @Override
    @Transactional
    public MethodResponse<TokenResponseDTO> execute(LogarUsuarioRequest request) {
        boolean usuarioEhExistente = this.usuarioGateway.existsUserByLogin(request.login());
        if(!usuarioEhExistente) {throw new NotFoundException("Usuário não cadastrado!!");}

        UserEntityDomain usuarioBuscado = this.usuarioGateway.findUserByLogin(request.login())
                .orElseThrow(NotFoundException::new);

        boolean senhaEhValida = this.passwordEncoderAdapter.compare(request.password(), usuarioBuscado.password());
        if(!senhaEhValida) {throw new PasswordInvalidException();}

        String tokenGerado = this.tokenEncoderAdapter.gerar(usuarioBuscado);

        return new MethodResponse<>(200, "Login realizado com sucesso!", new TokenResponseDTO(tokenGerado));
    }
}
