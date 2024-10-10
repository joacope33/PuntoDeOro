package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Unsada.Web.model.Torneo;

public interface TorneoRepository extends JpaRepository<Torneo, Long>{
    
}
