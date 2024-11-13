package com.Unsada.Web.dto;

import java.time.LocalDate;

import com.Unsada.Web.model.Jugador;

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
    private String categoria;
    private LocalDate fechaDeNacimiento;
    private String dni;
    private int calificacion;
    private int puntos;
    private String comentario;


    public JugadorDTO(String nombreCompleto, String telefono, String categoria, LocalDate fechaDeNacimiento, String dni,
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

    // Constructor que acepta un objeto Jugador
    public JugadorDTO(Jugador jugador) {
        this.nombreCompleto = jugador.getNombreCompleto();
        this.telefono = jugador.getTelefono();
        this.categoria = jugador.getCategoria();
        this.fechaDeNacimiento = jugador.getFechaDeNacimiento();
        this.dni = jugador.getDni();
        this.calificacion = jugador.getCalificacion();
        this.puntos = jugador.getPuntos();
        this.comentario = jugador.getComentario();
    }
    
}
