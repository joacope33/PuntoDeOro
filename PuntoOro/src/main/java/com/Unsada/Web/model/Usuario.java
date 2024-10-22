package com.Unsada.Web.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Unsada.Web.repository.AdministradorRepository;

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
public class Usuario implements UserDetails {
    
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

    @Autowired
    private transient AdministradorRepository administradorRepository; // Inyectar el repositorio (asegúrate de que sea transitorio para evitar problemas de serialización)


    
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Verificar si el usuario es administrador y retornar la autoridad correspondiente
        if (isAdministrator()) {
            return Collections.singletonList(() -> "ROLE_ADMIN"); // Asignar el rol de administrador
        }
        return Collections.singletonList(() -> "ROLE_USER"); // Asignar rol de usuario general
    }

    // Método para verificar si el usuario es un administrador
    public boolean isAdministrator() {
        return administradorRepository.existsByUsuario(this); // Verifica si hay un administrador asociado
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombreCompleto=" + nombreCompleto + ", mail=" + mail + ", telefono=" + telefono
                + ", contrasena=" + contrasena + ", estado=" + estado + "]";
    }


    @Override
    public String getPassword() {
        return contrasena;
    }


    @Override
    public String getUsername() {
        return nombreCompleto;    
    }

    


}
