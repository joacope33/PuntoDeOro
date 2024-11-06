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
@Table(name = "auditoriaturnos")
@Getter
@Setter
public class AuditoriaTurno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idturnosreservado")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idjugador", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "idturno", nullable = false)
    private Turno turno;

    @Column(name = "fechayhora", nullable = false)
    private LocalDateTime fechaYHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    private TipoAccion accion;

    public AuditoriaTurno() {
    }

    public AuditoriaTurno(Jugador jugador, Turno turno, LocalDateTime fechaYHora, TipoAccion accion) {
        this.jugador = jugador;
        this.turno = turno;
        this.fechaYHora = fechaYHora;
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "AuditoriaTurnos [id=" + id + ", jugador=" + jugador + ", turno=" + turno + ", fechaYHora=" + fechaYHora + ", accion=" + accion + "]";
    }
}
