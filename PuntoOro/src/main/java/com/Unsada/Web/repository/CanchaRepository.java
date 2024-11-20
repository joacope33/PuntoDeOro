package com.Unsada.Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Cancha;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long>{
    public List<Cancha> findAll();
}
