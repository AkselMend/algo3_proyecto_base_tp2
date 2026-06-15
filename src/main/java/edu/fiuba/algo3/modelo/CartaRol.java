package edu.fiuba.algo3.modelo;

import java.util.List;

public abstract class CartaRol implements RolNocturno, RolDiurno{
    
    protected String bando;
    protected Eleccion eleccion;

    public CartaRol(String bando, Eleccion eleccion){
        this.eleccion = eleccion;
        this.bando = bando;
    }

    public abstract void rolearDeDia(List<Jugador> jugadores);

    public abstract void rolearDeNoche(List<Jugador> jugadores);

    public boolean esMafioso(){
        return bando.equals("Mafia");
    }

    public String getBando(){
        return bando;
    }

    protected void chequearDefunsion(Jugador elegido){
        if(!elegido.estaVivo()){
            throw new JugadorMuertoException();
        }
    }
}
