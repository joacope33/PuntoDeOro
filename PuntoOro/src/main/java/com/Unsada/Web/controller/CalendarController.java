package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.service.CanchaServiceImpl;
import com.Unsada.Web.service.TurnoServiceImpl;


@RestController
@RequestMapping("/Calendario")
public class CalendarController {

    @Autowired
    private TurnoServiceImpl turnoServiceImpl;
    
    @Autowired
    private CanchaServiceImpl canchaServiceImpl;

    // Método para obtener los eventos de una cancha específica
    @GetMapping("/{id}")
    public ResponseEntity<List<TurnoDTO>> getEvents(@PathVariable Long id) {
        try {
            Cancha cancha = canchaServiceImpl.obtenerCanchabyId(id);
            if (cancha == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
            }

            // Obtener lista de Turno
            List<Turno> turnos = turnoServiceImpl.obtenerTurnoByCancha(cancha);

            // Convertir cada Turno en TurnoDTO
            List<TurnoDTO> turnosDTO = turnos.stream()
                .map(turno -> new TurnoDTO(
                    turno.getId(),
                    turno.getDia(),
                    turno.getHora(),
                    turno.getCancha(),
                    turno.getAsistencia(),
                    turno.getEstado(),
                    turno.getTipoTurno(),
                    turno.getPartido()
                ))
                .collect(Collectors.toList());

            return ResponseEntity.ok(turnosDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
