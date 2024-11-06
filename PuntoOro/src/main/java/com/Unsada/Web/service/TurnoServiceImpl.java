package com.Unsada.Web.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.TurnoDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.repository.TurnoRepository;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;


   
    @Override
    public List<Turno> obtenerTurnoByIdCancha(Cancha cancha) {
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
                                   turnoDTO.getAsistencia()
        );
        return turnoRepository.save(turno);
    }
    
}
