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
    private UsuarioRepository usuarioRepository; // Repositorio para acceso a datos
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Usar BCryptPasswordEncoder

    @Transactional
    public void registrarUsuario(String nombreCompleto, String correoElectronico, String telefono, String contrasena) {
        
        // Validar si el correo electrónico ya existe
        if (usuarioRepository.existsByMail(correoElectronico)) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        // Por ejemplo, si estás usando BCrypt:
        String contrasenaEncriptada = passwordEncoder.encode(contrasena);
        
        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setMail(correoElectronico);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setContrasena(contrasenaEncriptada); // La contraseña ya debería estar encriptada
        nuevoUsuario.setEstado(1);
        usuarioRepository.save(nuevoUsuario); // Guarda el nuevo usuario
    }


}
