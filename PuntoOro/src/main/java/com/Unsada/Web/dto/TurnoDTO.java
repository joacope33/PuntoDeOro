package com.Unsada.Web.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Jugador;
import com.Unsada.Web.model.Partido;
import com.Unsada.Web.model.enums.EstadoCancha;
import com.Unsada.Web.model.enums.TipoTurno;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TurnoDTO {

    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time hora;

    private Cancha cancha;  // Usamos CanchaDTO
    private int asistencia;
    private EstadoCancha estado;
    private TipoTurno tipoTurno;
    private Partido partido; // Usamos PartidoDTO
    private Set<Jugador> jugadores; // Relación muchos a muchos con jugadores


}
