package com.company.Services;

import com.company.forms.GoodForm;
import org.springframework.security.core.Authentication;

public interface GoodService {
    boolean addOrDelGood(Authentication authentication, GoodForm form);
}
