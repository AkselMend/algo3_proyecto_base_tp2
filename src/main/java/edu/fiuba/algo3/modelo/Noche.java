package edu.fiuba.algo3.modelo;

import java.util.List;

public class Noche implements Fase{
    //private AnalistaElectoral analistaElectoral;

    //public Noche(AnalistaElectoral analistaElectoral){
    //    this.analistaElectoral = analistaElectoral;
    //}

    @Override
    public void ejecutar(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.ejecutarRolDeNocheSobre(jugadores);
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
    //        jugador.ejecutarRolDeNocheSobre(jugadores);
    //    }
    //    analistaElectoral.buscarMasVotado(jugadores);
    //    for (Jugador jugador : jugadores) {
    //        jugador.reiniciarVotos();
    //    }
    //}
}
