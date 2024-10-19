package com.Unsada.Web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Permitir acceso a recursos estáticos
                .requestMatchers("/login", "/index", "/forgetPassword", "/register").permitAll()  // Permitir acceso a las páginas de login y registro
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")  // Ruta de la página de login
                .defaultSuccessUrl("/startSuccessful", true)  // Redirigir a /index después de un inicio de sesión exitoso
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/index")  // Redirigir a /index después de cerrar sesión
                .permitAll();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
