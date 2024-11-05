package com.Unsada.Web.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.service.JugadorService;

@Controller
public class RankingController {
    
    @Autowired
    private JugadorService jugadorService;

    @GetMapping("/ranking")
    public String mostrarRankingPorCategoria(@RequestParam(value = "categoria", required = false) String categoria, Model model) {
        List<Jugador> jugadores;

        if (categoria != null && !categoria.isEmpty()) {
            jugadores = jugadorService.obtenerRankingPorCategoria(categoria);
        } else {
            jugadores = jugadorService.obtenerTodosLosJugadoresOrdenadosPorPuntos();
        }

        // Agrupar jugadores por categoría
        Map<String, List<Jugador>> jugadoresPorCategoria = jugadores.stream()
                .collect(Collectors.groupingBy(jugador -> jugador.getCategoria() != null ? jugador.getCategoria() : "Sin categoría"));

        model.addAttribute("jugadoresPorCategoria", jugadoresPorCategoria);
        model.addAttribute("categoria", categoria);

        return "ranking"; // Asegúrate de que este nombre coincida con tu vista
    }




}
