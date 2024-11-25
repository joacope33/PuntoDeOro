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

import com.Unsada.Web.dto.UsuarioDTO;
import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.model.enums.Role;
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
    

    @GetMapping
    public String obtenerFormUsuario(Model model) {
        System.out.println("Método obtenerFormJugador llamado");
        
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        System.out.println("Cantidad de usuarios encontrados: " + usuarios.size());
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en la base de datos.");
        } else {
            usuarios.forEach(j -> System.out.println(j.getNombreCompleto()));
        }

        model.addAttribute("usuarios", usuarios);
        return "usuario";
    }
 
    // Método para agregar un nuevo jugador
    @PostMapping("/guardar")
    public String agregarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        try {
            if(usuarioDTO.getRole()==null){
                usuarioDTO.setRole(Role.USER);

            }
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
            return "redirect:/usuario";
        }
    }

    // Método para actualizar el jugador
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuarioEmail") UsuarioDTO usuarioDTO, Model model) {
        try {
            usuarioService.actualizarUsuarioDesdeDTO(usuarioDTO);
            model.addAttribute("exito", true);
        } catch (Exception e) {
            model.addAttribute("error", true);
        }
        return "redirect:/usuario";
    }


    @GetMapping("/editar/{email}")
    @ResponseBody // Esto es importante para devolver solo el cuerpo de la respuesta
    public UsuarioDTO mostrarFormularioEdicion(@PathVariable("email") String email) {
        email = email.replaceAll("^\"|\"$", "");  // Eliminar comillas al inicio y al final
        System.out.println(email);
        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            System.out.println("No se encuantra al jugador");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Maneja el error adecuadamente
        }
        System.out.println("Se encontró al usuario" + usuario);
        return new UsuarioDTO(usuario); // Asegúrate de tener un DTO adecuado para devolver los datos
    }







}
