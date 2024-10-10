package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Unsada.Web.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
