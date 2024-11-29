package com.Unsada.Web.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Unsada.Web.model.Categoria;
import com.Unsada.Web.repository.CategoriaRepository;

@Configuration
public class CategoriaInitializer {

    @Bean
    CommandLineRunner initCategorias(CategoriaRepository categoriaRepository) {
        return args -> {
            if (categoriaRepository.count() == 0) { // Evita duplicar datos
                categoriaRepository.save(new Categoria("1"));
                categoriaRepository.save(new Categoria("2"));
                categoriaRepository.save(new Categoria("3"));
                categoriaRepository.save(new Categoria("4"));
                categoriaRepository.save(new Categoria("5"));
                categoriaRepository.save(new Categoria("6"));
                categoriaRepository.save(new Categoria("7"));
                categoriaRepository.save(new Categoria("8"));
            }
        };
    }
}
