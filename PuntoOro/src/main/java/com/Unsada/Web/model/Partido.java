package com.Unsada.Web.model;

import com.Unsada.Web.model.enums.FaseDePartidos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "partido")
@Getter
@Setter
@ToString
public class Partido {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpartido")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FaseDePartidos fase;

    @JoinColumn(name = "idzona", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Zona zona;

    @JoinColumn(name = "idpareja_ganadora", nullable = true)
    @OneToOne
    private Pareja ganadora;

    @JoinColumn(name = "idpareja_equipo1")
    @OneToOne
    private Pareja equipo1;

    @JoinColumn(name = "idpareja_equipo2")
    @OneToOne
    private Pareja equipo2;

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

    @Column(name ="puntos_equipo1")
    private int puntos_equipo1;

    @Column(name ="puntos_equipo2")
    private int puntos_equipo2;


    public Partido() {
    }

    public Partido(Pareja equipo1, Pareja equipo2, FaseDePartidos fase, Torneo torneo, Zona zona) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fase = fase;
        this.torneo = torneo;
        this.zona = zona;
    }


    
}
