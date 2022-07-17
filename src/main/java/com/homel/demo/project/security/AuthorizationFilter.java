package com.homel.demo.project.security;

import com.homel.demo.project.repository.UserRepository;
import com.homel.demo.project.utils.CommonUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.homel.demo.project.security.Constants.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(CommonUtils.getProperty(HEADER_STRING));

        if (token == null || !token.startsWith(CommonUtils.getProperty(TOKEN_PREFIX))) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
        token = token.replace(CommonUtils.getProperty(TOKEN_PREFIX), "");

        try {
            String user = Jwts.parser()
                    .setSigningKey(CommonUtils.getProperty(TOKEN_SECRET))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (user != null) {
                UserPrincipal userPrincipal = new UserPrincipal(userRepository.findByEmail(user));
                return new UsernamePasswordAuthenticationToken(user, null, userPrincipal.getAuthorities());
            }
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        return null;
    }
}
