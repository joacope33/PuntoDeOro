package com.Unsada.Web.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.Rol;
import com.Unsada.Web.repository.AdministradorRepository;


/**
 *  En el servicio solo modificamos los métodos implementados del repository
 *  que vamos a usar.
 */
@Service
public class AdministradorService {

    @Autowired 
    private AdministradorRepository administradorRepository;


    // Encontar administrador por ID
    public Optional<Rol> findById(Long id){
        return administradorRepository.findById(id);
    }

    // Guardar Administrador
    public <S extends Rol> S saveAdministrador(S entity) {
        return administradorRepository.save(entity);
    }
    
}
