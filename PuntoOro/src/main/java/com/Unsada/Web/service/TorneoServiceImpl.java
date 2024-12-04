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
    private CategoriaService categoriaService;

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
    public Torneo guardarTorneo(TorneoDTO torneoDTO) {
        if (torneoDTO == null) {
            throw new IllegalArgumentException("El torneoDTO no puede ser nulo");
        }

        if (torneoDTO.getIdCategoria() == null) {
            throw new IllegalArgumentException("El ID de la categoría no puede ser nulo");
        }

        Categoria categoria = categoriaRepository.findById(torneoDTO.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + torneoDTO.getIdCategoria()));

        Torneo torneo = new Torneo();
        torneo.setFechaInicio(torneoDTO.getFechaInicio());
        torneo.setFechaFin(torneoDTO.getFechaFin());
        torneo.setFormato(torneoDTO.getFormato());
        torneo.setEstado(torneoDTO.getEstado());
        torneo.setCategoria(categoria);

        return torneoRepository.save(torneo);
    }

    @Override
    public List<Torneo> obtenerTodosLosTorneos() {
        return torneoRepository.findAll();
    }
    
    @Override
    public void actualizarTorneoDesdeDTO(TorneoDTO torneoDTO) {
        if (torneoDTO.getId() == null) {
            throw new IllegalArgumentException("El ID del torneo no puede ser nulo");
        }
        // Busca el torneo en la base de datos
        Torneo torneo = torneoRepository.findById(torneoDTO.getId())
            .orElseThrow(() -> new EntityNotFoundException("Torneo no encontrado con ID: " + torneoDTO.getId()));
        
        // Actualiza los campos del torneo
        torneo.setFechaInicio(torneoDTO.getFechaInicio());
        torneo.setFechaFin(torneoDTO.getFechaFin());
        torneo.setFormato(torneoDTO.getFormato());
        torneo.setEstado(torneoDTO.getEstado());
        // Asigna la categoría si es necesario
        if (torneoDTO.getIdCategoria() != null) {
            Categoria categoria = categoriaService.findById(torneoDTO.getIdCategoria());
            torneo.setCategoria(categoria);
        }
        
        // Guarda el torneo actualizado
        torneoRepository.save(torneo);
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
