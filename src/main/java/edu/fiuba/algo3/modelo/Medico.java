package edu.fiuba.algo3.modelo;

import java.util.List;

public class Medico extends CartaRol{
    
    private Jugador protegidoAnterior = null;
    
    public Medico(Eleccion eleccion) {
        super("Ciudadano",eleccion);
    }

    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        Jugador sospechoso = eleccion.elegirJugadorEntre(jugadores);
        this.chequearDefunsion(sospechoso);
        sospechoso.agregarVoto();
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores) {
        Jugador protegidoActual = eleccion.elegirJugadorEntre(jugadores);
        this.chequearDefunsion(protegidoActual);
        if (protegidoActual == protegidoAnterior) {
            throw new ProtegidoRepetidoException();
        }
        protegidoAnterior = protegidoActual;
        protegidoActual.protegerse();
    }
}
