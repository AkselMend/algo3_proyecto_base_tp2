package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Finalizador {
    
    private List<Condicion> condiciones = new ArrayList<>();

    public boolean evaluar(List<Jugador> jugadores) {
        for (Condicion condicion : condiciones) {
            if (condicion.seCumple(jugadores)) {
                return true;
            }
        }
        return false;
    }
}
