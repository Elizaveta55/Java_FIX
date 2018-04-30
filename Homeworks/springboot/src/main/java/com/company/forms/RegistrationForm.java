package com.company.forms;

import lombok.Data;

@Data
public class RegistrationForm {
    private String login;
    private String password;
    private String checkPassword;
}
