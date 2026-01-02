package com.supplynext.company_api.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    //THis method filters the requests sent to the application
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken= request.getHeader("Authorizaion");   // getting token from the request header

        if(jwtToken== null || jwtToken.isBlank()){
            // Here we are not setting any authentication and directly calling
            // the parent method to reject the request!!
            filterChain.doFilter(request,response);
            return;
        }

        jwtToken= jwtToken.trim(); // removing whitespaces

        if(! jwtUtil.isTokenValid(jwtToken)){
            filterChain.doFilter(request,response);
            return;
        }

        // setting the authentication here
        Claims claims= jwtUtil.decryptToken(jwtToken);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(claims,null,new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);

    }
}
