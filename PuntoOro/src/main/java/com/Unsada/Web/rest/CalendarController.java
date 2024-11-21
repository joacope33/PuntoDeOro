package com.Unsada.Web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.service.CanchaService;
import com.Unsada.Web.service.TurnoService;


@RestController
@RequestMapping("/calendario")
public class CalendarController {

   
    
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private CanchaService canchaService;

    // Método para obtener los eventos de una cancha específica
    @GetMapping("/{id}")
    public ResponseEntity<List<Turno>> getEvents(@PathVariable Long id) {
        try {
            Cancha cancha = canchaService.findById(id);
            System.out.println(cancha);
            if (cancha == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
            }

            // Obtener lista de Turno
            List<Turno> turnos = turnoService.obtenerTurnoByCancha(cancha);
            System.out.println(turnos);


            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

     
}
