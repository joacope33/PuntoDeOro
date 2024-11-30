package com.Unsada.Web.service;

import java.util.List;

import com.Unsada.Web.dto.TorneoDTO;
import com.Unsada.Web.model.Torneo;

public interface TorneoService  {
    public List<Torneo> obtenerTodosLosTorneos();
    public List<Torneo> obtenerTodosLosTorneosPorFecha();
    public Torneo findById(Long id);
    public Torneo guardarTorneo(Torneo torneo);
    public void actualizarTorneoDesdeDTO(TorneoDTO torneoDTO);
    public void borrarTorneoPorId(Long id);
}
