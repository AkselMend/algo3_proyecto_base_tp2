package edu.fiuba.algo3.modelo;

import java.util.List;

public class Detective extends CartaRol{
    
    private Jugador investigadoAnterior;

    public Detective(Eleccion eleccion){
        super("Ciudadano", eleccion);
    }

    @Override
    public void rolearDeDia(List<Jugador> jugadores){
        Jugador sospechoso = eleccion.elegirJugadorEntre(jugadores);
        this.chequearDefunsion(sospechoso);
        sospechoso.agregarVoto();
    }

    @Override
    public void rolearDeNoche(List<Jugador> jugadores){
        Jugador investigadoActual = eleccion.elegirJugadorEntre(jugadores);
        if (investigadoActual == investigadoAnterior) {
            throw new InvestigadoRepetidoException();
        }
        investigadoAnterior = investigadoActual;

        // ACA NO SE TODAVIA COMO SE HARIA PERO
        // DEBERIAMOS MOSTRARLE AL DETECVTIVE (JUGADOR ACTUAL)
        // EL ROL, POR AHORA PARA EL TEST, SIMPLEMENTE LO DEVOLVEMOS
        // CON METODO PARA TESTS
        this.revelarBandoDeInvestigado(investigadoActual);
    }

    public String revelarBandoDeInvestigado(Jugador investigadoActual){
        return investigadoActual.getBando();
    }
}