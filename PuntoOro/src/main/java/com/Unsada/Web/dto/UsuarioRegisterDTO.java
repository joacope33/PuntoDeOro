package com.Unsada.Web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioRegisterDTO {
    
    private Long id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String contrasena;
    private int estado;
    
    public UsuarioRegisterDTO(String nombreCompleto, String email, String telefono, String contrasena, int estado) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public UsuarioRegisterDTO(String email) {
        super();
        this.email = email;
    }

    public UsuarioRegisterDTO() {
    }

    


}
