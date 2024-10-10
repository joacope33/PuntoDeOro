package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Pareja;

@Repository
public interface ParejaRepository extends JpaRepository<Pareja, Long>{
    
}
