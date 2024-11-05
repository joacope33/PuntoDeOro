package com.Unsada.Web.service;

import java.util.List;

import com.Unsada.Web.dto.JugadorDTO;
import com.Unsada.Web.model.Jugador;

public interface JugadorService {

    public Jugador guardarJugador(JugadorDTO jugadorDTO);
    public Jugador obtenerJugadorById(Long id);
    public Jugador findByNombreCompleto(String nombre);
    public List<Jugador> obtenerTodosLosJugadores();
    public List<Jugador> obtenerTodosLosJugadoresOrdenadosPorPuntos();
    public void actualizarPuntos(Long jugadorId, int puntos);
    public void sumarPuntos(Long jugadorId, int puntos);
    public void restarPuntos(Long jugadorId, int puntos);
    public void borrarJugadorPorDni(String dni);
    public Jugador findByDni(String dni);
    public void actualizarJugadorDesdeDTO(JugadorDTO jugadorDTO);
    public List<Jugador> obtenerRankingPorCategoria(String categoria);
    // public void inscribirseATorneo(Long jugadorId, Long torneoId); ESTO VA EN TORNEOS
    // public void registrarPartido(Long jugadorId, Partido partido, Long torneoId) ESTO VA EN PARTIDOS
    // public void verificarYAscenderCategoria(Long jugadorId) ESTO VA EN CATEGORIA


}
