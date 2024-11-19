package proyecto_1;

import static org.junit.jupiter.api.Assertions.*;

import Actividades.Actividad;
import Actividades.Tarea;
import learningpaths.LearningPaths;
import proyecto1.profesor.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ProfesorTest {

    private Profesor profesor;
    private LearningPaths learningPath;
    private Actividad actividad;

    @BeforeEach
    void setUp() {
        // Crear un profesor
        profesor = new Profesor("2", "María López", "maria@example.com", "password123");

        // Crear una actividad de prueba
        actividad = new Tarea("Actividad 1", "Descripción de la actividad", 1, 60, "Iniciación", null, null);

        // Crear una ruta de aprendizaje de prueba
        learningPath = new LearningPaths("Ruta 1", "Descripción de la ruta", 2, 120, 4.5f, "Aprender Java", null, null, "1.0", 1);
    }

    @Test
    void testEditarLearningPath() {
        // Act: Editar una ruta de aprendizaje
        profesor.editarLearningPath(learningPath, "Nuevo Título", "Nueva Descripción", 3, 180, 4.8f,
                "Nuevo Objetivo", LocalDate.now(), "2.0");

        // Assert: Verificar que los valores de la ruta fueron actualizados
        assertEquals("Nuevo Título", learningPath.getTitulo());
        assertEquals("Nueva Descripción", learningPath.getDescripcion());
        assertEquals(3, learningPath.getNivelDificultad());
        assertEquals(180, learningPath.getDuracion());
        assertEquals(4.8f, learningPath.getRating());
        assertEquals("Nuevo Objetivo", learningPath.getObjetivo());
        assertEquals("2.0", learningPath.getVersion());
    }

    @Test
    void testCrearLearningPath() {
        // Act: Crear una nueva ruta de aprendizaje
        LearningPaths nuevoPath = profesor.crearLearningPath("Java Básico", "Curso introductorio de Java");
        // Assert: Verificar que la ruta se creó correctamente
        assertNotNull(nuevoPath);
        assertEquals("Java Básico", nuevoPath.getTitulo());
        assertEquals("Curso introductorio de Java", nuevoPath.getDescripcion());
    }

    @Test
    void testCalificarActividad() {
        // Act: Calificar una actividad
        actividad.setCalificacion(85);

        // Assert: Verificar que la calificación se asignó correctamente
        assertEquals(85, actividad.obtenerResultado());
    }
}
