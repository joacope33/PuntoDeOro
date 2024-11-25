package com.Unsada.Web.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parejas")
@Getter
@Setter
public class Pareja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpareja")
    private Long id;

    @Column(name="nombrepareja")
    private String nombrePareja;
    
    @ManyToOne
    @JoinColumn(name = "idjugador1")
    private Jugador jugador1;

    @ManyToOne
    @JoinColumn(name = "idjugador2")
    private Jugador jugador2;

    @ManyToOne
    @JoinColumn(name = "idtorneo")
    private Torneo torneo;

    public Pareja(Jugador jugador1, Jugador jugador2, Torneo torneo) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.torneo = torneo;
    }

    public Pareja() {
    }

    @Override
    public String toString() {
        return "Pareja [id=" + id + ", jugador1=" + jugador1 + ", jugador2=" + jugador2 + ", torneo=" + torneo + "]";
    }

    
    

}
