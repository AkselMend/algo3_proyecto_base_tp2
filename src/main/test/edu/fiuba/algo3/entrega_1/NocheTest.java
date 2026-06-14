package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Eleccion;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mafioso;
import edu.fiuba.algo3.modelo.Medico;
import edu.fiuba.algo3.modelo.VictimaInvalidaException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class NocheTest {
    @Test
    public void test05VerificarQueLaMafiaPuedaSeleccionarUnCiudadanoVivo() {
        // Arrange
        Jugador tomas = new Jugador("tomas", "ciudadano");
        Eleccion mockEleccionDeMafia = new Eleccion();
        mockEleccionDeMafia.respuesta(tomas);
        Jugador pablo = new Jugador(
                "pablo",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador pedro = new Jugador(
                "pedro",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", "ciudadano"));
        jugadores.add(tomas);

        // Act & Assert
        assertDoesNotThrow(() -> {
            pablo.ejecutarRolDeNocheSobre(jugadores);
        });
    }

    @Test
    public void test06VerificarQueLaMafiaNoPuedaSeleccionarUnMafiosoVivo() {
        // Arrange
        Eleccion mockEleccionDeMafia = new Eleccion();
        Jugador pablo = new Jugador(
                "pablo",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador pedro = new Jugador(
                "pedro",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        mockEleccionDeMafia.respuesta(pablo);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", "ciudadano"));
        jugadores.add(new Jugador("tomas", "ciudadano"));

        // Act & Assert
        assertThrows(
                VictimaInvalidaException.class,
                () -> pablo.ejecutarRolDeNocheSobre(jugadores)
        );
    }

    @Test
    public void test07VerificarQueElProtegidoSigaVivoSiEsElObjetivoDeLaMafia() {
        // Arrange
        Jugador tomas = new Jugador("tomas", "ciudadano");
        Eleccion mockEleccionDeMafia = new Eleccion();
        mockEleccionDeMafia.respuesta(tomas);
        Jugador pablo = new Jugador(
                "pablo",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador pedro = new Jugador(
                "pedro",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador medico = new Jugador(
                "fran",
                "ciudadano",
                new Medico(mockEleccionDeMafia)
        );
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", "ciudadano"));
        jugadores.add(medico);
        jugadores.add(tomas);

        // Act
        pablo.ejecutarRolDeNocheSobre(jugadores);
        medico.ejecutarRolDeNocheSobre(jugadores);

        // Assert
        assertTrue(tomas.estaVivo());
    }

    @Test
    public void test08VerificarQueElObjetivoDeLaMafiaMueraSiNoEsProtegido() {
        // Arrange
        Jugador tomas = new Jugador("tomas", "ciudadano");
        Eleccion mockEleccionDeMafia = new Eleccion();
        mockEleccionDeMafia.respuesta(tomas);
        Eleccion mockEleccionDeMedico = new Eleccion();
        Jugador pablo = new Jugador(
                "pablo",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        mockEleccionDeMedico.respuesta(pablo);
        Jugador pedro = new Jugador(
                "pedro",
                "mafioso",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador medico = new Jugador(
                "fran",
                "ciudadano",
                new Medico(mockEleccionDeMedico)
        );
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", "ciudadano"));
        jugadores.add(medico);
        jugadores.add(tomas);
        
        // Act
        pablo.ejecutarRolDeNocheSobre(jugadores);
        medico.ejecutarRolDeNocheSobre(jugadores);

        // Assert
        assertFalse(tomas.estaVivo());
    }
}