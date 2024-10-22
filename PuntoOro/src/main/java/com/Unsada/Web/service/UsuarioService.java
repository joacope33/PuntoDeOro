package com.Unsada.Web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(Usuario usuario) {
        // Verifica si el correo electrónico ya está registrado
        if (usuarioRepository.existsByMail(usuario.getMail())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }
        // Guarda el usuario en la base de datos
        usuarioRepository.save(usuario);
    }


}
