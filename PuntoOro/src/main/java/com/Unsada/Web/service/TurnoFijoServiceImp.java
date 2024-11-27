package com.Unsada.Web.service;

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
    public List<TurnosFijos> findAllFijos(){
        return turnoFijoRepository.findAll();

}
} 
