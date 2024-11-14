package com.Unsada.Web.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<TurnoDTO> turnos; // Relación muchos a muchos con turnos



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
        if (jugador != null) {
            this.id = jugador.getId();
            this.nombreCompleto = jugador.getNombreCompleto();
            this.telefono = jugador.getTelefono();
            this.categoria = jugador.getCategoria();
            this.fechaDeNacimiento = jugador.getFechaDeNacimiento();
            this.dni = jugador.getDni();
            this.calificacion = jugador.getCalificacion();
            this.puntos = jugador.getPuntos();
            this.comentario = jugador.getComentario();
            this.turnos = jugador.getTurnos().stream()
                                .map(turno -> new TurnoDTO()) // Aquí mapeas los turnos si es necesario
                                .collect(Collectors.toSet());
        } else {
            // Manejar el caso cuando el jugador es null
            throw new IllegalArgumentException("Jugador no encontrado");
        }
    }
    
}
