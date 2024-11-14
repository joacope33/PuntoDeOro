package com.Unsada.Web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.Unsada.Web.dto.JugadorDTO;
import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.service.JugadorService;




@Controller
@RequestMapping("/jugador")
public class JugadorController {
    

    @Autowired
    private JugadorService jugadorService;


    @ModelAttribute("jugador")
    public JugadorDTO jugadorDTO() {
        return new JugadorDTO();
    }
    

    @GetMapping
    public String obtenerFormJugador(Model model) {
        System.out.println("Método obtenerFormJugador llamado");
        
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();
        System.out.println("Cantidad de jugadores encontrados: " + jugadores.size());
        
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores en la base de datos.");
        } else {
            jugadores.forEach(j -> System.out.println(j.getNombreCompleto()));
        }

        model.addAttribute("jugadores", jugadores);
        return "jugador";
    }

    // Método para agregar un nuevo jugador
    @PostMapping("/guardar")
    public String agregarJugador(@ModelAttribute("jugador") JugadorDTO jugadorDTO) {
        try {
            jugadorService.guardarJugador(jugadorDTO);
            return "redirect:/jugador?exito"; // Redirige al formulario con un mensaje de éxito
        } catch (Exception e) {
            return "redirect:/jugador?error"; // Redirige al formulario con un mensaje de error
        }
    }


    
    // Método para borrar un jugador por su DNI
    @PostMapping("/borrar/{dni}")
    public String borrarJugadorPorDni(@PathVariable String dni) {
        try {
            System.out.println("Intentando eliminar jugador con DNI: " + dni); // Depuración
            jugadorService.borrarJugadorPorDni(dni);
            System.out.println("Jugador eliminado con éxito."); // Depuración
            return "redirect:/jugador";
        } catch (Exception e) {
            System.err.println("Error al eliminar jugador: " + e.getMessage()); // Depuración
            return "redirect:/jugador";
        }
    }

    // Método para actualizar el jugador
    @PostMapping("/actualizar")
    public String actualizarJugador(@ModelAttribute("jugadorDni") JugadorDTO jugadorDTO, Model model) {
        try {
            jugadorService.actualizarJugadorDesdeDTO(jugadorDTO);
            model.addAttribute("exito", true);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/jugador";
    }


    @GetMapping("/editar/{dni}")
    @ResponseBody // Esto es importante para devolver solo el cuerpo de la respuesta
    public JugadorDTO mostrarFormularioEdicion(@PathVariable("dni") String dni) {
        dni = dni.replaceAll("^\"|\"$", "");  // Eliminar comillas al inicio y al final
        Jugador jugador = jugadorService.findByDni(dni);
        if (jugador == null) {
            System.out.println("No se encuantra al jugador");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Maneja el error adecuadamente
        }
        System.out.println("Se encuantró al jugador" + jugador);
        return new JugadorDTO(jugador); // Asegúrate de tener un DTO adecuado para devolver los datos
    }

    @GetMapping("/todos")
    @ResponseBody
    public List<JugadorDTO> obtenerTodosLosJugadores() {
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();
        return jugadores.stream()
                        .map(jugador -> new JugadorDTO(jugador))  // Usamos la lambda en lugar de la referencia al constructor
                        .toList();
    }
    

}
