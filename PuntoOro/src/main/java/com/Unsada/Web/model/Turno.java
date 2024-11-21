package com.Unsada.Web.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.Unsada.Web.model.enums.EstadoCancha;
import com.Unsada.Web.model.enums.TipoTurno;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turnos")
@Getter
@Setter
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idturno")
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
    
    // Relación Muchos a Muchos con Jugador
    @ManyToMany(fetch= FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
        name = "jugadores_turnos",  // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "idturno",referencedColumnName="idturno"),  // Columna de clave foránea en la tabla intermedia para jugadores
        inverseJoinColumns = @JoinColumn(name = "idjugador", referencedColumnName="idjugador") // Columna de clave foránea en la tabla intermedia para turnos
    )
    @JsonManagedReference  // Gestiona la relación de Turno a Jugador
    private List<Jugador> jugadores;  // Colección de jugadores asociados a este turno

    
    public Turno(LocalDate dia, Time hora, Cancha cancha, EstadoCancha estado, TipoTurno tipoTurno, int asistencia, Partido partido, List<Jugador> jugadores) {
        this.dia = dia;
        this.hora = hora;
        this.cancha = cancha;
        this.estado = estado;
        this.tipoTurno = tipoTurno;
        this.asistencia = asistencia;
        this.partido = partido;
        this.jugadores= jugadores;
    }


    public Turno() {
    }


    @Override
    public String toString() {
        return "Turno [id=" + id + ", dia=" + dia + ", hora=" + hora + ", cancha=" + cancha + ", estado=" + estado
                + ", tipoTurno=" + tipoTurno + ", asistencia=" + asistencia + ", partido=" + partido + "]";
    }


    

    

    
}
