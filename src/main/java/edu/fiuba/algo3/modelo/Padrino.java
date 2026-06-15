package edu.fiuba.algo3.modelo;

import java.util.List;

public class Padrino extends CartaRol{
    public Padrino(Eleccion eleccion){
        super("Ciudadano",eleccion);
    }

    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores){

    }
}
