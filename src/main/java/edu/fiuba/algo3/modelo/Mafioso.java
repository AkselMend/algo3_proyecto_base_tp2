package edu.fiuba.algo3.modelo;

import java.util.List;

public class Mafioso extends CartaRol{

    public Mafioso(Eleccion eleccion) {
        super("Mafia",eleccion);
    }
    
    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        Jugador sospechoso = eleccion.elegirJugadorEntre(jugadores);
        this.chequearDefunsion(sospechoso);
        sospechoso.agregarVoto();
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores) {
        Jugador victima = asesinar(jugadores);
        victima.agregarVoto();
    }

    private Jugador asesinar(List<Jugador> jugadores) {
        Jugador victima = eleccion.elegirJugadorEntre(jugadores);
        this.chequearDefunsion(victima);
        if (victima.esMafioso()) {
            throw new VictimaEsMafiosoException();
        }
        return victima;
    }
}
