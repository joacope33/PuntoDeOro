package com.Unsada.Web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.service.CanchaServiceImpl;

@RestController
public class CanchaREST {

    @Autowired
    private CanchaServiceImpl canchaService;

    // Endpoint para obtener una cancha por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cancha> obtenerCanchaPorId(@PathVariable Long id) {
        try {
            Cancha cancha = canchaService.findById(id);
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
