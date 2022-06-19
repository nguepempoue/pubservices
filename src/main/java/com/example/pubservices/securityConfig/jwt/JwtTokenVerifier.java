package com.example.pubservices.securityConfig.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifier extends OncePerRequestFilter{
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
              
                response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
                

        String key = "thisismaykeytestthisismaykeytestthisismaykeytestthisismaykeytestthisismaykeytest";
        String authorizationHeader = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.replace("Bearer ", "");
        try {
            Claims body = (Claims) Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .build()
                    .parse(token).getBody();
            String username = body.getSubject();
            ArrayList<Map<String, String>> roles =  (ArrayList<Map<String, String>>) body.get("roles");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r.get("authority")));
            });

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be truest", token));
        }
        filterChain.doFilter(request, response);
        
    }
}
