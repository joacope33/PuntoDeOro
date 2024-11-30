package com.Unsada.Web.model;

import java.time.LocalDate;

import com.Unsada.Web.dto.TorneoDTO;
import com.Unsada.Web.model.enums.EstadoTorneo;
import com.Unsada.Web.model.enums.FormatoTorneo;
import com.Unsada.Web.repository.CategoriaRepository;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "torneos")
@Getter
@Setter
public class Torneo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "torneo_seq")
    @SequenceGenerator(name = "torneo_seq", sequenceName = "torneos_idtorneo_seq", allocationSize = 1)
    @Column(name = "idtorneo")
    private Long id;
    
    @Column(name = "fechainicio")
    private LocalDate fechaInicio;
    @Column(name = "fechafin")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private FormatoTorneo formato; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategoria", nullable = false)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private EstadoTorneo estado;

    public Torneo(LocalDate fechaInicio, LocalDate fechaFin, Categoria categoria, EstadoTorneo estado) {
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

    public Torneo(TorneoDTO torneoDTO, CategoriaRepository categoriaRepository) {
        this.id = torneoDTO.getId();
        this.fechaInicio = torneoDTO.getFechaInicio();
        this.fechaFin = torneoDTO.getFechaFin();
        this.formato = torneoDTO.getFormato();
        // Buscar la categoría por ID usando el repositorio
        this.categoria = categoriaRepository.findById(torneoDTO.getIdCategoria())
                                            .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        this.estado = torneoDTO.getEstado();
    }
    
    

}
