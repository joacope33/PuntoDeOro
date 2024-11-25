package com.Unsada.Web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.repository.TorneoRepository;

@Service
public class TorneoServiceImpl implements  TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;
    
    public List<Torneo> obtenerTodosLosTorneosPorFecha() {
        return torneoRepository.findAllByOrderByFechaInicioDesc();
    }
    
    @Override
    public Torneo findById(Long id) {
        return torneoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(id + "No se encontró el torneo con ese Id: "));

    }
    
    @Override
    public Torneo guardarTorneo(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    @Override
    public List<Torneo> obtenerTodosLosTorneos() {
        return torneoRepository.findAll();
    }
    
    
}
