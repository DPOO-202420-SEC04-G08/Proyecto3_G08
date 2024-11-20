package proyecto_1;

import static org.junit.jupiter.api.Assertions.*;

import Actividades.Actividad;
import Actividades.Tarea;
import learningpaths.LearningPaths;
import proyecto1.profesor.Profesor;
import proyecto1.estudiante.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfesorTest {

    private Profesor profesor;
    private LearningPaths learningPath;
    private Actividad actividad;
    
    @BeforeEach
    void setUp() {
        // Crear un profesor
        profesor = new Profesor("2", "María López", "maria@example.com", "password123");

        // Crear una actividad de prueba
        actividad = new Tarea("Actividad 1", "Descripción de la actividad", 1, 60, "ACT001", null, null);

        // Crear una ruta de aprendizaje de prueba
        learningPath = new LearningPaths("Ruta 1", "Descripción de la ruta", 2, 120, 4.5f, "Aprender Java", null, null, "1.0", "LP001");

        // Agregar la actividad a la ruta
        learningPath.getActividades().add(actividad);

        // Crear un estudiante de prueba
        List<String> intereses = new ArrayList<>();
        intereses.add("Programación");
        new Estudiante("3", "Juan Pérez", "juan@example.com", "password123", intereses);
    }

    @Test
    void testEditarLearningPath() {
        // Act: Editar una ruta de aprendizaje
        profesor.editarLearningPath(learningPath, "Nuevo Título", "Nueva Descripción", 3, 180, 4.8f,
                "Nuevo Objetivo", LocalDate.now(), "2.0");

        // Assert: Verificar que los valores de la ruta fueron actualizados
        assertEquals("Nuevo Título", learningPath.getTitulo(), "El título debe coincidir con el nuevo título.");
        assertEquals("Nueva Descripción", learningPath.getDescripcion(), "La descripción debe coincidir con la nueva descripción.");
        assertEquals(3, learningPath.getNivelDificultad(), "El nivel de dificultad debe coincidir.");
        assertEquals(180, learningPath.getDuracion(), "La duración debe coincidir.");
        assertEquals(4.8f, learningPath.getRating(), "El rating debe coincidir.");
        assertEquals("Nuevo Objetivo", learningPath.getObjetivo(), "El objetivo debe coincidir.");
        assertEquals("2.0", learningPath.getVersion(), "La versión debe coincidir.");
    }

    @Test
    void testCrearLearningPath() {
        // Act: Crear una nueva ruta de aprendizaje
        LearningPaths nuevoPath = profesor.crearLearningPath("Java Básico", "Curso introductorio de Java");

        // Assert: Verificar que la ruta se creó correctamente
        assertNotNull(nuevoPath, "La ruta de aprendizaje creada no debe ser nula.");
        assertEquals("Java Básico", nuevoPath.getTitulo(), "El título de la nueva ruta debe coincidir.");
        assertEquals("Curso introductorio de Java", nuevoPath.getDescripcion(), "La descripción de la nueva ruta debe coincidir.");
        assertTrue(profesor.getListaLearningPaths().contains(nuevoPath), "La nueva ruta debe estar en la lista del profesor.");
    }

    //void testCalificarActividad() {
        

    @Test
    void testClonarActividad() {
        // Act: Clonar una actividad
        Actividad actividadClonada = profesor.clonarActividad(actividad);

        // Assert: Verificar que la actividad se clonó correctamente
        assertNotNull(actividadClonada, "La actividad clonada no debe ser nula.");
        assertEquals(actividad.getDescripcion(), actividadClonada.getDescripcion(), "La descripción de la actividad clonada debe coincidir.");
        assertNotSame(actividad, actividadClonada, "La actividad clonada debe ser un objeto diferente.");
    }

    @Test
    void testListaLearningPaths() {
        // Act: Obtener la lista de rutas de aprendizaje antes y después de añadir una ruta
        List<LearningPaths> listaInicial = profesor.getListaLearningPaths();

        profesor.crearLearningPath("Python Avanzado", "Curso avanzado de Python");
        List<LearningPaths> listaFinal = profesor.getListaLearningPaths();

        // Assert: Verificar los tamaños de la lista
        assertEquals(0, listaInicial.size(), "La lista inicial debe estar vacía.");
        assertEquals(1, listaFinal.size(), "La lista final debe tener una ruta.");
    }
}
