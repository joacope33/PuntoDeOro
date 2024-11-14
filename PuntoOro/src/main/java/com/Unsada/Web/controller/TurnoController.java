package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.service.TurnoServiceImpl;


@Controller
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoServiceImpl turnoServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<List<Turno>> getEvents(@PathVariable Long id) {
        try {

            // Obtener lista de Turno
            List<Turno> turnos = turnoServiceImpl.obtenerTodosLosTurnos();
            System.out.println(turnos);


            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
