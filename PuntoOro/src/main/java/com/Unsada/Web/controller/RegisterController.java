package com.Unsada.Web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegisterController {




    @GetMapping
    public String mostrarFormularioRegister() {
        return "register";  // Asegúrate de tener una vista llamada "login.html" o "login.jsp"
    }
    
    
}
