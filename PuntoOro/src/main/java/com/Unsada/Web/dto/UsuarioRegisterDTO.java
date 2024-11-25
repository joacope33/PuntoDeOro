package com.Unsada.Web.dto;

import com.Unsada.Web.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegisterDTO {
    
    private Long id;
    private Role role;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String contrasena;
    private int estado;
    
     // Constructor con todos los atributos
     public UsuarioRegisterDTO(String nombreCompleto, String email, String telefono, String contrasena, int estado, Role role) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
        this.role = Role.USER;  // Inicializamos el role
    }

    public UsuarioRegisterDTO(String email) {
        super();
        this.email = email;
    }

   

    


}
