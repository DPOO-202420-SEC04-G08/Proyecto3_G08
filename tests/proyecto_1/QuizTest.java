package proyecto_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import Actividades.*;

public class QuizTest {
    private Quiz quiz;

    @BeforeEach
    public void setUp() {
        List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Java es un lenguaje compilado?", null, "Sí", 5),
            new Pregunta("¿Qué significa JVM?", null, "Java Virtual Machine", 5)
        );
        quiz = new Quiz(
            "Quiz básico",
            "Evaluar conceptos simples",
            1,
            10,
            "QUIZ001",
            "Pendiente",
            null,
            10,
            preguntas, 0
        );
    }

    @Test
    public void testEvaluarQuizCorrecto() {
        List<String> respuestas = Arrays.asList("Sí", "Java Virtual Machine");
        quiz.evaluarQuiz(respuestas);

        assertEquals("Aprobado", quiz.getEstado(), "El estado debería ser 'Aprobado'.");
        assertEquals(10, quiz.getPuntajeObtenido(), "El puntaje debería ser 10.");
    }

    @Test
    public void testEvaluarQuizIncorrecto() {
        List<String> respuestas = Arrays.asList("No", "No sé");
        quiz.evaluarQuiz(respuestas);

        assertEquals("No Aprobado", quiz.getEstado(), "El estado debería ser 'No Aprobado'.");
        assertEquals(0, quiz.getPuntajeObtenido(), "El puntaje debería ser 0.");
    }
}

