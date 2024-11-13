package com.Unsada.Web.model;

import java.sql.Time;
import java.util.List;

import com.Unsada.Web.model.enums.EstadoCancha;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "canchas")
@Getter
@Setter
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcancha")
    private Long id;
    
    @Column(name = "horarioapertura")
    private Time horarioApertura; 
    @Column(name = "horariocierre")
    private Time horarioCierre;
    @Column(name = "disponibilidad")
    private boolean disponibilidad;
    @Column(name = "duracion")
    private Time duracion;

    @JsonBackReference  // Evita que los turnos se serialicen dentro de Cancha, evitando recursión infinita
    @OneToMany(mappedBy = "cancha")
    private List<Turno> turnos;

    @Enumerated(EnumType.STRING)
    private EstadoCancha estado;

    

    public Cancha(Time horarioApertura, Time horarioCierre, boolean disponibilidad, Time duracion, List<Turno> turnos,
            EstadoCancha estado) {
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.disponibilidad = disponibilidad;
        this.duracion = duracion;
        this.turnos = turnos;
        this.estado = estado;
    }


    public Cancha(Time horarioApertura, Time horarioCierre, boolean disponibilidad, Time duracion,
            EstadoCancha estado) {
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.disponibilidad = disponibilidad;
        this.duracion = duracion;
        this.estado = estado;
    }


    public Cancha(Long id, Time horarioApertura, Time horarioCierre, boolean disponibilidad, Time duracion,
            EstadoCancha estado) {
        this.id = id;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.disponibilidad = disponibilidad;
        this.duracion = duracion;
        this.estado = estado;
    }

    public Cancha() {
    }


    @Override
    public String toString() {
        return "Cancha [id=" + id + ", horarioApertura=" + horarioApertura + ", horarioCierre=" + horarioCierre
                + ", disponibilidad=" + disponibilidad + ", duracion=" + duracion + ", estado=" + estado + "]";
    }

    

    
    

}
