package com.Unsada.Web.service;

import java.util.List;

import com.Unsada.Web.model.Torneo;

public interface TorneoService  {
    public List<Torneo> obtenerTodosLosTorneos();
    public Torneo findById(Long id);
    public Torneo guardarTorneo(Torneo torneo);
}
