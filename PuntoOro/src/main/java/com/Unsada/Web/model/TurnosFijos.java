package com.Unsada.Web.model;

import java.sql.Time;
import java.time.LocalDate;


import com.Unsada.Web.model.enums.EstadoCancha;
import com.Unsada.Web.model.enums.TipoTurno;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "turnos_fijos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TurnosFijos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idturnofijo")
    private Long id;

    @Column(name = "dia")
    private LocalDate dia;

    @Column(name = "hora")
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "idcancha")
    @JsonManagedReference  // Evita la recursión infinita serializando solo el lado principal de la relación
    private Cancha cancha;

    @Enumerated(EnumType.STRING)
    private EstadoCancha estado;

    @Enumerated(EnumType.STRING)
    private TipoTurno tipoTurno;
    
    @Column(name = "asistencia")
    private int asistencia;

    @OneToOne
    @JoinColumn(name = "idpartido")
    private Partido partido;
    
    @Column(name = "cantidad_turnos_restantes")
    private int cantidad_turnos_restantes;
   

}