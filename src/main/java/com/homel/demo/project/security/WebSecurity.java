package com.homel.demo.project.security;

import com.homel.demo.project.repository.UserRepository;
import com.homel.demo.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestAuthenticationEntryPoint authEntryPoint;
    private final UserRepository userRepository;

    @Value("${security.properties.signUpUrl}")
    private String SIGN_UP_URL;
    @Value("${security.properties.h2ConsoleUrl}")
    private String H2_CONSOLE;
    @Value("${security.properties.loginUrl}")
    private String LOGIN_URL;

    @Autowired
    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, RestAuthenticationEntryPoint authEntryPoint, UserRepository userRepository) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authEntryPoint = authEntryPoint;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(authEntryPoint);

        http.csrf().disable().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL)
                .permitAll()
                .antMatchers(H2_CONSOLE)
                .permitAll()
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyAuthority("DELETE_AUTHORITY")
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilterAfter(new AuthorizationFilter(authenticationManager(), userRepository), CustomExceptionTranslationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().authenticationEntryPoint(authEntryPoint);

          http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(LOGIN_URL);
        return authenticationFilter;
    }
}
