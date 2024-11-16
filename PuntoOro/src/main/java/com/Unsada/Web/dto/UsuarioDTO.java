package com.Unsada.Web.dto;


import com.Unsada.Web.model.Usuario;
import com.Unsada.Web.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    private Long id;
    private Role role;
    private String nombreCompleto;  
    private String email;
    private String telefono;
    private String contrasena;
    private int estado;


    public UsuarioDTO(String nombreCompleto, String email, String telefono, String contrasena, int estado) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;

    }

    public UsuarioDTO(Usuario usuario) {
        this.nombreCompleto = usuario.getNombreCompleto();
        this.email = usuario.getEmail();
        this.telefono = usuario.getTelefono();
        this.contrasena = usuario.getContrasena();
        this.estado = usuario.getEstado();

    }



}
