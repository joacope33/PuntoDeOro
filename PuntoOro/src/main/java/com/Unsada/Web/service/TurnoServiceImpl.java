package com.Unsada.Web.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.repository.TurnoRepository;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    public TurnoRepository turnoRepository;

    @Autowired
    public JugadorTurnoService jugadorTurnoService;

    @Override
    public List<Turno> obtenerTurnoByCancha(Cancha cancha) {
        return turnoRepository.findAllByCancha(cancha);
            
    }

    @Override
    public Turno findById(Long idTurno) {
        return turnoRepository.findById(idTurno)
        .orElseThrow(() -> new RuntimeException("No se encontró el turno con ese Id: " + idTurno));
        
    }

    @Override
    public List<Turno> obtenerTurnoByDia(LocalDate dia) {
        return turnoRepository.findAllByDia(dia);
    }

    @Override
    public Turno guardarTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno(   turnoDTO.getDia(),
                                   turnoDTO.getHora(),
                                   turnoDTO.getCancha(),
                                   turnoDTO.getEstado(),
                                   turnoDTO.getTipoTurno(),
                                   turnoDTO.getAsistencia(),
                                   turnoDTO.getPartido(),
                                   turnoDTO.getJugadores()
        );
        Set<Jugador> jugadores = turno.getJugadores(); // Obtener el conjunto de jugadores

        // Recorrer cada jugador y asignarlo al turno
        for (Jugador jugador : jugadores) {
            jugadorTurnoService.asignarJugadorATurno(jugador.getId(), turno.getId());
        }
        return turnoRepository.save(turno);
    }


    @Override
    public List<Turno> obtenerTodosLosTurnos() {
        try {
            return turnoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los turnos", e);
        }
    }


}
