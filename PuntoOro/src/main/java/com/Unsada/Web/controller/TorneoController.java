package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.service.TorneoService;

@Controller
@RequestMapping("/torneos")
public class TorneoController {
    
    @Autowired
    private TorneoService torneoService;

   

    @GetMapping
    public String obtenerFormJugador(Model model) {
        System.out.println("Método obtenerFormTorneo llamado");
        
        List<Torneo> torneos = torneoService.obtenerTodosLosTorneos();
        System.out.println("Cantidad de torneos encontrados: " + torneos.size());
        
        if (torneos.isEmpty()) {
            System.out.println("No hay jugadores en la base de datos.");
        } else {
            torneos.forEach(j -> System.out.println(j.getCategoria()));
        }

        model.addAttribute("torneos", torneos);
        return "torneos";
    }

      
    

    @GetMapping("/{id}")
    public String verTorneo(@PathVariable Long id, Model model) {
        try{
            Torneo torneo = torneoService.findById(id);
            model.addAttribute("torneo", torneo);
            return "redirect:/torneos?exito";
        }catch(Exception e){
            return "redirect:/torneos?error";
        }
        
        
    }

    @PostMapping("/crear")
    public String crearTorneo(@ModelAttribute Torneo torneo) {
        try{
            torneoService.guardarTorneo(torneo);
            return "redirect:/torneos";
        }catch(Exception e){
            return "redirect:/torneos?error";
        }
        
    }
/* 
    @PostMapping("/editar/{id}")
    public String editarTorneo(@PathVariable Long id, @ModelAttribute Torneo torneo) {
        torneoService.actualizarTorneo(id, torneo);
        return "redirect:/torneos";
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<?> eliminarTorneo(@PathVariable Long id) {
        torneoService.eliminarTorneo(id);
        return ResponseEntity.ok().build();
    }


    */

}
