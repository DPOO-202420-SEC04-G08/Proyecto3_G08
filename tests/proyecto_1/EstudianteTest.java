package proyecto_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import Actividades.Actividad;
import Actividades.Tarea;
import Reseñas.Reseña;
import learningpaths.LearningPaths;
import proyecto1.estudiante.Estudiante;

import java.util.Arrays;
import java.util.List;

class EstudianteTest {

    private Estudiante estudiante;
    private Actividad actividad;
    private LearningPaths learningPath;

    @BeforeEach
    void setUp() {
        // Crear un estudiante
        List<String> intereses = Arrays.asList("Matemáticas", "Ciencias");
        estudiante = new Estudiante("1", "Juan Pérez", "juan@example.com", "password123", intereses);

        // Crear una ruta de aprendizaje de prueba
        learningPath = new LearningPaths("LP001", "Aprender Java", 2, 120, 4.5f, "Objetivo General", null, null, "1.0", "LP001");

        // Crear una actividad de prueba y asociarla al LearningPath
        actividad = new Tarea("Actividad 1", "Descripción de la actividad", 1, 60, "ACT001", null, null);
        actividad.setLearningPath(learningPath);

        // Agregar la actividad al LearningPath
        learningPath.getActividades().add(actividad);
    }


    @Test
    void testInscribirLearningPath() {
        // Act: Inscribir al estudiante en la ruta
        estudiante.inscribirLearningPath(learningPath);

        // Assert: Verificar que el progreso en la ruta sea inicial
        assertEquals(0.0, estudiante.obtenerProgreso(), "El progreso inicial debe ser 0%.");
    }

    @Test
    void testRealizarActividad() {
        // Inscribir al estudiante en la ruta
        estudiante.inscribirLearningPath(learningPath);

        // Act: Realizar la actividad
        estudiante.realizarActividad(actividad);

        // Assert: Verificar que el progreso aumentó
        assertTrue(estudiante.obtenerProgreso() > 0.0, "El progreso debe aumentar después de realizar una actividad.");
    }

    @Test
    void testEscribirReseña() {
        // Act: Escribir una reseña para la actividad
        Reseña reseña = estudiante.escribirReseña(actividad, "Buena actividad", 5);

        // Assert: Validar que la reseña fue creada correctamente
        assertNotNull(reseña, "La reseña no debe ser nula.");
        assertEquals("Buena actividad", reseña.getComentario(), "El comentario de la reseña no coincide.");
        assertEquals(5, reseña.getCalificacion(), "La calificación de la reseña no coincide.");
    }

    @Test
    void testEscribirReseñaRuta() {
        // Act: Escribir una reseña para la ruta de aprendizaje
        Reseña reseña = estudiante.escribirReseñaRuta(learningPath, "Excelente ruta", 4);

        // Assert: Validar que la reseña fue creada correctamente
        assertNotNull(reseña, "La reseña no debe ser nula.");
        assertEquals("Excelente ruta", reseña.getComentario(), "El comentario de la reseña no coincide.");
        assertEquals(4, reseña.getCalificacion(), "La calificación de la reseña no coincide.");
    }

    @Test
    void testGetProgresoEnActividad() {
        // Act: Obtener el estado inicial de la actividad (debería ser "No iniciado")
        String estadoInicial = estudiante.getProgresoEnActividad(actividad);
        
        // Assert: Validar que el estado inicial sea "No iniciado"
        assertEquals("No iniciado", estadoInicial, "El estado inicial debe ser 'No iniciado'.");

        // Inscribir al estudiante en la ruta y realizar la actividad
        estudiante.inscribirLearningPath(learningPath);
        estudiante.realizarActividad(actividad);

        // Act: Obtener el estado final de la actividad
        String estadoFinal = estudiante.getProgresoEnActividad(actividad);

        // Assert: Validar que el estado final sea "Completado"
        assertEquals("Completado", estadoFinal, "El estado final debe ser 'Completado'.");
    }

}
