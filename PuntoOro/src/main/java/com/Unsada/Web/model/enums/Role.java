package com.Unsada.Web.model.enums;

public enum Role {
    ADMIN("Administrador - tiene acceso completo"),
    USER("Usuario - acceso limitado");

    private final String descripcion;

    Role(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
