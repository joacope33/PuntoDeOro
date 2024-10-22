package com.Unsada.Web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Unsada.Web.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private CustomUserDetailsService userDetailsService; // Inyectar tu servicio personalizado

    @SuppressWarnings({ "removal"})
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/login", "/index", "/forgetPassword", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/startSuccessful", true)
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/index")
                .permitAll();

        return http.build();
    }

    @Bean
    @Lazy
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar BCrypt para encriptar contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
            .userDetailsService(userDetailsService) // Tu servicio que carga usuarios
            .passwordEncoder(passwordEncoder()); // Aquí usamos el PasswordEncoder
        return authenticationManagerBuilder.build();
    }


    
}
