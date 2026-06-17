package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class Ballotage extends AnalistaElectoral{
    @Override
    protected Jugador hayEmpatados(List<Jugador> empatados, List<Jugador> jugadores) {
        if (empatados.size() > 1) {
            return volverAVotarEntre(empatados, jugadores);
        }
        return empatados.get(0);
    }

    protected abstract Jugador volverAVotarEntre(List<Jugador> empatados, List<Jugador> jugadores);
}
