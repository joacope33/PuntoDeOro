package com.Unsada.Web.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.model.TurnosFijos;
import com.Unsada.Web.repository.TurnoFijoRepository;

@Service
public class TurnoFijoServiceImp implements TurnoFijoService {



    @Autowired
    TurnoFijoRepository turnoFijoRepository;

    @Override
    public List<TurnosFijos> findAllFijos() {
        try {
            return turnoFijoRepository.findAll();
        } catch (Exception e) {
            // Log error
            
            return Collections.emptyList(); // Devuelve una lista vacía en caso de error
        }
    }
} 
