package edu.fiuba.algo3.modelo;

import java.util.List;

public class BallotageNocturno extends Ballotage {

    @Override
    protected Jugador volverAVotarEntre(List<Jugador> empatados, List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.reiniciarVotos();
        }
        for (Jugador jugador : jugadores) {
            if (jugador.esMafioso()) {
                jugador.ejecutarRolDeNocheSobre(empatados);
            }
        }
        return recuentoDeVotos(jugadores);
    }
}
