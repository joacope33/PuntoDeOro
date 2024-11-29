package com.Unsada.Web.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.service.CanchaService;

@RestController
@RequestMapping("/canchas")
public class CanchaREST {

    @Autowired
    private CanchaService canchaService;


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

    @GetMapping("/todas")
    public ResponseEntity<List<Cancha>> obtenerTodasLasCanchas() {
        try {
            List<Cancha> canchas = canchaService.obtenerTodasLasCanchas();
            if (canchas.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 si no hay canchas
            }
            return ResponseEntity.ok(canchas); // Retorna 200 si hay canchas
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Collections.emptyList()); // Retorna 500 con una lista vacía
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CanchaDTO> obtenerCanchaPorId(@PathVariable("id") Long id) {
        Cancha cancha = canchaService.findById(id);
        if (cancha == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CanchaDTO canchaDTO = new CanchaDTO(cancha); // Asumiendo que CanchaDTO tiene un constructor para mapear Cancha
        return ResponseEntity.ok(canchaDTO);
    }

    

}
