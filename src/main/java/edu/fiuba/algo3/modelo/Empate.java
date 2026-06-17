package edu.fiuba.algo3.modelo;

import java.util.List;

public class Empate extends AnalistaElectoral {
    @Override
    protected Jugador hayEmpatados(List<Jugador> empatados, List<Jugador> jugadores) {
        if (empatados.size() > 1) {
            throw new FaseSinMuertosException();
        }

        return empatados.get(0);
    }
}
