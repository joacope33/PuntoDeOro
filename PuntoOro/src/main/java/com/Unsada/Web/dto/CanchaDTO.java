package com.Unsada.Web.dto;

import java.sql.Time;

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
    private Time horarioApertura; 
    private Time horarioCierre;
    private boolean disponibilidad;
    private Time duracion;
    private EstadoCancha estado;

    public boolean getDisponibilidad(){
        return disponibilidad;
    }



}
