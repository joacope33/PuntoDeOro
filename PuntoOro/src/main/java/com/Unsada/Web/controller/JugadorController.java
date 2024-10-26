package com.Unsada.Web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.JugadorDTO;
import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.service.JugadorService;




@Controller
@RequestMapping("/jugador")
public class JugadorController {
    

    @Autowired
    JugadorService jugadorService;


    @ModelAttribute("jugador")
    public JugadorDTO jugadorDTO() {
        return new JugadorDTO();
    }
    

    @GetMapping
    public String obtenerFormJugador(Model model) {
        System.out.println("Método obtenerFormJugador llamado");
        
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadoresOrdenadosPorPuntos();
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
    

}
