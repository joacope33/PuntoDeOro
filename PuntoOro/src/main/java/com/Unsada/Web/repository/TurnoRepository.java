package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
    
}
