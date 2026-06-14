package edu.fiuba.algo3.modelo;

import java.util.List;

public class Noche implements Fase{
    
    @Override
    public void ejecutar(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.ejecutarRolDeNocheSobre(jugadores);
        }
    }
}
