package com.Unsada.Web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "partido")
@Getter
@Setter
public class Partido {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpartido")
    private Long id;

    @OneToOne
    @JoinColumn(name = "idparejaganadora")
    private Pareja parejaGanadora;
    
    @OneToOne
    @JoinColumn(name = "idparejaperdedora")
    private Pareja parejaPerdedora;

    @Column(name = "set1")
    private String setUno;
    @Column(name = "set2")
    private String setDos;
    @Column(name = "set3")
    private String setTres;

    @ManyToOne
    @JoinColumn(name = "idtorneo")
    private Torneo torneo;

    @OneToOne
    @JoinColumn(name = "idturno")
    private Turno turno;

    @Column(name = "asistenciapareja1")
    private int asistenciaParejaUno;
    @Column(name = "asistenciapareja2")
    private int asistenciaParejaDos;

    
    public Partido(Pareja parejaGanadora, Pareja parejaPerdedora, String setUno, String setDos, String setTres,
            Torneo torneo, Turno turno, int asistenciaParejaUno, int asistenciaParejaDos) {
        this.parejaGanadora = parejaGanadora;
        this.parejaPerdedora = parejaPerdedora;
        this.setUno = setUno;
        this.setDos = setDos;
        this.setTres = setTres;
        this.torneo = torneo;
        this.turno = turno;
        this.asistenciaParejaUno = asistenciaParejaUno;
        this.asistenciaParejaDos = asistenciaParejaDos;
    }


    public Partido() {
    }


    @Override
    public String toString() {
        return "Partido [id=" + id + ", parejaGanadora=" + parejaGanadora + ", parejaPerdedora=" + parejaPerdedora
                + ", setUno=" + setUno + ", setDos=" + setDos + ", setTres=" + setTres + ", torneo=" + torneo
                + ", turno=" + turno + ", asistenciaParejaUno=" + asistenciaParejaUno + ", asistenciaParejaDos="
                + asistenciaParejaDos + "]";
    }

    
    
}
