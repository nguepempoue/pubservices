package com.example.pubservices.securityConfig.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.pubservices.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtUsernameAndPasswordAuthentificationFilter extends UsernamePasswordAuthenticationFilter{
    @Autowired
    private AuthenticationManager authenticationManager;

    // @Autowired
    // private JwtConfig JwtConfig; 


    public JwtUsernameAndPasswordAuthentificationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //vérification des identifications de l'utilisateur et l'authentifier
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                User user = null;
                try{
                    user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }
  
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                String key = "thisismaykeytestthisismaykeytestthisismaykeytestthisismaykeytestthisismaykeytest";
                String token =  Jwts.builder()
                .setSubject(authResult.getName())
                .claim("roles", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
                
                response.addHeader("Authorization", "Bearer " + token);
    }
}