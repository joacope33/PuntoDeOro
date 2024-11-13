package com.Unsada.Web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;


@Controller
@RequestMapping("/miCuenta")
public class UsuarioLogueadoController {
    
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarPerfil(Principal principal, Model model) {
        // Obtener el correo electrónico (username)
        String email = principal.getName();

        // Buscar el usuario por correo electrónico
        Usuario usuario = usuarioService.findByEmail(email);

        // Agregar el usuario completo al modelo
        model.addAttribute("usuario", usuario);
        
        return "miCuenta";
    }

    @PostMapping("/editar")
    public String editarPerfil(@ModelAttribute("usuario") UsuarioRegisterDTO usuarioDTO, Principal principal, Model model) {
        try {
            // Obtener el correo electrónico (username)
            String email = principal.getName();

            // Actualizamos el usuario
            usuarioService.actualizarUsuario(email, usuarioDTO);
            
            System.out.println("Funciona " + usuarioDTO);
            // Redirigir a la página de perfil
            return "redirect:/miCuenta";
        } catch (EntityNotFoundException e) {
            // Si no se encuentra el usuario, agregar el error al modelo
            model.addAttribute("error", "No se encontró el usuario.");
            return "miCuenta"; // Volver a la misma página con el mensaje de error
        } catch (Exception e) {
            // Manejo del error genérico
            model.addAttribute("error", "Ocurrió un error al actualizar el perfil. Inténtalo nuevamente.");
            return "miCuenta"; // Volver a la misma página con el mensaje de error
        }
    }
    
}
