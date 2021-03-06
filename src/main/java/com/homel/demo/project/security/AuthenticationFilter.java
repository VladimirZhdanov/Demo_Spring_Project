package com.homel.demo.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homel.demo.project.SpringApplicationContext;
import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.rest.LoginUserRequest;
import com.homel.demo.project.service.UserService;
import com.homel.demo.project.utils.CommonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.homel.demo.project.security.Constants.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginUserRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginUserRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        String userName = ((UserPrincipal) authResult.getPrincipal()).getUsername();

        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(CommonUtils.getProperty(EXPIRATION_TIME))))
                .signWith(SignatureAlgorithm.HS512, CommonUtils.getProperty(TOKEN_SECRET))
                .compact();

        UserService userService = SpringApplicationContext.getBean("userServiceImpl", UserService.class);
        UserDTO user = userService.getUser(userName);

        response.addHeader(CommonUtils.getProperty(HEADER_STRING), CommonUtils.getProperty(TOKEN_PREFIX) + token);
        response.addHeader("User id", String.valueOf(user.getId()));
    }
}
