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
@Table(name = "auditorialogin")
@Getter
@Setter
public class AuditoriaLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idauditorialogin")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fechayhora", nullable = false)
    private LocalDateTime fechaYHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    private TipoAccion accion;

    public AuditoriaLogin() {
    }

    public AuditoriaLogin(Usuario usuario, LocalDateTime fechaYHora, TipoAccion accion) {
        this.usuario = usuario;
        this.fechaYHora = fechaYHora;
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "AuditoriaLogin [id=" + id + ", usuario=" + usuario + ", fechaYHora=" + fechaYHora + ", accion=" + accion + "]";
    }
}
