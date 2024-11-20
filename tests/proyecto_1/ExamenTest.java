package proyecto_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import Actividades.*;

public class ExamenTest {
    private Examen examen;

    @BeforeEach
    public void setUp() {
        List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Java es un lenguaje compilado?", null, "Sí", 10),
            new Pregunta("¿Qué significa JVM?", null, "Java Virtual Machine", 15)
        );
        examen = new Examen(
            "Examen básico",
            "Evaluar conceptos de Java",
            2,
            30,
            "EXAM001",
            null,
            null,
            preguntas,
            25, 0
        );
    }

    @Test
    public void testExamenAprobado() {
        examen.responderPregunta(examen.getPreguntas().get(0), "Sí");
        examen.responderPregunta(examen.getPreguntas().get(1), "Java Virtual Machine");

        examen.calificarPregunta(examen.getPreguntas().get(0));
        examen.calificarPregunta(examen.getPreguntas().get(1));

        assertTrue(examen.esAprobado(), "El examen debería estar aprobado.");
        assertEquals(25, examen.getPuntajeObtenido(), "El puntaje obtenido debería ser 25.");
    }

    @Test
    public void testExamenReprobado() {
        examen.responderPregunta(examen.getPreguntas().get(0), "No");
        examen.calificarPregunta(examen.getPreguntas().get(0));

        assertFalse(examen.esAprobado(), "El examen no debería estar aprobado.");
        assertEquals(0, examen.getPuntajeObtenido(), "El puntaje obtenido debería ser 0.");
    }
}
