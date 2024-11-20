package com.Unsada.Web.service;

import java.util.List;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;

public interface  CanchaService {
    public Cancha guardarCancha(CanchaDTO canchaDTO);
    public Cancha obtenerCanchabyId(Long id);
    public List<Cancha> getAll();
}
