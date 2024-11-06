package com.Unsada.Web.model;

import java.util.Collection;

import com.Unsada.Web.model.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios", uniqueConstraints= @UniqueConstraint(columnNames="correoelectronico"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "nombrecompleto")
    private String nombreCompleto;
    @Column(name = "correoelectronico", unique=true)
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "contraseña")
    private String contrasena;
    @Column(name = "estado")
    private int estado;
    
    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles", 
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName= "id")
    )
    private Collection<Rol> roles;


    public Usuario(String nombreCompleto, String email, String telefono, String contrasena, int estado, Collection<Rol> roles) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
        this.roles = roles;
    }



    
    
    
    



    


}
