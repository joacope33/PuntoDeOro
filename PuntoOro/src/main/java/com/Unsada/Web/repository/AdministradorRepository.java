package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Rol;

@Repository
public interface AdministradorRepository extends JpaRepository<Rol, Long> {
    
   

}
