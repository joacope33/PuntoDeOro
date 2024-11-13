package com.Unsada.Web.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jugadores")
@Getter
@Setter
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjugador")
    private Long id;
    
    @Column(name = "nombrecompleto")
    private String nombreCompleto;
    
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "categoria")
    private String categoria;
    @Column(name = "fechadenacimiento")
    private LocalDate fechaDeNacimiento;
    @Column(name = "dni", unique=true)
    private String dni;
    @Column(name = "calificacion")
    private int calificacion;
    @Column(name = "puntos")
    private int puntos;
    @Column(name = "comentario")
    private String comentario;

    // Relación Muchos a Muchos con Turno
    @ManyToMany
    @JoinTable(
        name = "jugadores_turnos",  // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "idjugador"),  // Columna de clave foránea en la tabla intermedia para jugadores
        inverseJoinColumns = @JoinColumn(name = "idturno")  // Columna de clave foránea en la tabla intermedia para turnos
    )
    @JsonBackReference  // Evita la recursión infinita serializando solo el lado secundario de la relación
    private Set<Turno> turnos;  // Colección de turnos asociados


    
    public Jugador(String nombreCompleto, String telefono, String categoria, LocalDate fechaDeNacimiento, String dni,
            int calificacion, int puntos, String comentario) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.categoria = categoria;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.dni = dni;
        this.calificacion = calificacion;
        this.puntos = puntos;
        this.comentario = comentario;
    }


    public Jugador() {
    }


    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", categoria="
                + categoria + ", fechaDeNacimiento=" + fechaDeNacimiento + ", dni=" + dni + ", calificacion="
                + calificacion + ", puntos=" + puntos + ", comentario=" + comentario + "]";
    }


   
}
