package com.Unsada.Web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Unsada.Web.dto.CanchaDTO;
import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.Turno;
import com.Unsada.Web.repository.CanchaRepository;

import jakarta.persistence.EntityNotFoundException;

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
    public Cancha findById(Long id) {
        return canchaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró la cancha con ese Id: " + id));
    }

    @Override
    public void actualizarCancha(CanchaDTO canchaDTO) {
        // Busca la cancha existente en la base de datos
        Cancha cancha = canchaRepository.findById(canchaDTO.getId())
                 .orElseThrow(() -> new EntityNotFoundException("Cancha no encontrada con id: " + canchaDTO.getId()));


        if (cancha != null) {
            // Actualiza los campos con los valores del DTO
            cancha.setHorarioApertura(canchaDTO.getHorarioApertura());
            cancha.setHorarioCierre(canchaDTO.getHorarioCierre());
            cancha.setDisponibilidad(canchaDTO.getDisponibilidad());
            cancha.setDuracion(canchaDTO.getDuracion());
            cancha.setEstado(canchaDTO.getEstado());

            // Guarda la entidad actualizada
            canchaRepository.save(cancha);
        } else {
        throw new EntityNotFoundException("Jugador no encontrado con dni: " + canchaDTO.getId());
        }
    }

    @Override
    public List<Turno> mostrarTurnos(CanchaDTO canchaDTO) {
        try{
            Cancha cancha = canchaRepository.findById(canchaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la cancha con el id: " + canchaDTO.getId()));
            return cancha.getTurnos();

        }catch(Exception e){
            throw new RuntimeException("No se encontraron canchas");
        }
    }

    @Override
    public List<Cancha> obtenerTodasLasCanchas() {
       try {
            return canchaRepository.findAll();
       } catch (Exception e) {
        throw new RuntimeException("No se encontraron canchas");
       }
    }

    @Override
    public void actualizarCanchaDesdeDTO(CanchaDTO canchaDTO) {
       // Busca el jugador existente en la base de datos
        Cancha cancha = canchaRepository.findById(canchaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("No se encontró la cancha con ese Id: " + canchaDTO.getId()));

        if (cancha != null) {
            // Actualiza los campos con los valores del DTO
            cancha.setHorarioApertura(canchaDTO.getHorarioApertura());
            cancha.setHorarioCierre(canchaDTO.getHorarioCierre());
            cancha.setDisponibilidad(canchaDTO.getDisponibilidad());
            cancha.setDuracion(canchaDTO.getDuracion());

            // Guarda la entidad actualizada
            canchaRepository.save(cancha);
        } else {
        throw new EntityNotFoundException("Cancha no encontrada con id: " + canchaDTO.getId());
        }
    }

    @Override
    public void actualizarCancha(Cancha cancha) {
       try {
           // Guarda la entidad actualizada
           canchaRepository.save(cancha);
       } catch (Exception e) {
        throw new EntityNotFoundException("Cancha no se pudo guardar la cancha");
       }
    }

    @Override
    public List<Cancha> obtenerTodasLasCanchasOrdenadas() {
        try {
            return canchaRepository.findAllByOrderByIdAsc();
       } catch (Exception e) {
        throw new RuntimeException("No se encontraron canchas");
       }
    }
    
    @Override
    public List<Cancha> getAll(){
        return canchaRepository.findAll();
    }

    @Override
    public Cancha obtenerCanchabyId(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
