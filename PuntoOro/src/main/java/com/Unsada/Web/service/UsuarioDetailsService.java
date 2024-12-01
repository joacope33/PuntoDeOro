package com.Unsada.Web.service;

import java.util.ArrayList;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.Usuario;

import com.Unsada.Web.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario!=null) {
                return new org.springframework.security.core.userdetails.User(usuario.getNombreCompleto(), usuario.getContrasena(), new ArrayList<>());
            }else{
                 new UsernameNotFoundException("Usuario no encontrado");
            }
                        return null;
    
    }

    
}