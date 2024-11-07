package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.service.TurnoServiceImpl;


@Controller
@RequestMapping("/turno")
public class TurnoController {
    
    @Autowired
    private TurnoServiceImpl turnoServiceImpl;
    
    @ModelAttribute("turno")
    public TurnoDTO turnoDTO() {
        return new TurnoDTO();
    }

    @GetMapping
    public String obtenerFormTurno(@RequestParam("idCancha") Long idCancha, Model model) {
        System.out.println("Método obtenerFormTurno llamado para idCancha: " + idCancha);
        
        // Llamada al servicio para obtener los turnos por idCancha
        List<Turno> turnos = turnoServiceImpl.obtenerTurnoByIdCancha(idCancha);
        System.out.println("Cantidad de turnos encontrados: " + turnos.size());
        
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos para la cancha con id " + idCancha + " en la base de datos.");
        } else {
            turnos.forEach(t -> System.out.println("Turno ID: " + t.getId() + ", Estado: " + t.getEstado()));
        }

        // Agrega la lista de turnos al modelo para pasarlo a la vista
        model.addAttribute("turnos", turnos);
        
        return "turno"; // Nombre de la vista HTML para mostrar la lista de turnos
    }
}