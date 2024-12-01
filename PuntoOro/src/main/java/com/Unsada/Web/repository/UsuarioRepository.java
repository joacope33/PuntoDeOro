package com.Unsada.Web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByEmail(String email);

}
