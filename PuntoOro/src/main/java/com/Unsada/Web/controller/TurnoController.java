package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.service.CanchaServiceImpl;
import com.Unsada.Web.service.TurnoServiceImpl;


@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoServiceImpl turnoServiceImpl;

    @Autowired
    private CanchaServiceImpl CanchaServiceImpl;

    
    @GetMapping("/todos")
    public ResponseEntity<List<Turno>> getEvents() {
        try {
            // Obtener lista de Turno
            List<Turno> turnos = turnoServiceImpl.obtenerTodosLosTurnos();
            System.out.println(turnos);

            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/reservar")
    public String agregarTurno(@ModelAttribute("turno") TurnoDTO turnoDTO) {
        try {
            System.out.print(turnoDTO);
           turnoServiceImpl.guardarTurno(turnoDTO);
            return "redirect:/calendario"; // Redirige al formulario con un mensaje de éxito
        } catch (Exception e) {
            return "redirect:/calendario"; // Redirige al formulario con un mensaje de error
        }   
    }
}
