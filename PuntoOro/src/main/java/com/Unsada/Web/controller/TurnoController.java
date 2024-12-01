package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.model.TurnosFijos;
import com.Unsada.Web.model.enums.EstadoCancha;
import com.Unsada.Web.service.TurnoFijoService;
import com.Unsada.Web.service.TurnoService;


@Controller
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoServiceImpl;
    @Autowired
    private TurnoFijoService turnoFijoServiceImp;
    
   
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
            if(turnoDTO.getCancha().getEstado()==EstadoCancha.DISPONIBLE){
           turnoServiceImpl.guardarTurno(turnoDTO);
            } 
            else {
                throw new IllegalArgumentException("La cancha no está disponible para reservar.");
            }

                return "redirect:/calendario?success=true"; // Redirige al formulario con un mensaje de éxito
            } catch (IllegalArgumentException e) {
                System.err.println("Error de validación: " + e.getMessage());
                return "" + e.getMessage(); // Redirige con el mensaje de error
            } catch (Exception e) {
                System.err.println("Error inesperado: " + e.getMessage());
                e.printStackTrace(); // Opcional: para registrar el stack trace completo
                return ""; // Redirige con un mensaje de error genérico
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

    @GetMapping("/Fijos")
    public ResponseEntity<List<TurnosFijos>> getEvents1() {
        try {
            // Obtener lista de Turno
            List<TurnosFijos> turnos = turnoFijoServiceImp.findAllFijos();
            System.out.println(turnos);

            return ResponseEntity.ok(turnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
