package com.Unsada.Web.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Usuario;

public interface UsuarioService extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioRegisterDTO registerDTO);


     
}
