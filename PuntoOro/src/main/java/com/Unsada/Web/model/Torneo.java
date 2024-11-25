package com.Unsada.Web.model;

import java.time.LocalDate;

import com.Unsada.Web.model.enums.EstadoTorneo;
import com.Unsada.Web.model.enums.FormatoTorneo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
<<<<<<< HEAD
    private LocalDate fechaInicio;
    @Column(name = "fechafin")
    private LocalDate fechaFin;
=======
    private Date fechaInicio;

    @Column(name = "fechafin")  
    private Date fechaFin;
>>>>>>> a64c3847b6d11d13954e3ff72ebda3b7280f16ac

    @Enumerated(EnumType.STRING)
    private FormatoTorneo formato; 

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private EstadoTorneo estado;

<<<<<<< HEAD
    public Torneo(LocalDate fechaInicio, LocalDate fechaFin, Categoria categoria, String estado) {
=======
    public Torneo(Date fechaInicio, Date fechaFin,FormatoTorneo formato, Categoria categoria, EstadoTorneo estado) {
>>>>>>> a64c3847b6d11d13954e3ff72ebda3b7280f16ac
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.formato = formato;
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
