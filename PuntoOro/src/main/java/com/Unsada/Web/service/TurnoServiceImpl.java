package com.Unsada.Web.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.repository.TurnoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    public TurnoRepository turnoRepository;

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
        // Recorrer cada jugador y asignarlo al turno
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
    @Override
    public void eliminarTurno(Long idTurno) {
            if (turnoRepository.existsById(idTurno)) {
                turnoRepository.deleteById(idTurno);
            } else {
                throw new EntityNotFoundException("El turno con ID " + idTurno + " no existe");
            }
        }
        @Override
        public void editarTurno(Long id, TurnoDTO turnoDTO) {
             Turno turnoExistente = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));
            turnoExistente.setJugadores(turnoDTO.getJugadores()); // Ejemplo, actualizar jugador
            turnoExistente.setDia(turnoDTO.getDia());
            turnoExistente.setHora(turnoDTO.getHora());
            turnoExistente.setTipoTurno(turnoDTO.getTipoTurno());
            turnoExistente.setCancha(turnoDTO.getCancha());
            turnoRepository.save(turnoExistente); // Guardar el turno actualizado en la base de datos

    }

}
