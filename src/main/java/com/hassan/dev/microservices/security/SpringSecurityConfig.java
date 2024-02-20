package com.hassan.dev.microservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //1) All Requests should be authenticated
        httpSecurity.authorizeHttpRequests(
                auth-> auth.anyRequest().authenticated()
        );
        //2) if not authenticated a web page is shown to authenticate
        httpSecurity.httpBasic(Customizer.withDefaults());

        //3) disable CSRF for put and post requests
        httpSecurity.csrf((csrf) -> csrf.disable());
        return httpSecurity.build();
    }
}
