package com.bankIndia.bankindia_secure.filter;

import com.bankIndia.bankindia_secure.constant.BnakIndiaConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTTokenGenerator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication obj = SecurityContextHolder.getContext().getAuthentication();
        Environment env = getEnvironment();
        String secret = env.getProperty(BnakIndiaConstant.JWT_SECRET_KEY,BnakIndiaConstant.JWT_SECRET_DEFAULT_VALUE);
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    String jwt=    Jwts.builder().setIssuer("Eazy Bank").setSubject("JWT Token")
                .claim("username" , obj.getName())
                .claim("authorities" , obj.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date((new Date()).getTime() + 30000000))
        .signWith(secretKey).compact();

    response.addHeader("Authorization",jwt);
    filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return !request.getServletPath().equals("/user");

    }
}
