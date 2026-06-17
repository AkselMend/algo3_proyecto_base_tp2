package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class AnalistaElectoral {
    public void buscarMasVotado(List<Jugador> jugadores) {
        Jugador jugadorMasVotado = recuentoDeVotos(jugadores);
        jugadorMasVotado.recibirDisparo();
    }

    public Jugador recuentoDeVotos(List<Jugador> jugadores) {
        List<Jugador> empatados = new ArrayList<>();
        Jugador jugadorConMasVotos = jugadores.get(0);

        for (Jugador jugador : jugadores) {
            if (jugadorConMasVotos.otroJugadorMeSuperaEnVotos(jugador)) {
                jugadorConMasVotos = jugador;
                empatados.clear();
            }
            if (jugadorConMasVotos.otroJugadorMeIgualaEnVotos(jugador)) {
                empatados.add(jugador);
            }
        }
        return hayEmpatados(empatados, jugadores);
    }

    protected abstract Jugador hayEmpatados(List<Jugador> empatados, List<Jugador> jugadores);
}
