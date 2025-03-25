package controlador;

import controlador.Controlador;
import modelo.Socio;
import modelo.Excursion;
import controlador.SocioNoEncontradoException;
import controlador.PlazasInsuficientesException;
import controlador.ExcursionNoEncontradaException;
import controlador.InscripcionDuplicadaException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class ControladorTest {
    private Controlador controlador;

    @BeforeEach
    void setUp() {
        controlador = new Controlador();
        controlador.registrarSocio(new Socio(0, "Jordi Vergés", "Barcelona", "123456789"));
        controlador.registrarExcursion(new Excursion(0, "Senderismo", new Date(), "Montaña", 5));
    }

    @Test
    void testModificarSocio_NoEncontrado() {
        assertThrows(SocioNoEncontradoException.class, () -> {
            controlador.modificarSocio(99, "Nombre", "Localidad", "Telefono");
        });
    }

    @Test
    void testInscribirSocio_Exitoso() throws Exception {
        controlador.inscribirSocio(1, 1);
        assertTrue(controlador.estaInscrito(1, 1));
    }

    @Test
    void testInscribirSocio_SinPlazas() throws SocioNoEncontradoException, ExcursionNoEncontradaException, PlazasInsuficientesException, InscripcionDuplicadaException {
        // Inscribimos al primer socio
        controlador.inscribirSocio(1, 1);

        assertThrows(PlazasInsuficientesException.class, () -> {
            controlador.inscribirSocio(2, 1);
        });
    }



}
