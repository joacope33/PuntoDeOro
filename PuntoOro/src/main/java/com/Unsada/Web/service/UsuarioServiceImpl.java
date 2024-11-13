package com.Unsada.Web.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Rol;
import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    


    public UsuarioServiceImpl(BCryptPasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario guardarUsuario(UsuarioRegisterDTO registerDTO) {
        Usuario usuario = new Usuario( registerDTO.getNombreCompleto(), 
                                       registerDTO.getEmail(), 
                                       registerDTO.getTelefono(), 
                                       passwordEncoder.encode(registerDTO.getContrasena()), 
                                       registerDTO.getEstado(), 
                                       Arrays.asList(new Rol("USER")));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario o contraseña inválidos");
        }
        return new User(usuario.getEmail(), usuario.getContrasena(), mapearAutoridadesRoles(usuario.getRoles()));
    }
    

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    @Override
    public UsuarioRegisterDTO convertirUsuarioADTO(Usuario usuario) {
        // Crear un nuevo DTO de Usuario
        UsuarioRegisterDTO usuarioDTO = new UsuarioRegisterDTO();

        // Mapear los atributos de la entidad Usuario al DTO
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setRole(usuario.getRole()); // Suponiendo que Role es un enum
        usuarioDTO.setNombreCompleto(usuario.getNombreCompleto());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setContrasena(usuario.getContrasena());
        usuarioDTO.setEstado(usuario.getEstado());

        // Retornar el DTO con los datos del usuario
        return usuarioDTO;
    }

    @Override
    public void actualizarUsuario(String email, UsuarioRegisterDTO registerDTO) {
        // Busca el jugador existente en la base de datos
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null) {
            // Actualiza los campos con los valores del DTO

            usuario.setNombreCompleto(registerDTO.getNombreCompleto());
            usuario.setEmail(registerDTO.getEmail());
            usuario.setTelefono(registerDTO.getTelefono());
            usuario.setEstado(registerDTO.getEstado());

            // Guarda la entidad actualizada
            usuarioRepository.save(usuario);
        } else {
        throw new EntityNotFoundException("Jugador no encontrado con dni: " + registerDTO.getId());
        }
    }

}
