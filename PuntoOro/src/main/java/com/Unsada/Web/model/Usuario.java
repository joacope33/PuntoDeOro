package com.Unsada.Web.model;

import com.Unsada.Web.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    


    public Usuario(String nombreCompleto, String email, String telefono, String contrasena, int estado, Role role) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
        this.role = role;
        

    }



    
    // Getters y setters
    public String getRoleName() {
        return role.name();  // Esto devuelve "ADMIN" o "USER"
    }
    
    



    


}
