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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.Unsada.Web.dto.TorneoDTO;
import com.Unsada.Web.model.Categoria;
import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.service.CategoriaService;
import com.Unsada.Web.service.TorneoService;

@Controller
@RequestMapping("/torneos")
public class TorneoController {
    
    @Autowired
    private TorneoService torneoService;

    @Autowired
    private CategoriaService categoriaService;
   

    @GetMapping
    public String obtenerFormJugador(Model model) {
        System.out.println("Método obtenerFormTorneo llamado");
        
        List<Torneo> torneos = torneoService.obtenerTodosLosTorneosPorFecha();
        System.out.println("Cantidad de torneos encontrados: " + torneos.size());
        
        if (torneos.isEmpty()) {
            System.out.println("No hay torneos en la base de datos.");
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
            return "/torneos";
        }catch(Exception e){
            return "/torneos";
        }
        
        
    }

    @PostMapping("/crear")
    public String crearTorneo(@RequestParam(value = "idCategoria", required = false) Long idCategoria, @ModelAttribute Torneo torneo) {
        try{

            System.out.println("La categoria es: " + idCategoria);
            // Buscar la categoría en la base de datos
            Categoria categoria = categoriaService.findById(idCategoria);
                
            // Asignar la categoría al torneo
            torneo.setCategoria(categoria);

            torneoService.guardarTorneo(torneo);
            System.out.println("Torneo agregado");
            return "redirect:/torneos";
        }catch(Exception e){
            System.out.println("Error");
            return "redirect:/torneos";
        }
        
    }

    @GetMapping("/editar/{id}")
    @ResponseBody // Esto es importante para devolver solo el cuerpo de la respuesta
    public TorneoDTO editarTorneo(@PathVariable("id") Long id) {
        Torneo torneo = torneoService.findById(id);
        if (torneo == null) {
            System.out.println("No se encuantra el torneo");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Maneja el error adecuadamente
        }
        System.out.println("Se encontró el torneo " + torneo);
        return new TorneoDTO(torneo); // Asegúrate de tener un DTO adecuado para devolver los datos
    }

    // Método para actualizar el jugador
    @PostMapping("/actualizar")
    public String actualizarJugador(@ModelAttribute("torneoId") TorneoDTO torneoDTO, Model model) {
        try {
            torneoService.actualizarTorneoDesdeDTO(torneoDTO);
            model.addAttribute("exito", true);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/torneos";
    }





}



