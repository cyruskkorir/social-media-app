package com.cyrus.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .authorizeHttpRequests(requests -> requests
        .requestMatchers("/", "/login", "/register", "/dashboard").permitAll()
        .requestMatchers("/admin/").hasRole("ADMIN").anyRequest().authenticated()
        )
        .formLogin((form) -> form
        .loginPage("/login")
        .defaultSuccessUrl("/posts")
        .permitAll()
        )
        .logout(logout -> logout.permitAll());

        return httpSecurity.build();
    }
}
