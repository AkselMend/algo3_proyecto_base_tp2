package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.fiuba.algo3.modelo.Ciudadano;
import edu.fiuba.algo3.modelo.Dia;
import edu.fiuba.algo3.modelo.Eleccion;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.JugadorMuertoException;
import edu.fiuba.algo3.modelo.Mafioso;
import edu.fiuba.algo3.modelo.Noche;
import edu.fiuba.algo3.modelo.NominadoMuertoException;

public class DiaTest {
    
    @Test
    public void test13DeDiaAlNominarUnMuertoElSistemaRechazaLaAccion(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano));
        Jugador tilemans = new Jugador("tilemans", new Ciudadano(mockEleccionDeCiudadano));
        Jugador fede = new Jugador("fede", new Ciudadano(mockEleccionDeCiudadano));
        Jugador facu = new Jugador("facu", new Ciudadano(mockEleccionDeCiudadano));
        Jugador pedro = new Jugador("pedro", new Mafioso(mockEleccionDeMafioso));
        mockEleccionDeMafioso.respuesta(tomas);
        mockEleccionDeCiudadano.respuesta(tomas);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(tomas);
        jugadores.add(tilemans);
        jugadores.add(fede);
        jugadores.add(facu);
        jugadores.add(pedro);
        Noche noche = new Noche();
        noche.ejecutar(jugadores);

        // Act & Assert
        assertThrows(
                NominadoMuertoException.class,
                () -> tilemans.ejecutarRolDeDiaSobre(jugadores)
        );
    }
    
    @Test
    public void test14DeDiaElNominadoConMasVotosMuere(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano));
        Jugador tilemans = new Jugador("tilemans", new Ciudadano(mockEleccionDeCiudadano));
        Jugador fede = new Jugador("fede", new Ciudadano(mockEleccionDeCiudadano));
        Jugador facu = new Jugador("facu", new Ciudadano(mockEleccionDeCiudadano));
        Jugador pedro = new Jugador("pedro", new Mafioso(mockEleccionDeMafioso));
        mockEleccionDeMafioso.respuesta(tomas);
        mockEleccionDeCiudadano.respuesta(tilemans);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(tomas);
        jugadores.add(tilemans);
        jugadores.add(fede);
        jugadores.add(facu);
        jugadores.add(pedro);
        Dia dia = new Dia();

        // Act
        dia.ejecutar(jugadores);

        // Assert
        assertFalse(tilemans.estaVivo());
    }

    @Test
    public void test17DeDiaSiUnJugadorEliminadoIntentaHacerAlgoElSistemaRechazaLaAccion(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano));
        Jugador tilemans = new Jugador("tilemans", new Ciudadano(mockEleccionDeCiudadano));
        Jugador fede = new Jugador("fede", new Ciudadano(mockEleccionDeCiudadano));
        Jugador facu = new Jugador("facu", new Ciudadano(mockEleccionDeCiudadano));
        Jugador pedro = new Jugador("pedro", new Mafioso(mockEleccionDeMafioso));
        mockEleccionDeMafioso.respuesta(tomas);
        mockEleccionDeCiudadano.respuesta(facu);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(tomas);
        jugadores.add(tilemans);
        jugadores.add(fede);
        jugadores.add(facu);
        jugadores.add(pedro);
        Noche noche = new Noche();
        Dia dia = new Dia();

        // ANTES DE ESTO TODOS ESTAN VIVOS
        noche.ejecutar(jugadores);
        
        // Act & Assert
        // TOMAS ES UN CIUDADANO MUERTO
        // EL SISTEMA AL EJECUTAR DIA VE QUE UNO DE LOS CIUDADANOS
        // NO PUEDE ACTUAR PORQUE ESTA MUERTO
        assertThrows(
                JugadorMuertoException.class,
                () -> dia.ejecutar(jugadores)
        );
    }
}
