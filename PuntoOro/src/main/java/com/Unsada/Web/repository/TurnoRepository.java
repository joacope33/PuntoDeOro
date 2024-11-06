package com.Unsada.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long>{
    public List<Turno> findAllByDia(LocalDate dia);
    public List<Turno> findAllByCancha(Cancha cancha);
}
