package com.Unsada.Web.service;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;

public interface  CanchaService {
    public Cancha guardarCancha(CanchaDTO canchaDTO);
    public Cancha obtenerCanchabyId(Long id);
}
