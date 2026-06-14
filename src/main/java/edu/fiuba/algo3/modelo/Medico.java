package edu.fiuba.algo3.modelo;

import java.util.List;

public class Medico extends CartaRol{
    
    private Jugador protegidoAnterior = null;
    
    public Medico(Eleccion eleccion) {
        super(eleccion);
    }

    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        // NO HACE NADA
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores) {
        Jugador protegidoActual = proteger(jugadores);
        Jugador jugadorConMasVotos = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugadorConMasVotos.otroJugadorMeSuperaEnVotos(jugador)) {
                jugadorConMasVotos = jugador;
            }
        }
        jugadorConMasVotos.mueroSiNoSoyProtegido(protegidoActual);
    }

    private Jugador proteger(List<Jugador> jugadores) {
        Jugador protegidoActual = eleccion.elegirJugadorEntre(jugadores);
        if (protegidoActual == protegidoAnterior || !protegidoActual.estaVivo()) {
            throw new ProtegidoInvalidoException();
        }
        protegidoAnterior = protegidoActual;
        return protegidoActual;
    }
}
