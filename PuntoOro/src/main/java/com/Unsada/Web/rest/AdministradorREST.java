package com.Unsada.Web.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.model.Rol;
import com.Unsada.Web.service.AdministradorService;


@RestController
@RequestMapping("/administrador")
public class AdministradorREST {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rol>> getAdministradoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(administradorService.findById(id));
    }
    
}
