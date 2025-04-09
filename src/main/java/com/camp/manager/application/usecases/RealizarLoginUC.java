package com.camp.manager.application.usecases;

import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.domain.exception.custom.PasswordInvalidException;
import com.camp.manager.infra.http.request.user.LoginUserRequest;
import com.camp.manager.application.gateway.TokenEncoderAdapter;
import com.camp.manager.application.gateway.PasswordEncoderAdapter;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.domain.entity.UserEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealizarLoginUC {
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

    @Transactional
    public String execute(LoginUserRequest request) {
        boolean usuarioEhExistente = this.usuarioGateway.existsUserByLogin(request.login());
        if(!usuarioEhExistente) {throw new NotFoundException("Usuário não cadastrado!!");}

        UserEntityDomain usuarioBuscado = this.usuarioGateway.findUserByLogin(request.login())
                .orElseThrow(NotFoundException::new);

        boolean senhaEhValida = this.passwordEncoderAdapter.compare(request.password(), usuarioBuscado.password());
        if(!senhaEhValida) {throw new PasswordInvalidException();}

        return this.tokenEncoderAdapter.gerar(usuarioBuscado);
    }
}
