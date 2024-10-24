package com.Unsada.Web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController{    
    
    @GetMapping("/index")
    public String mostrarIndex() {
        return "index"; // Asegúrate de que haya un archivo index.html o index.jsp en la carpeta de plantillas
    }


}
