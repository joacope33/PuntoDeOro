package com.Unsada.Web.dto;

import java.time.LocalTime;
import java.util.List;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.model.enums.EstadoCancha;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CanchaDTO {
    

    private Long id;
    private LocalTime  horarioApertura; 
    private LocalTime  horarioCierre;
    private boolean disponibilidad;
    private LocalTime  duracion;
    private List<Turno> turnos;
    private EstadoCancha estado;

    public boolean getDisponibilidad(){
        return disponibilidad;
    }

    public CanchaDTO(Cancha cancha){
        this.id = cancha.getId();
        this.horarioApertura = cancha.getHorarioApertura();
        this.horarioCierre = cancha.getHorarioCierre();
        this.disponibilidad = cancha.isDisponibilidad();
        this.duracion = cancha.getDuracion();
        this.estado = cancha.getEstado();
    }



}
