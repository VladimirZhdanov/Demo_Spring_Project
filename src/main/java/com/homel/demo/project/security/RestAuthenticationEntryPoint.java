package com.homel.demo.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homel.demo.project.error.DataErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        DataErrorResponse dataErrorResponse = new DataErrorResponse();
        dataErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataErrorResponse.setMessage(e.getMessage());
        dataErrorResponse.setTimeStamp(System.currentTimeMillis());

        response.setStatus(response.SC_UNAUTHORIZED);
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        response.getOutputStream().write(objectMapper.writeValueAsString(dataErrorResponse).getBytes(StandardCharsets.UTF_8));
    }
}