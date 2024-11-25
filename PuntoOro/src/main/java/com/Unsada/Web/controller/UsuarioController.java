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
import org.springframework.web.server.ResponseStatusException;

import com.Unsada.Web.dto.UsuarioDTO;
import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
     @Autowired
    private UsuarioService usuarioService;


    @ModelAttribute("usuario")
    public UsuarioDTO usuarioDTO() {
        return new UsuarioDTO();
    }
    

    // Carga la lista de usuarios en el modelo y redirige a la vista de usuario
    @GetMapping
    public String obtenerFormUsuario(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario"; // Nombre de la vista correspondiente
    }
 
    // Método para agregar un nuevo jugador
    @PostMapping("/guardar")
    public String agregarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        try {
            usuarioService.guardarUsuario(usuarioDTO);
            return "redirect:/usuario?exito"; // Redirige al formulario con un mensaje de éxito
        } catch (Exception e) {
            return "redirect:/usuario?error"; // Redirige al formulario con un mensaje de error
        }
    }



    // Método para borrar un jugador por su DNI
    @PostMapping("/borrar/{email}")
    public String borrarUsuarioPorEmail(@PathVariable String email) {
        try {
            System.out.println("Intentando eliminar Usuario con Email: " + email); // Depuración
            usuarioService.borrarUsuarioPorEmail(email);
            System.out.println("Usuario eliminado con éxito."); // Depuración
            return "redirect:/usuario";
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage()); // Depuración
            return "redirect:/usuario?error";
        }
    }

    // Actualiza un usuario y redirige con mensajes de éxito o error
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuarioEmail") UsuarioDTO usuarioDTO) {
        try {
            usuarioService.actualizarUsuarioDesdeDTO(usuarioDTO);
            return "redirect:/usuario?exito";
        } catch (Exception e) {
            return "redirect:/usuario?error";
        }
    }


    // Devuelve un usuario específico para la edición
    @GetMapping("/editar/{email}")
    public UsuarioDTO mostrarFormularioEdicion(@PathVariable String email) {
        email = email.replaceAll("^\"|\"$", ""); // Elimina comillas si existen
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        return new UsuarioDTO(usuario); // Devuelve el DTO para la edición
    }







}
