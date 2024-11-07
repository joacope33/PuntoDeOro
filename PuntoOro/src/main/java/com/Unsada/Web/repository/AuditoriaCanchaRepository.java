package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.AuditoriaCancha;

@Repository
public interface AuditoriaCanchaRepository extends JpaRepository<AuditoriaCancha, Long>{
    
}
