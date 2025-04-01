package com.camp.manager.utils.config.bean;

import com.camp.manager.application.gateway.CreateUserGateway;
import com.camp.manager.application.usecases.CreateUserUC;
import com.camp.manager.infra.http.controllers.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfiguration {

    @Bean
    public CreateUserUC createUserUC(CreateUserGateway createUserGateway) {
        return new CreateUserUC(createUserGateway);
    }

    @Bean
    public UserController userController(CreateUserUC createUserUC) {
        return new UserController(createUserUC);
    }
}
