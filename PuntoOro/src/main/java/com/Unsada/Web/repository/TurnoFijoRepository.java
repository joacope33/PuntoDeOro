package com.Unsada.Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.TurnosFijos;

@Repository
public interface TurnoFijoRepository extends JpaRepository<TurnosFijos, Long> {
    public List<TurnosFijos> findAll();  // Método estándar para obtener todos
}
