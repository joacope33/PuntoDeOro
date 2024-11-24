package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.service.TurnoService;


@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoServiceImpl;


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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        try {
            turnoServiceImpl.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el turno: " + e.getMessage());
        }
    }
    @PostMapping("/editar/{idTurno}")
    public String editarTurno(@PathVariable Long idTurno, @ModelAttribute("turno") TurnoDTO turnoDTO) {
        try {
            // Obtener el turno por id y actualizarlo con los nuevos datos
            turnoServiceImpl.editarTurno(idTurno, turnoDTO); // Implementa este método en tu servicio
            return "redirect:/calendario"; // Redirige al calendario después de la edición
        } catch (Exception e) {
            return "redirect:/calendario?error=true"; // Redirige con un mensaje de error
        }
}
}
