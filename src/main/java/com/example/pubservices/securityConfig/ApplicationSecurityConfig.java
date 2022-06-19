package com.example.pubservices.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.pubservices.securityConfig.jwt.JwtTokenVerifier;
import com.example.pubservices.securityConfig.jwt.JwtUsernameAndPasswordAuthentificationFilter;

@Configuration
@EnableWebSecurity 
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**", "/user/register").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtUsernameAndPasswordAuthentificationFilter(authenticationManager()));
        http.addFilterBefore(new JwtTokenVerifier(), UsernamePasswordAuthenticationFilter.class);
    }
}
