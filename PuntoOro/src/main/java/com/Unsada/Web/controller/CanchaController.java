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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.service.CanchaService;





@Controller
@RequestMapping("/canchas")
public class CanchaController {

    @Autowired
    private CanchaService canchaService;


    @ModelAttribute("cancha")
    public CanchaDTO canchaDTO() {
        return new CanchaDTO();
    }


    @GetMapping
    public String mostrarCanchas(Model model) {
        System.out.println("Método mostrarCanchas llamado");

        List<Cancha> canchas = canchaService.obtenerTodasLasCanchasOrdenadas();
        

        if (canchas.isEmpty()) {
            System.out.println("No hay canchas en la base de datos.");
        } else {
            canchas.forEach(j -> System.out.println(j.getId()));
        }
        model.addAttribute("canchas", canchas);

        return "canchas";
    }


    @PostMapping("/actualizar")
    public String actualizarCancha(@ModelAttribute("cancha") CanchaDTO canchaDTO, RedirectAttributes redirectAttributes) {
        if (canchaDTO.getId() == null || canchaDTO.getId() <= 0) {
            redirectAttributes.addFlashAttribute("error", "ID de cancha no válido.");
            return "redirect:/cancha";
        }

        try {
            canchaService.actualizarCanchaDesdeDTO(canchaDTO); // Método de actualización
            redirectAttributes.addFlashAttribute("exito", "Cancha actualizada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al actualizar la cancha.");
        }
        return "redirect:/canchas";
    }



    @GetMapping("/editar/{id}")
    @ResponseBody // Esto es importante para devolver solo el cuerpo de la respuesta
    public CanchaDTO mostrarFormularioEdicion(@PathVariable("id") Long id) {

        Cancha cancha = canchaService.findById(id);
        if (cancha == null) {
            System.out.println("No se encuantra la cancha");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Maneja el error adecuadamente
        }
        System.out.println("Se encontró la cancha" + cancha);
        return new CanchaDTO(cancha); // Asegúrate de tener un DTO adecuado para devolver los datos
    }


    // Método para agregar un nuevo jugador
    @PostMapping("/agregar")
    public String agregarCancha(@ModelAttribute("cancha") CanchaDTO canchaDTO) {
        try {
            canchaService.guardarCancha(canchaDTO);
            return "redirect:/canchas"; // Redirige al formulario con un mensaje de éxito
        } catch (Exception e) {
            return "redirect:/canchas"; // Redirige al formulario con un mensaje de error
        }
    }
    

    public String borrarCanchaId(@PathVariable Long id) {
        try {
            System.out.println("Intentando eliminar cancha con ID: " + id); // Depuración
            canchaService.eliminarCancha(id);
            System.out.println("Cancha eliminada con éxito."); // Depuración
            return "redirect:/canchas";
        } catch (Exception e) {
            System.err.println("Error al eliminar cancha: " + e.getMessage()); // Depuración
            return "redirect:/canchas";
        }
    }
}