package com.Unsada.Web.model;

import java.sql.Timestamp;

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
@Table(name = "auditoriacanchas")
@Getter
@Setter
public class AuditoriaCancha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idauditoriacanchas")
    private Integer idAuditoriaCanchas;

    @ManyToOne
    @JoinColumn(name = "idcancha", nullable = false)
    private Cancha cancha;

    @ManyToOne
    @JoinColumn(name = "idadministrador", nullable = false)
    private Usuario administrador;

    @Column(name = "fechayhora", nullable = false)
    private Timestamp fechaYHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    private TipoAccion accion;

    // Constructor sin argumentos (requerido por JPA)
    public AuditoriaCancha() {
    }

    // Constructor completo con todos los atributos menos el ID
    public AuditoriaCancha(Cancha cancha, Usuario administrador, Timestamp fechaYHora, TipoAccion accion) {
        this.cancha = cancha;
        this.administrador = administrador;
        this.fechaYHora = fechaYHora;
        this.accion = accion;
    }

    // Constructor completo incluyendo ID
    public AuditoriaCancha(Integer idAuditoriaCanchas, Cancha cancha, Usuario administrador, Timestamp fechaYHora, TipoAccion accion) {
        this.idAuditoriaCanchas = idAuditoriaCanchas;
        this.cancha = cancha;
        this.administrador = administrador;
        this.fechaYHora = fechaYHora;
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "AuditoriaCancha [idAuditoriaCanchas=" + idAuditoriaCanchas + ", cancha=" + cancha + 
               ", administrador=" + administrador + ", fechaYHora=" + fechaYHora + ", accion=" + accion + "]";
    }
}

