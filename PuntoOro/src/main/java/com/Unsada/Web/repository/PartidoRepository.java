package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{
    
}