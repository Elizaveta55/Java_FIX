package com.company.Services;

import com.company.forms.LoginForm;
import com.company.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
