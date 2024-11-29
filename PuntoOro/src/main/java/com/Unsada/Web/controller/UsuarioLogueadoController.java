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
    public String editarPerfil(@ModelAttribute("usuario") UsuarioRegisterDTO usuarioDTO, Principal principal) {
        try {
            // Validación de estado
            if (usuarioDTO.getEstado() < 0 || usuarioDTO.getEstado() > 1) {
                return "redirect:/miCuenta?error"; // Valor de estado no válido
            }

            // Obtener el correo electrónico (username)
            String email = principal.getName();

            // Actualizamos el usuario
            usuarioService.actualizarUsuario(email, usuarioDTO);

            return "redirect:/miCuenta?exito"; // Redirigir al perfil si todo sale bien
        } catch (NumberFormatException e) {
            // Si la conversión falla, redirigir con un parámetro "error"
            return "redirect:/miCuenta?error"; // Redirigir con un parámetro error
        } catch (EntityNotFoundException e) {
            // Si no se encuentra el usuario, redirigir con un parámetro "error"
            return "redirect:/miCuenta?error"; // Redirigir con un parámetro error
        } catch (Exception e) {
            // Manejo del error genérico
            return "redirect:/miCuenta?error"; // Redirigir con un parámetro error
        }
    }

}
