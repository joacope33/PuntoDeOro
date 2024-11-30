package com.Unsada.Web.dto;

import java.time.LocalDate;

import com.Unsada.Web.model.Torneo;
import com.Unsada.Web.model.enums.EstadoTorneo;
import com.Unsada.Web.model.enums.FormatoTorneo;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class TorneoDTO {
    

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private FormatoTorneo formato; 
    private Long idCategoria; // ID de la categoría seleccionada


    private EstadoTorneo estado;

    public TorneoDTO(LocalDate fechaInicio, LocalDate fechaFin, Long idCategoria, EstadoTorneo estado) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.formato = formato;
        this.idCategoria = idCategoria;
        this.estado = estado;
    }

    public TorneoDTO() {
    }

    @Override
    public String toString() {
        return "Torneo [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", categoria="
                + idCategoria + ", estado=" + estado + "]";
    }
    
    public TorneoDTO(Torneo torneo) {
        this.id = torneo.getId();
        this.fechaInicio = torneo.getFechaInicio();
        this.fechaFin = torneo.getFechaFin();
        this.formato = torneo.getFormato();
        this.idCategoria = torneo.getCategoria() != null ? torneo.getCategoria().getId() : null; // Solo el ID
        this.estado = torneo.getEstado();
    }

}

