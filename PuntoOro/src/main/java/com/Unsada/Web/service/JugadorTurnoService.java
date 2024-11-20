package com.Unsada.Web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.model.JugadorTurno;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.repository.JugadorRepository;
import com.Unsada.Web.repository.JugadorTurnoRepository;
import com.Unsada.Web.repository.TurnoRepository;

@Service
public class JugadorTurnoService {

    @Autowired
    private JugadorTurnoRepository jugadorTurnoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Transactional
    public void asignarJugadorATurno(Long idJugador, Long idTurno) {
        // Asegurarse de que el jugador y el turno existan
        Jugador jugador = jugadorRepository.findById(idJugador)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        Turno turno = turnoRepository.findById(idTurno)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        // Crear una nueva asignación de jugador a turno
        JugadorTurno jugadorTurno = new JugadorTurno();
        jugadorTurno.setJugador(jugador);
        jugadorTurno.setTurno(turno);

        // Guardar en la tabla jugadores_turnos
        jugadorTurnoRepository.save(jugadorTurno);
        
        // Si necesitas otras operaciones, puedes añadirlas aquí, y todas estarán dentro de la misma transacción.
    }
}
