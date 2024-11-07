package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.AuditoriaLogin;

@Repository
public interface AuditoriaLoginRepository extends JpaRepository<AuditoriaLogin, Long>{
    
}
