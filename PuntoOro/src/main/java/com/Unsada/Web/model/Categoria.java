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
@Table(name = "categorias")
@Getter
@Setter
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    
}
