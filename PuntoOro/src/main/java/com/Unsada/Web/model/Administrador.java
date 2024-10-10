package com.Unsada.Web.model;

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
@Table(name = "administradores")
@Getter
@Setter
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idadministrador")
    private Long id;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Administrador(Usuario usuario) {
        this.usuario = usuario;
    }

    public Administrador() {
    }

    
}
