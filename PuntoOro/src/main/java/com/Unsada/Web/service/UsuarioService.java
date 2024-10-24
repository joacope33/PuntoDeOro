package com.Unsada.Web.service;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Usuario;

public interface UsuarioService {

    public Usuario guardarUsuario(UsuarioRegisterDTO registerDTO);

}
