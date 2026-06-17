package edu.fiuba.algo3.modelo;

import java.util.List;

public class Dia implements Fase{
    //private AnalistaElectoral analistaElectoral;

    //public Dia(AnalistaElectoral analistaElectoral){
    //    this.analistaElectoral = analistaElectoral;
    //}

    @Override
    public void ejecutar(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.ejecutarRolDeDiaSobre(jugadores);
        }
        Jugador jugadorConMasVotos = this.buscarMasVotado(jugadores);
        jugadorConMasVotos.recibirDisparo();
        for (Jugador jugador : jugadores) {
            jugador.reiniciarVotos();
        }
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
    
    //@Override
    //public void ejecutar(List<Jugador> jugadores) {
    //    for (Jugador jugador : jugadores) {
    //        jugador.ejecutarRolDeDiaSobre(jugadores);
    //    }
    //    analistaElectoral.buscarMasVotado(jugadores);
    //    for (Jugador jugador : jugadores) {
    //        jugador.reiniciarVotos();
    //    }
    //}
}
