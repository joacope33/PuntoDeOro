package com.Unsada.Web.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.JugadorDTO;
import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.repository.JugadorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JugadorServiceImpl implements JugadorService {


    @Autowired
    private JugadorRepository jugadorRepository;



    @Override
    public Jugador guardarJugador(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador(jugadorDTO.getNombreCompleto(),
                                      jugadorDTO.getTelefono(),
                                      jugadorDTO.getCategoria(), 
                                      jugadorDTO.getFechaDeNacimiento(),
                                      jugadorDTO.getDni(),
                                      jugadorDTO.getCalificacion(),
                                      jugadorDTO.getPuntos(),
                                      jugadorDTO.getComentario()
                                      );

        return jugadorRepository.save(jugador);
    }

    @Override
    public Jugador obtenerJugadorById(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el jugador con esa Id: " + id));
    }


    @Override
    public Jugador findByNombreCompleto(String nombre) {
        return jugadorRepository.findByNombreCompleto(nombre)
                .orElseThrow(() -> new RuntimeException("No se encontró el jugador con ese nombre: "+ nombre));
    }

    @Override
    public List<Jugador> obtenerTodosLosJugadores() {
        try {
            return jugadorRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los jugadores", e);
        }
    }

    @Override
    public List<Jugador> obtenerTodosLosJugadoresOrdenadosPorPuntos() {
        List<Jugador> jugadores = obtenerTodosLosJugadores();
        Collections.sort(jugadores, Comparator.comparing(Jugador::getPuntos).reversed());
        return jugadores;
    }

    @Override
    public void borrarJugadorPorDni(String dni) {
        Jugador jugador = jugadorRepository.findByDni(dni); // Buscar jugador por DNI
        if (jugador != null) { // Verificar si el jugador existe
            jugadorRepository.deleteById(jugador.getId()); // Eliminar por ID
        } else {
            throw new RuntimeException("Jugador no encontrado con DNI: " + dni);
        }
    }

    @Override
    public void actualizarPuntos(Long jugadorId, int puntos) {
        Jugador jugador = obtenerJugadorById(jugadorId);
        jugador.setPuntos(puntos);
        jugadorRepository.save(jugador);
    }

    @Override
    public void sumarPuntos(Long jugadorId, int puntos) {
        Jugador jugador = obtenerJugadorById(jugadorId);
        jugador.setPuntos(jugador.getPuntos() + puntos);
        jugadorRepository.save(jugador);
    }

    @Override
    public void restarPuntos(Long jugadorId, int puntos) {
        Jugador jugador = obtenerJugadorById(jugadorId);
        jugador.setPuntos(Math.max(jugador.getPuntos() - puntos, 0)); // Esto evita los puntos negativos
        jugadorRepository.save(jugador);
    }

    @Override
    public Jugador findByDni(String dni) {
        System.out.println(dni);
        Jugador jugador = jugadorRepository.findByDni(dni);
        
        if (jugador == null) {
            // Aquí puedes manejar el caso en el que no se encuentra un jugador con ese DNI.
            System.out.println("No se encontró un jugador con el DNI: " + dni);
            return null;  // O lanzar una excepción, dependiendo de cómo desees manejarlo.
        }
        
        System.out.println(jugador.getNombreCompleto());
        return jugador;
    }

    @Override
    public void actualizarJugadorDesdeDTO(JugadorDTO jugadorDTO) {
        // Busca el jugador existente en la base de datos
        Jugador jugador = jugadorRepository.findByDni(jugadorDTO.getDni());

        if (jugador != null) {
            // Actualiza los campos con los valores del DTO
            jugador.setNombreCompleto(jugadorDTO.getNombreCompleto());
            jugador.setTelefono(jugadorDTO.getTelefono());
            jugador.setCategoria(jugadorDTO.getCategoria());
            jugador.setFechaDeNacimiento(jugadorDTO.getFechaDeNacimiento());
            jugador.setDni(jugadorDTO.getDni());
            jugador.setCalificacion(jugadorDTO.getCalificacion());
            jugador.setPuntos(jugadorDTO.getPuntos());
            jugador.setComentario(jugadorDTO.getComentario());

            // Guarda la entidad actualizada
            jugadorRepository.save(jugador);
        } else {
        throw new EntityNotFoundException("Jugador no encontrado con dni: " + jugadorDTO.getId());
        }
    }


    @Override
    public List<Jugador> obtenerRankingPorCategoria(String categoria) {
        List<Jugador> jugadores = jugadorRepository.findByCategoria(categoria);
        jugadores.sort(Comparator.comparingInt(Jugador::getPuntos).reversed()); // Orden descendente
        return jugadores;
    }
}


