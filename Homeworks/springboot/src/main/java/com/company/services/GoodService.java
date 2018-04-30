package com.company.services;

import com.company.forms.GoodForm;
import org.springframework.security.core.Authentication;

public interface GoodService {
    boolean addDelUpd(GoodForm form, Authentication authentication);
}
