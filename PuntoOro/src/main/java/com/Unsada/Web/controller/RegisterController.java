package com.Unsada.Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Unsada.Web.service.UsuarioService; // Asegúrate de tener un servicio para manejar usuarios

@Controller
public class RegisterController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public String register(@RequestParam String nombreCompleto, 
                           @RequestParam String correoElectronico, 
                           @RequestParam String telefono, 
                           @RequestParam String contrasena, 
                           RedirectAttributes redirectAttributes) {
        try {
            usuarioService.registrarUsuario(nombreCompleto, correoElectronico, telefono, contrasena);
            return "redirect:/login"; // Redirigir a la página de login después del registro exitoso
        } catch (IllegalArgumentException e) {
            // Manejar el error, enviando un mensaje de error al formulario de registro
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register"; // Redirigir de vuelta al formulario de registro
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error durante el registro. Por favor, intenta nuevamente.");
            return "redirect:/register"; // Redirigir de vuelta al formulario de registro
        }
    }
}
