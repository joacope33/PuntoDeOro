package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.JugadorTurno;

@Repository
public interface JugadorTurnoRepository extends JpaRepository<JugadorTurno, Long> {
    // Métodos personalizados si es necesario
}