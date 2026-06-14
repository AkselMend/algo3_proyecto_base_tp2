package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class CartaRol implements RolNocturno, RolDiurno{
    
    protected Eleccion eleccion;

    public CartaRol(Eleccion eleccion){
        this.eleccion = eleccion;
    }

    public abstract void rolearDeDia(List<Jugador> jugadores);

    public abstract void rolearDeNoche(List<Jugador> jugadores);
}
