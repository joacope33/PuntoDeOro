package com.Unsada.Web.model;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Cancha(Time horarioApertura, Time horarioCierre, boolean disponibilidad, Time duracion) {
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.disponibilidad = disponibilidad;
        this.duracion = duracion;
    }

    public Cancha() {
    }

    @Override
    public String toString() {
        return "Cancha [id=" + id + ", horarioApertura=" + horarioApertura + ", horarioCierre=" + horarioCierre
                + ", disponibilidad=" + disponibilidad + ", duracion=" + duracion + "]";
    }

    
    

}
