package com.Unsada.Web.config;

import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Unsada.Web.model.Cancha;
import com.Unsada.Web.model.enums.EstadoCancha;
import com.Unsada.Web.repository.CanchaRepository;

@Configuration
public class DatabaseInitializer  {
       @Bean
    public CommandLineRunner initializeCanchas(CanchaRepository canchaRepository) {
        return args -> {
            if (canchaRepository.count() == 0) { // Solo si no hay canchas en la base de datos
                Cancha cancha1 = new Cancha(null, LocalTime.of(8, 0), LocalTime.of(22, 0), true, LocalTime.of(1, 0), EstadoCancha.DISPONIBLE);
                Cancha cancha2 = new Cancha(null, LocalTime.of(8, 0), LocalTime.of(22, 0), true, LocalTime.of(1, 0), EstadoCancha.DISPONIBLE);
                Cancha cancha3 = new Cancha(null, LocalTime.of(8, 0), LocalTime.of(22, 0), true, LocalTime.of(1, 0), EstadoCancha.DISPONIBLE);
                Cancha cancha4 = new Cancha(null, LocalTime.of(8, 0), LocalTime.of(22, 0), true, LocalTime.of(1, 0), EstadoCancha.DISPONIBLE);

                canchaRepository.saveAll(Arrays.asList(cancha1, cancha2, cancha3, cancha4));
            }
        };
    }
}
