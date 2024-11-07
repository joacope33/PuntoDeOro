package com.Unsada.Web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.repository.UsuarioRepository;




@Controller
public class HomeController{    
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/index")
    public String mostrarPaginaPrincipal(Principal principal, Model model) {
        // Obtener el correo electrónico (username)
        String email = principal.getName();

        // Buscar el usuario por correo electrónico
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Agregar el nombre del usuario al modelo
        model.addAttribute("nombreUsuario", usuario.getNombreCompleto());

        return "index";
    }

    @GetMapping("/calendario")
    public String mostrarCalendario() {
        return "calendario";
    }

    @GetMapping("/canchas")
    public String mostrarCanchas() {
        return "canchas";
    }
    
    @GetMapping("/torneos")
    public String mostrarTorneos() {
        return "torneos";
    }


}
