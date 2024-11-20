package proyecto_1;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Actividades.Pregunta;
import Actividades.PreguntaVerdaderoFalso;
import Actividades.QuizVerdaderoFalso;

public class QuizVerdaderoFalsoTest {
    private QuizVerdaderoFalso quiz; // Instancia del QuizVerdaderoFalso
    private List<Pregunta> preguntas; // Lista de preguntas
    private List<Boolean> respuestasCorrectas; // Lista de respuestas correctas

    @BeforeEach
    public void setUp() {
        // Crear preguntas de tipo Verdadero/Falso
        preguntas = Arrays.asList(
            new PreguntaVerdaderoFalso("¿Java es un lenguaje compilado?", true, 5),
            new PreguntaVerdaderoFalso("¿JavaScript es lo mismo que Java?", false, 5)
        );

        // Crear respuestas correctas para el quiz
        respuestasCorrectas = Arrays.asList(true, false);

        // Inicializar el quiz
        quiz = new QuizVerdaderoFalso(
            "Quiz verdadero/falso",
            "Evaluar conceptos básicos",
            1, // Nivel de dificultad
            10, // Duración en minutos
            "QVF001", // ID
            "Pendiente", // Estado inicial
            null, // Sin prerrequisitos
            10, // Puntaje mínimo
            preguntas, // Lista de PreguntaVerdaderoFalso
            respuestasCorrectas, // Respuestas correctas
            0 // Calificación inicial
        );
    }

    @Test
    public void testEvaluarRespuestasCorrectas() {
        // Crear respuestas del estudiante (todas correctas)
        List<Boolean> respuestasEstudiante = Arrays.asList(true, false);

        // Evaluar el quiz
        boolean resultado = quiz.evaluar(respuestasEstudiante);

        // Verificar los resultados
        assertTrue(resultado, "El estudiante debería aprobar el quiz.");
        assertEquals(10, quiz.getPuntajeObtenido(), "El puntaje obtenido debería ser 10.");
        assertEquals("Aprobado", quiz.getEstado(), "El estado debería ser 'Aprobado'.");
    }

    @Test
    public void testEvaluarRespuestasIncorrectas() {
        // Crear respuestas del estudiante (todas incorrectas)
        List<Boolean> respuestasEstudiante = Arrays.asList(false, true);

        // Evaluar el quiz
        boolean resultado = quiz.evaluar(respuestasEstudiante);

        // Verificar los resultados
        assertFalse(resultado, "El estudiante no debería aprobar el quiz.");
        assertEquals(0, quiz.getPuntajeObtenido(), "El puntaje obtenido debería ser 0.");
        assertEquals("No Aprobado", quiz.getEstado(), "El estado debería ser 'No Aprobado'.");
    }

    @Test
    public void testEvaluarRespuestasParcialmenteCorrectas() {
        // Crear respuestas del estudiante (una correcta, una incorrecta)
        List<Boolean> respuestasEstudiante = Arrays.asList(true, true);

        // Evaluar el quiz
        boolean resultado = quiz.evaluar(respuestasEstudiante);

        // Verificar los resultados
        assertFalse(resultado, "El estudiante no debería aprobar el quiz.");
        assertEquals(5, quiz.getPuntajeObtenido(), "El puntaje obtenido debería ser 5.");
        assertEquals("No Aprobado", quiz.getEstado(), "El estado debería ser 'No Aprobado'.");
    }

    @Test
    public void testRespuestasNoCoincidenConPreguntas() {
        // Crear respuestas del estudiante (tamaño incorrecto)
        List<Boolean> respuestasEstudiante = Arrays.asList(true);

        // Evaluar y verificar que lanza una excepción
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            quiz.evaluar(respuestasEstudiante);
        });

        // Verificar el mensaje de la excepción
        assertEquals("El número de respuestas no coincide con las preguntas.", exception.getMessage());
    }

    @Test
    public void testInicializacionIncorrecta() {
        // Intentar inicializar con tamaños desiguales en preguntas y respuestas correctas
        List<Boolean> respuestasIncorrectas = Arrays.asList(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new QuizVerdaderoFalso(
                "Quiz verdadero/falso",
                "Evaluar conceptos básicos",
                1, 10, "QVF002", "Pendiente",
                null, 10,
                preguntas, // Lista de preguntas
                respuestasIncorrectas, // Respuestas correctas incorrectas
                0
            );
        });

        // Verificar el mensaje de la excepción
        assertEquals("Las respuestas correctas deben coincidir con el número de preguntas.", exception.getMessage());
    }
}
