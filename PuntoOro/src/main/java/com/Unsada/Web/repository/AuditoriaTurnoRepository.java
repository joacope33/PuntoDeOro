package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.AuditoriaTurno;

@Repository
public interface AuditoriaTurnoRepository extends JpaRepository<AuditoriaTurno, Long>{
    
}

