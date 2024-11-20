package com.Unsada.Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Cancha;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long>{
<<<<<<< HEAD
    List<Cancha> findAllByOrderByIdAsc(); // Para mantener el orden por ID
=======
    public List<Cancha> findAll();
>>>>>>> c44d3851b79841092c3888afa743a7aeda7576bb
}
