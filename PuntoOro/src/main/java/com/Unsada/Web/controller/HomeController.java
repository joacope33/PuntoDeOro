package com.Unsada.Web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// Define la ruta base para todas las solicitudes manejadas por este controlador.
@RequestMapping("")
public class HomeController{    
    /**
     * Este metodo GET maneja la solicitud de /home
     * 
     * @return  retorna la pagina index
     */
    @GetMapping("")
        public String mostrarFormularioRegistro() {
            // LOG

            return "index"; // Esto devuelve el nombre del archivo HTML sin la extensión (.html)
    }
    @GetMapping("/calendario")
        public String mostrarCalendario(){
            return "calendario";
        }
    @GetMapping("/jugadores")
    public String mostrarJugadores(){
        return "jugadores";
    }
    @GetMapping("/ranking")
    public String mostrarRanking(){
        return "Ranking";
    }
    @GetMapping("/torneos")
    public String mostrarTorneos(){
        return "torneos";
    }

}
