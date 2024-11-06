package com.Unsada.Web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.repository.CanchaRepository;

@Service
public class CanchaServiceImpl implements CanchaService {

    @Autowired
    private CanchaRepository canchaRepository;


    @Override
    public Cancha guardarCancha(CanchaDTO canchaDTO) {
        Cancha cancha = new Cancha(canchaDTO.getId(),
                                   canchaDTO.getHorarioApertura(),
                                   canchaDTO.getHorarioCierre(),
                                   canchaDTO.getDisponibilidad(),
                                   canchaDTO.getDuracion(),
                                   canchaDTO.getEstado()
         );

        return canchaRepository.save(cancha);
    }

    @Override
    public Cancha obtenerCanchabyId(Long id) {
        return canchaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró la cancha con ese Id: " + id));
    }
    
}
