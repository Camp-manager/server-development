package com.camp.manager.utils.config.bean;

import com.camp.manager.application.gateway.CreateUserGateway;
import com.camp.manager.application.gateway.ValidateUserGateway;
import com.camp.manager.application.usecases.CreateUserUC;
import com.camp.manager.application.usecases.ValidateUserUC;
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
    public ValidateUserUC validateUserUC(ValidateUserGateway validateUserGateway) {
        return new ValidateUserUC(validateUserGateway);
    }

    @Bean
    public UserController userController(CreateUserUC createUserUC, ValidateUserUC validateUserUC) {
        return new UserController(createUserUC, validateUserUC);
    }
}
