package proyecto_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import Actividades.*;

public class EncuestaTest {
    private Encuesta encuesta;

    @BeforeEach
    public void setUp() {
        List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Java es un lenguaje compilado?", null, "Sí", 5),
            new Pregunta("¿Qué significa JVM?", null, "Java Virtual Machine", 5)
        );
        encuesta = new Encuesta(
            "Encuesta sobre Java",
            "Evaluar conocimientos básicos",
            1,
            15,
            "ENC001",
            null,
            null,
            preguntas,
            10
        );
    }

    @Test
    public void testRespuestasCorrectas() {
        encuesta.responderPregunta(encuesta.getPreguntas().get(0), "Sí");
        encuesta.responderPregunta(encuesta.getPreguntas().get(1), "Java Virtual Machine");

        assertTrue(encuesta.esCompleta(), "La encuesta debería estar completa.");
        assertEquals("Encuesta completada", encuesta.obtenerResultado(), "Debería devolver el estado completado.");
    }

    @Test
    public void testRespuestaIncorrecta() {
        encuesta.responderPregunta(encuesta.getPreguntas().get(0), "No");

        assertFalse(encuesta.esCompleta(), "La encuesta no debería estar completa si faltan respuestas.");
    }
}

