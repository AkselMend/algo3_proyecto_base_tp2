package edu.fiuba.algo3.modelo;

import java.util.List;

public class Dia implements Fase{
    
    @Override
    public void ejecutar(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.ejecutarRolDeDiaSobre(jugadores);
        }
        Jugador jugadorConMasVotos = this.buscarMasVotado(jugadores);
        jugadorConMasVotos.recibirDisparo();
    }

    private Jugador buscarMasVotado(List<Jugador> jugadores){
        Jugador jugadorConMasVotos = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            if (jugadorConMasVotos.otroJugadorMeSuperaEnVotos(jugador)) {
                jugadorConMasVotos = jugador;
            }
        }
        return jugadorConMasVotos;
    }
}
