package com.Unsada.Web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.service.UsuarioService;


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

        // ID
        model.addAttribute("id", usuario.getId());
        // Role
        model.addAttribute("role", usuario.getRole());
        // Agregar el nombre del usuario al modelo
        model.addAttribute("nombreUsuario", usuario.getNombreCompleto());
        // Agregar el nombre del usuario al modelo
        model.addAttribute("email", usuario.getEmail());
        // Telefono
        model.addAttribute("telefono", usuario.getTelefono());
        // Estado
        model.addAttribute("estado", usuario.getEstado());
        return "miCuenta";
    }

    @PostMapping("/editar")
    public String editarPerfil(Usuario usuarioActualizado, Principal principal) {
        // Obtener el correo electrónico (username)
        String email = principal.getName();

        // Buscar el usuario actual en la base de datos
        Usuario usuario = usuarioService.findByEmail(email);
        
        // Actualizar los datos del usuario
        usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setTelefono(usuarioActualizado.getTelefono());
        usuario.setEstado(usuarioActualizado.getEstado());

        // Convertimos el usuario a usuarioDTO
        UsuarioRegisterDTO usuarioRegisterDTO = usuarioService.convertirUsuarioADTO(usuario);

        // Guardar el usuario actualizado en la base de datos
        usuarioService.guardarUsuario(usuarioRegisterDTO);

        // Redirigir a la página de perfil
        return "redirect:/miCuenta";
    }
    
}
