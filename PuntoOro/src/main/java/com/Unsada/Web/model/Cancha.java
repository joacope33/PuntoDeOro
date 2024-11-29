package com.Unsada.Web.model;

import java.time.LocalTime;
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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "canchas")
@Getter
@Setter
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancha_seq")
    @SequenceGenerator(name = "cancha_seq", sequenceName = "canchas_idcancha_seq", allocationSize = 1)
    @Column(name = "idcancha")
    private Long id;

    
    @Column(name = "horarioapertura")
    private LocalTime  horarioApertura; 
    @Column(name = "horariocierre")
    private LocalTime  horarioCierre;
    @Column(name = "disponibilidad")
    private boolean disponibilidad;
    @Column(name = "duracion")
    private LocalTime  duracion;

    @JsonBackReference  // Evita que los turnos se serialicen dentro de Cancha, evitando recursión infinita
    @OneToMany(mappedBy = "cancha")
    private List<Turno> turnos;

    @Enumerated(EnumType.STRING)
    private EstadoCancha estado;

    

    public Cancha(LocalTime  horarioApertura, LocalTime  horarioCierre, boolean disponibilidad, LocalTime  duracion, List<Turno> turnos,
            EstadoCancha estado) {
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.disponibilidad = disponibilidad;
        this.duracion = duracion;
        this.turnos = turnos;
        this.estado = estado;
    }


    public Cancha(LocalTime  horarioApertura, LocalTime  horarioCierre, boolean disponibilidad, LocalTime  duracion,
            EstadoCancha estado) {
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.disponibilidad = disponibilidad;
        this.duracion = duracion;
        this.estado = estado;
    }


    public Cancha(Long id, LocalTime  horarioApertura, LocalTime  horarioCierre, boolean disponibilidad, LocalTime  duracion,
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
