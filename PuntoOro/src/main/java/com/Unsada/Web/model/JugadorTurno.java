package com.Unsada.Web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idjugador", referencedColumnName = "idjugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "idturno", referencedColumnName = "idturno")
    private Turno turno;

    // Otros atributos si es necesario, como la fecha o el estado
}