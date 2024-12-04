package com.Unsada.Web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.Categoria;
import com.Unsada.Web.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }


    @Override
    public Categoria findById(Long idCategoria) {

        return categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria));
    }
       
        
    
}
