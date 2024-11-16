package com.Unsada.Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.service.CanchaServiceImpl;

@RestController
@RequestMapping("/cancha")
public class CanchaController {

    private CanchaServiceImpl canchaService;

    @Autowired
    public CanchaController(CanchaServiceImpl canchaService) {
        this.canchaService = canchaService;
    }

    // Endpoint para obtener una cancha por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> obtenerCanchaPorId(@PathVariable Long id) {
        try {
            Cancha cancha = canchaService.obtenerCanchabyId(id);
            return ResponseEntity.ok(cancha);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para guardar una nueva cancha
    @PostMapping
    public ResponseEntity<Cancha> guardarCancha(@RequestBody CanchaDTO canchaDTO) {
        try {
            Cancha canchaGuardada = canchaService.guardarCancha(canchaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(canchaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}