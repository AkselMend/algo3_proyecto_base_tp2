package edu.fiuba.algo3.modelo;

import java.util.List;

public class Jugador {
    private String nombre;
    private boolean vivo = true;
    private boolean protegido = false;
    private Integer votos = 0;
    private CartaRol rol;

    public Jugador(String nombre, CartaRol rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public void ejecutarRolDeDiaSobre(List<Jugador> jugadores){
        if (!vivo) {
            throw new JugadorMuertoException();
        } 
        rol.rolearDeDia(jugadores);
    }

    public void ejecutarRolDeNocheSobre(List<Jugador> jugadores){
        if (!vivo) {
            throw new JugadorMuertoException();
        }
        rol.rolearDeNoche(jugadores);
    }

    public void protegerse(){
        protegido = true;
    }

    public void recibirDisparo(){
        if (!protegido) {
            vivo = false;        
        } else {
            protegido = false;
        }
    }

    public void agregarVoto(){
        votos += 1;
    }

    public boolean otroJugadorMeSuperaEnVotos(Jugador jugador){
        return jugador.tengoMasVotos(votos);
    }

    public boolean tengoMasVotos(Integer votos){
        boolean respuesta = this.votos > votos;
        votos = 0;
        return respuesta;
    }

    public boolean esMafioso(){
        return rol.esMafioso();
    }

    public boolean estaVivo(){
        return vivo;
    }

    public String getBando(){
        return rol.getBando();
    }
}
