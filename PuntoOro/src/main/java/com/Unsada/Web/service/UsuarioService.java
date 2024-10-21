package com.Unsada.Web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.UsuarioRepository;

public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio para acceso a datos
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Usar BCryptPasswordEncoder

    public void registrarUsuario(String nombreCompleto, String correoElectronico, String nombreUsuario, String contrasena) {
        
        // Por ejemplo, si estás usando BCrypt:
        String contrasenaEncriptada = passwordEncoder.encode(contrasena);
        
        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setMail(correoElectronico);
        nuevoUsuario.setTelefono(nombreUsuario);
        nuevoUsuario.setContrasena(contrasenaEncriptada); // La contraseña ya debería estar encriptada
        nuevoUsuario.setEstado(1);
        usuarioRepository.save(nuevoUsuario); // Guarda el nuevo usuario
    }


}
