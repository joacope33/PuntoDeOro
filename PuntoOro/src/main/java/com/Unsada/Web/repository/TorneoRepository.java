package com.Unsada.Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Torneo;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Long>{
    public List<Torneo> findAllByOrderByFechaInicioDesc();
}
