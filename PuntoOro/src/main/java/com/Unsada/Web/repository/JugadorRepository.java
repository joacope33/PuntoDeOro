package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{
    
}