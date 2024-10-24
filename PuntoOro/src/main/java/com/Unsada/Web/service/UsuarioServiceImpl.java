package com.Unsada.Web.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Rol;
import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(UsuarioRegisterDTO registerDTO) {
        Usuario usuario = new Usuario(registerDTO.getNombreCompleto(), registerDTO.getEmail(), registerDTO.getTelefono(), registerDTO.getContrasena(), registerDTO.getEstado(), Arrays.asList(new Rol("USER")));
        return usuarioRepository.save(usuario);
    }
    
}
