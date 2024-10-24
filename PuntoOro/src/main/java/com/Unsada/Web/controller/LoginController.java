package com.Unsada.Web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }


}
