package com.Unsada.Web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.Unsada.Web.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService; // Inyectar tu servicio personalizado

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/login", "/index", "/forgetPassword", "/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")  // Asegúrate que esto coincide con tu vista de login
                        .defaultSuccessUrl("/index", true) // Redirige al index después de un login exitoso
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true") // Redirige al login tras cerrar sesión
                        .permitAll());

        return http.build();
    }
}
