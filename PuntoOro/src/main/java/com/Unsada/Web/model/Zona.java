package com.Unsada.Web.model;

import jakarta.persistence.CascadeType;
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
@Table(name = "Zonas")
@Getter
@Setter
public class Zona {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idzona")
    private Long zonas;

    @Column(name = "nombrezona")
    private String nombreZona;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idtorneo", unique= true)
    private Torneo idTorneo;

}
