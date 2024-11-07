package com.Unsada.Web.service;

import java.time.LocalDate;
import java.util.List;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Turno;



public interface TurnoService {
    public Turno guardarTurno(TurnoDTO turnoDTO);
    public List<Turno> obtenerTurnoByIdCancha(long cancha);
    public Turno findById(Long idTurno);
    public List<Turno> obtenerTurnoByDia(LocalDate dia);
}
