package com.Unsada.Web.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jugadores")
@Getter
@Setter
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idjugador")
    private Long id;
    
    @Column(name = "nombrecompleto")
    private String nombreCompleto;
    @Column(name = "telefono")
    private String telefono;

    @OneToMany
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;
    
    @Column(name = "fechadenacimiento")
    private Date fechaDeNacimiento;
    @Column(name = "dni")
    private String dni;
    @Column(name = "calificacion")
    private int calificacion;
    @Column(name = "puntos")
    private int puntos;
    @Column(name = "comentario")
    private String comentario;

    
    public Jugador(String nombreCompleto, String telefono, Categoria categoria, Date fechaDeNacimiento, String dni,
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


    


}
