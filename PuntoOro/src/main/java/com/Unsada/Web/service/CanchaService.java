package com.Unsada.Web.service;

import java.util.List;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;


public interface CanchaService {
    public Cancha guardarCancha(CanchaDTO canchaDTO);
    public void actualizarCancha(CanchaDTO canchaDTO);
    public List<Turno> mostrarTurnos(CanchaDTO cancha);
    public List<Cancha> obtenerTodasLasCanchas();
    public void actualizarCanchaDesdeDTO(CanchaDTO canchaDTO);
    public void actualizarCancha(Cancha cancha);
    public Cancha findById(Long id);
    public List<Cancha> obtenerTodasLasCanchasOrdenadas();
}
