package com.Unsada.Web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.model.enums.Role;
import com.Unsada.Web.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner  {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Email del administrador
        String emailAdmin = "admin@puntooro.com";

        // Verifica si ya existe
        if (!usuarioRepository.existsByEmail(emailAdmin)) {
            Usuario admin = new Usuario();
            admin.setNombreCompleto("Administrador General");
            admin.setEmail(emailAdmin);
            admin.setTelefono("123456789");
            admin.setContrasena(new BCryptPasswordEncoder().encode("admin123")); // Contraseña encriptada
            admin.setRole(Role.ADMIN);
            admin.setEstado(1); // Activo

            usuarioRepository.save(admin);
            System.out.println("Usuario administrador creado con éxito.");
        } else {
            System.out.println("El usuario administrador ya existe.");
        }
    }
}
