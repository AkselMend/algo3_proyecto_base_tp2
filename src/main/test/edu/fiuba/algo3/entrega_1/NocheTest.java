package edu.fiuba.algo3.entrega_1;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Ciudadano;
import edu.fiuba.algo3.modelo.Detective;
import edu.fiuba.algo3.modelo.Dia;
import edu.fiuba.algo3.modelo.Eleccion;
import edu.fiuba.algo3.modelo.InvestigadoRepetidoException;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.JugadorMuertoException;
import edu.fiuba.algo3.modelo.Mafioso;
import edu.fiuba.algo3.modelo.Medico;
import edu.fiuba.algo3.modelo.Noche;
import edu.fiuba.algo3.modelo.Padrino;
import edu.fiuba.algo3.modelo.ProtegidoRepetidoException;
import edu.fiuba.algo3.modelo.VictimaEsMafiosoException;

import static org.junit.Assert.assertEquals;
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
        Eleccion mockEleccionDeMafia = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeMafia));
        mockEleccionDeMafia.respuesta(tomas);
        Jugador pablo = new Jugador(
                "pablo",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador pedro = new Jugador(
                "pedro",
                new Mafioso(mockEleccionDeMafia)
        );
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", new Ciudadano(mockEleccionDeMafia)));
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
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador pedro = new Jugador(
                "pedro",
                new Mafioso(mockEleccionDeMafia)
        );
        mockEleccionDeMafia.respuesta(pablo);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        jugadores.add(new Jugador("joaquin", new Ciudadano(mockEleccionDeCiudadano)));
        jugadores.add(new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano)));

        // Act & Assert
        assertThrows(
                VictimaEsMafiosoException.class,
                () -> pablo.ejecutarRolDeNocheSobre(jugadores)
        );
    }

    @Test
    public void test07VerificarQueElProtegidoSigaVivoSiEsElObjetivoDeLaMafia() {
        // Arrange
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano));
        Eleccion mockEleccionDeMafia = new Eleccion();
        mockEleccionDeMafia.respuesta(tomas);
        Jugador pablo = new Jugador(
                "pablo",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador pedro = new Jugador(
                "pedro",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador medico = new Jugador(
                "fran",
                new Medico(mockEleccionDeMafia)
        );
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", new Ciudadano(mockEleccionDeCiudadano)));
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
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano));
        Eleccion mockEleccionDeMafia = new Eleccion();
        mockEleccionDeMafia.respuesta(tomas);
        Eleccion mockEleccionDeMedico = new Eleccion();
        Jugador pablo = new Jugador(
                "pablo",
                new Mafioso(mockEleccionDeMafia)
        );
        mockEleccionDeMedico.respuesta(pablo);
        Jugador pedro = new Jugador(
                "pedro",
                new Mafioso(mockEleccionDeMafia)
        );
        Jugador medico = new Jugador(
                "fran",
                new Medico(mockEleccionDeMedico)
        );
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pablo);
        jugadores.add(pedro);
        jugadores.add(new Jugador("joaquin", new Ciudadano(mockEleccionDeCiudadano)));
        jugadores.add(medico);
        jugadores.add(tomas);
        Noche noche = new Noche();
        
        // Act
        noche.ejecutar(jugadores);

        // Assert
        assertFalse(tomas.estaVivo());
    }

    @Test
    public void test09ElDetectiveRecibeElRolCorrecto(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeDetective = new Eleccion();
        Jugador pedro = new Jugador("pedro", new Mafioso(mockEleccionDeMafioso));
        Detective rolDeRaul = new Detective(mockEleccionDeDetective);

        // Act & Assert
        assertEquals(rolDeRaul.revelarBandoDeInvestigado(pedro), "Mafia");
    }

    @Test
    public void test10SiDetectiveInvestigaAlPadrinoVeUnCiudadano(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeDetective = new Eleccion();
        Jugador pedro = new Jugador("pedro", new Padrino(mockEleccionDeMafioso));
        Detective rolDeRaul = new Detective(mockEleccionDeDetective);

        // Act & Assert
        assertEquals(rolDeRaul.revelarBandoDeInvestigado(pedro), "Ciudadano");
    }

    @Test
    public void test11DetectiveNoPuedeInvestigarAlMismoJugadorDosNochesSeguidas(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeDetective = new Eleccion();
        Jugador pedro = new Jugador("pedro", new Padrino(mockEleccionDeMafioso));
        Detective rolDeRaul = new Detective(mockEleccionDeDetective);
        mockEleccionDeDetective.respuesta(pedro);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pedro);

        // Act
        rolDeRaul.rolearDeNoche(jugadores);

        // Assert
        assertThrows(
                InvestigadoRepetidoException.class,
                () -> rolDeRaul.rolearDeNoche(jugadores)
        );
    }

    @Test
    public void test12ElMedicoNoPuedeProtegerAlMismoJugadorDosNochesSeguidas(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeMedico = new Eleccion();
        Jugador pedro = new Jugador("pedro", new Padrino(mockEleccionDeMafioso));
        Medico rolDeRaul = new Medico(mockEleccionDeMedico);
        mockEleccionDeMedico.respuesta(pedro);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(pedro);

        // Act
        rolDeRaul.rolearDeNoche(jugadores);

        // Assert
        assertThrows(
                ProtegidoRepetidoException.class,
                () -> rolDeRaul.rolearDeNoche(jugadores)
        );
    }

    @Test
    public void test17DeNocheSiUnJugadorEliminadoIntentaHacerAlgoElSistemaRechazaLaAccion(){
        // Arrange
        Eleccion mockEleccionDeMafioso = new Eleccion();
        Eleccion mockEleccionDeCiudadano = new Eleccion();
        Jugador tomas = new Jugador("tomas", new Ciudadano(mockEleccionDeCiudadano));
        Jugador tilemans = new Jugador("tilemans", new Ciudadano(mockEleccionDeCiudadano));
        Jugador fede = new Jugador("fede", new Ciudadano(mockEleccionDeCiudadano));
        Jugador facu = new Jugador("facu", new Ciudadano(mockEleccionDeCiudadano));
        Jugador pedro = new Jugador("pedro", new Mafioso(mockEleccionDeMafioso));
        mockEleccionDeMafioso.respuesta(tomas);
        mockEleccionDeCiudadano.respuesta(pedro);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(tomas);
        jugadores.add(tilemans);
        jugadores.add(fede);
        jugadores.add(facu);
        jugadores.add(pedro);
        Noche noche = new Noche();
        Dia dia = new Dia();

        // Act
        // TODOS ESTAN VIVOS ANTES DE ESTO
        dia.ejecutar(jugadores);

        // Assert
        // LOS CIUDADANOS VOTARON A PEDRO
        // EL UNICO MAFIOSO NO VA A PODER ACTUAR PORQUE ESTA MUERTO
        // EL SISTEMA RECHA LA ACCION
        assertThrows(
                JugadorMuertoException.class,
                () -> noche.ejecutar(jugadores)
        );
    }
}