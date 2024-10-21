package com.Unsada.Web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.AdministradorRepository;
import com.Unsada.Web.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private AdministradorRepository administradorRepository;


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
    
        boolean isAdmin = administradorRepository.existsByUsuario(usuario);

        // Asignar el rol basado en si el usuario es administrador
        String role = isAdmin ? "ROLE_ADMIN" : "ROLE_USER";

        return User.builder()
                   .username(usuario.getMail())
                   .password(usuario.getContrasena())
                   .roles(role)  // Spring automáticamente añade el prefijo "ROLE_"
                   .build();
    }

}
