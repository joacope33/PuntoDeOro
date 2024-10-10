package com.Unsada.Web.model;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idturno")
    private Long id;

    @Column(name = "dia")
    private Date dia;

    @Column(name = "hora")
    private Time hora;

    @OneToOne
    @JoinColumn(name = "idcancha")
    private Cancha cancha;

    @Column(name = "estado")
    private String estado;
    @Column(name = "estorneo")
    private int esTorneo;
    @Column(name = "asistencia")
    private int asistencia;


    public Turno(Date dia, Time hora, Cancha cancha, String estado, int esTorneo, int asistencia) {
        this.dia = dia;
        this.hora = hora;
        this.cancha = cancha;
        this.estado = estado;
        this.esTorneo = esTorneo;
        this.asistencia = asistencia;
    }


    public Turno() {
    }


    @Override
    public String toString() {
        return "Turno [id=" + id + ", dia=" + dia + ", hora=" + hora + ", cancha=" + cancha + ", estado=" + estado
                + ", esTorneo=" + esTorneo + ", asistencia=" + asistencia + "]";
    }

    

    
}
