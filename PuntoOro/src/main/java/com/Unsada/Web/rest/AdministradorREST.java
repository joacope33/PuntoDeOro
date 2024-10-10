package com.Unsada.Web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.service.AdministradorService;

@RestController
@RequestMapping("/jugadores")
public class AdministradorREST {

    @Autowired
    private AdministradorService administradorService;
    
}
