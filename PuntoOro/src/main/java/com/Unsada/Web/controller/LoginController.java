package com.Unsada.Web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;;

@Controller
public class LoginController {
    
    // Mostrar el formulario de login
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";  // Asegúrate de tener una vista llamada "login.html" o "login.jsp"
    }

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";  // Asegúrate de tener una vista llamada "index.html"
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, 
                        @RequestParam("password") String password,
                        Model model){
        // Lógica de autenticación aquí
        if (username.equals("admin") && password.equals("admin")) {
            // Si es válido, redirigir a una página de éxito
            return "redirect:/index";
        } else {
            // Si falla, devolver al home con un mensaje de error
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index";
        }                    
    }
}
