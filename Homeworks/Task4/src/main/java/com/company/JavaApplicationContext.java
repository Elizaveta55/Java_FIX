package com.company;

import com.company.repositories.GoodRepositoryJDBCImpl;
import com.company.repositories.UserRepositoryJdbcTemplateImpl;
import com.company.services.GoodService;
import com.company.services.LoginService;
import com.company.services.RegistrationService;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaApplicationContext {

    //for JDBC Template

    @Bean
    public LoginService loginService(){
        return new LoginService(userRepositoryJdbcTemplate());
    }

    @Bean
    public UserRepositoryJdbcTemplateImpl userRepositoryJdbcTemplate(){
        return new UserRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public RegistrationService registrationService(){
        return new RegistrationService(userRepositoryJdbcTemplate());
    }

    @Bean
    public DriverManagerDataSource dataSource(){
        return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/listOfGoods", "postgres", "HighLow11");
    }
}
