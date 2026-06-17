package edu.fiuba.algo3.modelo;

import java.util.List;

public class BallotageDiurno extends Ballotage {

    @Override
    protected Jugador volverAVotarEntre(List<Jugador> empatados, List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.reiniciarVotos();
        }
        for (Jugador jugador : jugadores) {
            jugador.ejecutarRolDeDiaSobre(empatados);
        }
        return recuentoDeVotos(jugadores);
    }
}
