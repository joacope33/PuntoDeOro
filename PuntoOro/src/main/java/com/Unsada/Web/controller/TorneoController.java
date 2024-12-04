package com.Unsada.Web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Unsada.Web.dto.TorneoDTO;
import com.Unsada.Web.model.Categoria;
import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.model.enums.EstadoTorneo;
import com.Unsada.Web.model.enums.FormatoTorneo;
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


        model.addAttribute("formatos", FormatoTorneo.values());
        model.addAttribute("estados", EstadoTorneo.values());
        model.addAttribute("torneos", torneos);
        model.addAttribute("torneo", new TorneoDTO()); // Asegúrate de agregar un objeto Torneo vacío
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
    public String crearTorneo(@ModelAttribute("torneo") TorneoDTO torneoDTO) {
        try {
            torneoService.guardarTorneo(torneoDTO);
            return "redirect:/torneos";
        } catch (ResponseStatusException e) {
            e.printStackTrace(); // Imprime el error en la consola
            return "redirect:/torneos?error=" + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola
            return "redirect:/torneos?error=Ocurrió un error inesperado";
        }
    }


    @GetMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<TorneoDTO> editarTorneo(@PathVariable("id") Long id) {
        Torneo torneo = torneoService.findById(id);
        if (torneo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Torneo no encontrado");
        }
        return ResponseEntity.ok(new TorneoDTO(torneo));
    }



    

    @PostMapping("/actualizar")
    public String actualizarTorneo(@ModelAttribute("torneo") TorneoDTO torneoDTO, 
                                    RedirectAttributes redirectAttributes) {
        // Verifica qué datos llegan al backend
        System.out.println("Torneo recibido: " + torneoDTO);
        System.out.println("ID de categoría recibido: " + torneoDTO.getIdCategoria());

        if (torneoDTO.getId() == null || torneoDTO.getId() <= 0) {
            redirectAttributes.addFlashAttribute("error", "ID de torneo no válido: " + torneoDTO.getId());

            return "redirect:/torneos";
        }

        try {
    torneoService.actualizarTorneoDesdeDTO(torneoDTO);
        redirectAttributes.addFlashAttribute("exito", "Torneo actualizado correctamente.");
    } catch (DataIntegrityViolationException e) {
        redirectAttributes.addFlashAttribute("error", "Error de integridad al actualizar el torneo.");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al actualizar el torneo.");
        e.printStackTrace();  // Imprime el error en consola para depuración
    }


        return "redirect:/torneos";
    }






// Método para borrar un usuario por su DNI

@PostMapping("/borrar/{id}")
public String borrarTorneoPorId(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
        torneoService.borrarTorneoPorId(id);
        redirectAttributes.addFlashAttribute("exito", "Torneo eliminado con éxito.");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al eliminar el torneo.");
    }
    return "redirect:/torneos";
}



}



