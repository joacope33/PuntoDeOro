package com.Unsada.Web.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "torneos")
@Getter
@Setter
public class Torneo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtorneo")
    private Long id;
    
    @Column(name = "fechainicio")
    private LocalDate fechaInicio;
    @Column(name = "fechafin")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;

    @Column(name = "estado")
    private String estado;

    public Torneo(LocalDate fechaInicio, LocalDate fechaFin, Categoria categoria, String estado) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Torneo() {
    }

    @Override
    public String toString() {
        return "Torneo [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", categoria="
                + categoria + ", estado=" + estado + "]";
    }
    
    

}
