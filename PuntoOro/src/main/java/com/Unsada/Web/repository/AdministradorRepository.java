package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Administrador;
import com.Unsada.Web.model.Usuario;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
    boolean existsByUsuario(Usuario usuario);

}
