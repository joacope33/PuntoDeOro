package com.Unsada.Web.dto;

import java.time.LocalDate;

import com.Unsada.Web.model.Categoria;
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
    private Categoria categoria;


    private EstadoTorneo estado;

    public TorneoDTO(LocalDate fechaInicio, LocalDate fechaFin, Categoria categoria, EstadoTorneo estado) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.formato = formato;
        this.categoria = categoria;
        this.estado = estado;
    }

    public TorneoDTO() {
    }

    @Override
    public String toString() {
        return "Torneo [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", categoria="
                + categoria + ", estado=" + estado + "]";
    }
    
    public TorneoDTO(Torneo torneo) {
        this.fechaInicio = torneo.getFechaInicio();
        this.fechaFin = torneo.getFechaFin();
        this.formato = torneo.getFormato();
        this.categoria = torneo.getCategoria();
        this.estado = torneo.getEstado();
    }

}

