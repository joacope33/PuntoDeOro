package com.Unsada.Web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(Usuario usuario) {
        // Verifica si el correo electrónico ya está registrado
        if (usuarioRepository.existsByMail(usuario.getMail())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        // Encriptar la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        // Guarda el usuario en la base de datos
        usuarioRepository.save(usuario);
    }


}
