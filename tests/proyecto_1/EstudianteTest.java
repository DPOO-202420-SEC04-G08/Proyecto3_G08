package proyecto_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Actividades.Actividad;
import Actividades.Tarea;
import Reseñas.Reseña;
import learningpaths.LearningPaths;
import proyecto1.estudiante.Estudiante;
import org.junit.jupiter.api.BeforeEach;
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

        // Crear una actividad de prueba
        actividad = new Tarea("Actividad 1", "Descripción de la actividad", 1, 60, "Iniciación", null, null);

        // Crear una ruta de aprendizaje de prueba
        learningPath = new LearningPaths("Ruta 1", "Descripción de la ruta", 2, 120, 4.5f, "Aprender Java", null, null, "1.0", 1);
    }

    @Test
    void testEscribirReseña() {
        // Act: Escribir una reseña
        Reseña reseña = estudiante.escribirReseña(actividad, "Muy buena actividad", 5);

        // Assert: Verificar que los valores de la reseña son correctos
        assertNotNull(reseña);
        assertEquals("Muy buena actividad", reseña.getComentario());
        assertEquals(5, reseña.getCalificacion());
        assertEquals(estudiante, reseña.getEstudiante());
    }

    @Test
    void testEscribirReseñaRuta() {
        // Act: Escribir una reseña para la ruta de aprendizaje
        Reseña reseña = estudiante.escribirReseñaRuta(learningPath, "Excelente ruta", 4);

        // Assert: Verificar que los valores de la reseña son correctos
        assertNotNull(reseña);
        assertEquals("Excelente ruta", reseña.getComentario());
        assertEquals(4, reseña.getCalificacion());
        assertEquals(estudiante, reseña.getEstudiante());
    }

    @Test
    void testInscribirLearningPath() {
        // Act: Inscribir al estudiante en una ruta de aprendizaje
        estudiante.inscribirLearningPath(learningPath);

        // Assert: Verificar que no haya errores (podrías ampliar esto si la clase `ProgresoEstudiante` tiene métodos públicos para verificar el progreso)
        assertNotNull(learningPath);
    }

    @Test
    void testRealizarActividad() {
        // Act: Realizar una actividad
        estudiante.realizarActividad(actividad);

        // Assert: Verificar que no haya errores
        // Si la clase `ProgresoEstudiante` tiene formas de verificar el progreso, podrías agregar más validaciones aquí.
    }

    @Test
    void testObtenerProgreso() {
        // Act: Obtener el progreso total
        double progreso = estudiante.obtenerProgreso();

        // Assert: Verificar que el progreso inicial sea 0 (o lo esperado)
        assertEquals(0.0, progreso); // Cambia esto según cómo se calcule el progreso en tu implementación
    }
}
