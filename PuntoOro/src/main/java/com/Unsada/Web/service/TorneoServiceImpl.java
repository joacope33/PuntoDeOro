package com.Unsada.Web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.TorneoDTO;
import com.Unsada.Web.model.Categoria;
import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.repository.CategoriaRepository;
import com.Unsada.Web.repository.TorneoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TorneoServiceImpl implements  TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate; // Para ejecutar consultas SQL nativas
    
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
             // Buscar la categoría por ID en el DTO y asignarla al torneo
            Categoria categoria = categoriaRepository.findById(torneoDTO.getIdCategoria())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con el ID: " + torneoDTO.getIdCategoria()));
            torneo.setCategoria(categoria);
            torneo.setEstado(torneoDTO.getEstado());
            // Guarda la entidad actualizada
            torneoRepository.save(torneo);
        } else {
        throw new EntityNotFoundException("Torneo no encontrado con id: " + torneoDTO.getId());
        }
    }

    @Override
    public void borrarTorneoPorId(Long id) {
        // Buscar el torneo por ID
        Torneo torneo = torneoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el torneo con esa Id: " + id));

        // Eliminar el torneo
        torneoRepository.deleteById(torneo.getId());

        // Reiniciar la secuencia del ID del torneo
        reiniciarSecuencia();
    }

    // Reiniciar la secuencia para que el próximo ID sea el siguiente al último eliminado
    private void reiniciarSecuencia() {
        String secuencia = "torneos_idtorneo_seq"; // Nombre de la secuencia en la base de datos
        String sql = String.format(
            "SELECT setval('%s', COALESCE((SELECT MAX(idtorneo) FROM torneos), 0) + 1, false)", 
            secuencia
        );
        jdbcTemplate.execute(sql);
    }

}
