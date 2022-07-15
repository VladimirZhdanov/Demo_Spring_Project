package com.homel.demo.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionTranslationFilter extends ExceptionTranslationFilter {

    @Autowired
    public CustomExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationEntryPoint);
    }
}
