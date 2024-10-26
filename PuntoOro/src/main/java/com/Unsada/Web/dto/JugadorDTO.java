package com.Unsada.Web.dto;

import java.time.LocalDate;

import com.Unsada.Web.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDTO {

    private Long id;
    private String nombreCompleto;
    private String telefono;
    private Categoria categoria;
    private LocalDate fechaDeNacimiento;
    private String dni;
    private int calificacion;
    private int puntos;
    private String comentario;


    public JugadorDTO(String nombreCompleto, String telefono, Categoria categoria, LocalDate fechaDeNacimiento, String dni,
            int calificacion, int puntos, String comentario) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.categoria = categoria;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.dni = dni;
        this.calificacion = calificacion;
        this.puntos = puntos;
        this.comentario = comentario;
    }


    
}