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
<<<<<<< HEAD
        this.estado = estado != 0 ? estado : 1; // Si estado es 0, asigna un valor predeterminado
        this.role = role;  // Inicializamos el role
=======
        this.estado = estado;
        this.role = Role.USER;  // Inicializamos el role
>>>>>>> a64c3847b6d11d13954e3ff72ebda3b7280f16ac
    }

    public UsuarioRegisterDTO(String email) {
        super();
        this.email = email;
    }

   

    


}
