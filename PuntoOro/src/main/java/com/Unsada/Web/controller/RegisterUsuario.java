package com.Unsada.Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Unsada.Web.dto.UsuarioRegisterDTO;
import com.Unsada.Web.service.UsuarioService;



@Controller
@RequestMapping("/register")
public class RegisterUsuario {
    
    @Autowired
    UsuarioService usuarioService;

    @ModelAttribute("usuario")
    public UsuarioRegisterDTO usuarioRegistradoDTO() {
        return new UsuarioRegisterDTO();
    }

    @GetMapping
    public String mostrarFormularioRegister() {
        return "register";
    }
    
    @PostMapping
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioRegisterDTO usuarioRegisterDTO) {
        try {
            usuarioService.guardarUsuario(usuarioRegisterDTO);
        return "redirect:/register?exito";
        } catch (Exception e) {
            return "redirect:/register?error";
        }
    }
        
}
    
    


