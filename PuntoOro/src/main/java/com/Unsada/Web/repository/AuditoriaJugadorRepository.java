package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.AuditoriaJugador;


@Repository
public interface AuditoriaJugadorRepository extends JpaRepository<AuditoriaJugador, Long>{
    
}

