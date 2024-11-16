package com.Unsada.Web.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Unsada.Web.dto.UsuarioDTO;
import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Usuario;


// En este se usa UserDetailsService porque es para Spring Security
public interface UsuarioService extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioRegisterDTO registerDTO);
    public Usuario guardarUsuario(UsuarioDTO registerDTO);
    public Usuario findByEmail(String email);
    public UsuarioRegisterDTO convertirUsuarioADTO(Usuario usuario);
    public void actualizarUsuario(String email, UsuarioRegisterDTO registerDTO);
    public List<Usuario> obtenerTodosLosUsuarios();
    public void borrarUsuarioPorEmail(String email);
    public void actualizarUsuarioDesdeDTO(UsuarioDTO usuarioDTO);
     
}
