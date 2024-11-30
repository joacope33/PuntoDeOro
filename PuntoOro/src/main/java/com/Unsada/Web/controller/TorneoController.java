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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        
        List<Torneo> torneos = torneoService.obtenerTodosLosTorneos();
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();

        model.addAttribute("torneos", torneos);
        model.addAttribute("categorias", categorias);


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
    @ResponseBody
    public TorneoDTO editarTorneo(@PathVariable("id") Long id) {
        System.out.println("ID recibido: " + id);  // Verifica si el id es correcto

        Torneo torneo = torneoService.findById(id);
        if (torneo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Torneo no encontrado");
        }
        return new TorneoDTO(torneo);
    }


    

    @PostMapping("/actualizar")
    public String actualizarTorneo(@ModelAttribute("torneo") TorneoDTO torneoDTO, 
                                    RedirectAttributes redirectAttributes) {
        // Verifica qué datos llegan al backend
        System.out.println("Torneo recibido: " + torneoDTO);
        System.out.println("ID de categoría recibido: " + torneoDTO.getIdCategoria());

        if (torneoDTO.getId() == null || torneoDTO.getId() <= 0) {
            redirectAttributes.addFlashAttribute("error", "ID de torneo no válido.");
            return "redirect:/torneos";
        }

        try {
            torneoService.actualizarTorneoDesdeDTO(torneoDTO);
            redirectAttributes.addFlashAttribute("exito", "Torneo actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el torneo.");
            e.printStackTrace();
        }

        return "redirect:/torneos";
    }






// Método para borrar un usuario por su DNI
@PostMapping("/borrar/{id}")
public String borrarTorneoPorId(@PathVariable Long id) {
    try {
        
        torneoService.borrarTorneoPorId(id);
        System.out.println("Torneo eliminado con éxito."); // Depuración
        return "redirect:/torneos";
    } catch (Exception e) {
        System.err.println("Error al eliminar torneo: " + e.getMessage()); // Depuración
        return "redirect:/torneos";
    }
}


}



