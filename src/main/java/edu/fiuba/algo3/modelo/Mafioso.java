package edu.fiuba.algo3.modelo;

import java.util.List;

public class Mafioso extends CartaRol{
    
    public Mafioso(Eleccion eleccion) {
        super(eleccion);
    }
    
    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        // NO HACE NADA
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores) {
        Jugador victima = asesinar(jugadores);
        victima.agregarVoto();
    }

    private Jugador asesinar(List<Jugador> jugadores) {
        Jugador victima = eleccion.elegirJugadorEntre(jugadores);
        if (!victima.estaVivo() || victima.esMafioso()) {
            throw new VictimaInvalidaException();
        }
        return victima;
    }
}
