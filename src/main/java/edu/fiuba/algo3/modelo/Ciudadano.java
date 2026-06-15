package edu.fiuba.algo3.modelo;

import java.util.List;

public class Ciudadano extends CartaRol{
    
    public Ciudadano(Eleccion eleccion){
        super("Ciudadano",eleccion);
    }
    
    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        Jugador sospechoso = eleccion.elegirJugadorEntre(jugadores);
        this.chequearDefunsion(sospechoso);
        sospechoso.agregarVoto();
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores){
        // NADA
    }
}
