package com.Unsada.Web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.TorneoDTO;
import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.repository.TorneoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TorneoServiceImpl implements  TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;
    
    @Override
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
    
    @Override
    public void actualizarTorneoDesdeDTO(TorneoDTO torneoDTO) {
        // Busca el jugador existente en la base de datos
        Torneo torneo = torneoRepository.findById(torneoDTO.getId())
                .orElseThrow(() -> new RuntimeException(torneoDTO.getId() + "No se encontró el torneo con ese Id: "));

        if (torneo != null) {
            // Actualiza los campos con los valores del DTO
            torneo.setId(torneoDTO.getId());
            torneo.setFechaInicio(torneoDTO.getFechaInicio());
            torneo.setFechaFin(torneoDTO.getFechaFin());    
            torneo.setFormato(torneoDTO.getFormato());        
            torneo.setCategoria(torneoDTO.getCategoria());
            torneo.setEstado(torneoDTO.getEstado());
            // Guarda la entidad actualizada
            torneoRepository.save(torneo);
        } else {
        throw new EntityNotFoundException("Torneo no encontrado con id: " + torneoDTO.getId());
        }
    }
    
}
