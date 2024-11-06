package com.Unsada.Web.model;

import java.time.LocalDateTime;

import com.Unsada.Web.model.enums.TipoAccion;

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
@Table(name = "auditoriajugadores")
@Getter
@Setter
public class AuditoriaJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idregistrojugadores")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idjugador", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fechayhora", nullable = false)
    private LocalDateTime fechaYHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    private TipoAccion accion;

    public AuditoriaJugador() {
    }

    public AuditoriaJugador(Jugador jugador, Usuario usuario, LocalDateTime fechaYHora, TipoAccion accion) {
        this.jugador = jugador;
        this.usuario = usuario;
        this.fechaYHora = fechaYHora;
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "AuditoriaJugadores [id=" + id + ", jugador=" + jugador + ", usuario=" + usuario + ", fechaYHora=" + fechaYHora + ", accion=" + accion + "]";
    }
}

