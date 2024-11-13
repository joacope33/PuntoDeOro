package com.Unsada.Web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Usuario;


// En este se usa UserDetailsService porque es para Spring Security
public interface UsuarioService extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioRegisterDTO registerDTO);
    public Usuario findByEmail(String email);
    public UsuarioRegisterDTO convertirUsuarioADTO(Usuario usuario);
    public void actualizarUsuario(String email, UsuarioRegisterDTO registerDTO);

     
}
