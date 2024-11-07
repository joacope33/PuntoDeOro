package com.Unsada.Web.model;
//import time;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long id;

    
    @Column(name = "categoria")
    private String categoria;

    
    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria() {
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", categoria=" + categoria + "]";
    }

    
    public void setCategoria(String categoria) {
        List<String> categoriasPermitidas = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", null);
        if (categoriasPermitidas.contains(categoria)) {
            this.categoria = categoria;
        } else {
            throw new IllegalArgumentException("La categoría debe estar entre 1 y 8 o ser nula.");
        }
    }

    
}
