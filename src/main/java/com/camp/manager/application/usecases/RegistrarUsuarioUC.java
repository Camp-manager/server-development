package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.PasswordEncoderAdapter;
import com.camp.manager.application.gateway.UsuarioGateway;
import com.camp.manager.domain.entity.MethodResponse;
import com.camp.manager.domain.exception.custom.UserFoundException;
import com.camp.manager.infra.http.request.user.CreateUserRequest;
import com.camp.manager.domain.entity.UserEntityDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarUsuarioUC implements UseCase<CreateUserRequest, MethodResponse<Void>> {
    private final UsuarioGateway usuarioGateway;
    private final PasswordEncoderAdapter passwordEncoderAdapter;

    @Autowired
    public RegistrarUsuarioUC(UsuarioGateway usuarioGateway,
                              PasswordEncoderAdapter passwordEncoderAdapter) {
        this.usuarioGateway = usuarioGateway;
        this.passwordEncoderAdapter = passwordEncoderAdapter;
    }

    @Transactional
    public MethodResponse<Void> execute(CreateUserRequest request) {
       boolean usuarioEhExistente = this.usuarioGateway.existsUserByLogin(request.login());
       if (usuarioEhExistente) {throw new UserFoundException("Usuário já cadastrado!!");}

       String senhaCriptografada = this.passwordEncoderAdapter.encode(request.password());

       UserEntityDomain usuarioCadastrado = new UserEntityDomain(request.username(), request.login(), senhaCriptografada, request.roleUser());

       this.usuarioGateway.salvar(usuarioCadastrado);
       return new MethodResponse<>(201L, "Usuário cadastrado com sucesso!", null);
    }
}
