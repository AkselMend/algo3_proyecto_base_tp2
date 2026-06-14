package edu.fiuba.algo3.modelo;

import java.util.List;

public class Jugador {
    private String nombre;
    private String estado = "vivo";
    private String bando;
    private Integer votos = 0;
    private CartaRol rol;

    public Jugador(String nombre, String bando, CartaRol rol) {
        this.nombre = nombre;
        this.bando = bando;
        this.rol = rol;
    }

    public Jugador(String nombre, String bando) {
        this(nombre, bando, null);
    }

    public void ejecutarRolDeDiaSobre(List<Jugador> jugadores){
        rol.rolearDeDia(jugadores);
    }

    public void ejecutarRolDeNocheSobre(List<Jugador> jugadores){
        rol.rolearDeNoche(jugadores);
    }

    public void mueroSiNoSoyProtegido(Jugador protegido){
        if (this != protegido) {
            estado = "muerto";        
        }
    }

    public void agregarVoto(){
        votos += 1;
    }

    public boolean otroJugadorMeSuperaEnVotos(Jugador jugador){
        return jugador.tengoMasVotos(votos);
    }

    public boolean tengoMasVotos(Integer votos){
        return this.votos > votos;
    }

    public boolean esMafioso(){
        return bando.equals("mafioso");
    }

    public boolean estaVivo(){
        return estado.equals("vivo");
    }
}
