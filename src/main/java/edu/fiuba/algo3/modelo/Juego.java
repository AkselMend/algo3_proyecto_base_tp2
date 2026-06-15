package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Juego {

    private Finalizador finalizador = new Finalizador();
    private List<Fase> fases = new ArrayList<>();

    public void jugar() {
        List<Jugador> jugadores = inicializar();
        int indice = 0;

        while (!finalizador.evaluar(jugadores)) {
            Fase faseActual = fases.get(indice);
            faseActual.ejecutar(jugadores);

            if (indice == fases.size() - 1) {
                indice = 0;
            } else {
                indice++;
            }
        }
    }

    public List<Jugador> inicializar(){
        // ACA SE DEBERIA LLAMAR A MAZO PARA QUE CREE LOS JUGADORES (POR CANTIDAD)
        // Y ASIGNE ROLES

        // HARDCODEAMOS JUGADORES PARA QUE NO SE QUEJE
        Eleccion eleccion = new Eleccion();
        List<Jugador> jugadores = new ArrayList<>();
        Jugador tomas = new Jugador("tomas", new Mafioso(eleccion));
        jugadores.add(tomas);

        return jugadores;
    }
}
