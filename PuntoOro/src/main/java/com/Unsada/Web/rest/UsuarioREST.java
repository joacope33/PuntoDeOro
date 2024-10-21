package com.Unsada.Web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioREST {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(
            @RequestParam String nombreCompleto,
            @RequestParam String correoElectronico,
            @RequestParam String telefono,
            @RequestParam String contrasena) {
        try {
            usuarioService.registrarUsuario(nombreCompleto, correoElectronico, telefono, contrasena);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
