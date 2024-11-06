package com.Unsada.Web.dto;

import java.sql.Time;
import java.time.LocalDate;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.enums.EstadoCancha;
import com.Unsada.Web.model.enums.TipoTurno;


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
    private LocalDate dia;
    private Time hora;
    private Cancha cancha;
    private int asistencia;
    private EstadoCancha estado;
    private TipoTurno tipoTurno;

}
