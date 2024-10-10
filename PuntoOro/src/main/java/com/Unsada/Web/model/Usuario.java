package com.Unsada.Web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idusuario")
    private Long id;

    @Column(name = "nombrecompleto")
    private String nombreCompleto;
    @Column(name = "correoelectronico")
    private String mail;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "contraseña")
    private String contrasena;
    @Column(name = "estado")
    private int estado;

    
    public Usuario(String nombreCompleto, String mail, String telefono, String contrasena, int estado) {
        this.nombreCompleto = nombreCompleto;
        this.mail = mail;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
    }


    public Usuario() {
    }


    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombreCompleto=" + nombreCompleto + ", mail=" + mail + ", telefono=" + telefono
                + ", contrasena=" + contrasena + ", estado=" + estado + "]";
    }

    


}
