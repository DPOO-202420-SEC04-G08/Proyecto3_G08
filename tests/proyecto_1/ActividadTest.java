package proyecto_1;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Actividades.*;
import learningpaths.LearningPaths;

import java.util.List;

public class ActividadTest {

    @Test
    public void testCreacionActividad() {
        Actividad actividad = new Tarea(
            "Tarea de ejemplo",
            "Resolver ejercicios básicos",
            2, // Nivel de dificultad
            45, // Duración en minutos
            "TAR001", // ID
            null, // Sin prerrequisitos
            null // Sin LearningPath asociado
        );

        assertEquals("Tarea de ejemplo", actividad.getDescripcion());
        assertEquals("Resolver ejercicios básicos", actividad.getObjetivo());
        assertEquals(2, actividad.getNivelDificultad());
        assertEquals(45, actividad.getDuracionMinutos());
        assertEquals("TAR001", actividad.getID());
        assertEquals("Pendiente", actividad.getEstado());
        assertNull(actividad.getLearningPath());
        assertNull(actividad.getPrerrequisitos());
    }

    @Test
    public void testEstaCompletadaPor() {
        Actividad actividad = new Tarea(
            "Tarea de ejemplo",
            "Completar ejercicios básicos",
            1, // Nivel de dificultad
            30, // Duración en minutos
            "TAR001", // ID
            null, // Sin prerrequisitos
            null // Sin LearningPath asociado
        );

        actividad.setEstado("Completado");
        assertTrue(actividad.estaCompletadaPor(null), "La actividad debería estar completada.");
        
        actividad.setEstado("Pendiente");
        assertFalse(actividad.estaCompletadaPor(null), "La actividad no debería estar completada.");
    }

    @Test
    public void testCloneActividad() {
        Actividad actividad = new Tarea(
            "Tarea inicial",
            "Clonar esta tarea",
            2, // Nivel de dificultad
            60, // Duración en minutos
            "TAR002", // ID
            null, // Sin prerrequisitos
            null // Sin LearningPath asociado
        );

        Actividad clonada = actividad.clone();
        
        assertEquals(actividad.getDescripcion(), clonada.getDescripcion());
        assertEquals(actividad.getObjetivo(), clonada.getObjetivo());
        assertEquals(actividad.getNivelDificultad(), clonada.getNivelDificultad());
        assertEquals(actividad.getDuracionMinutos(), clonada.getDuracionMinutos());
        assertEquals(actividad.getID(), clonada.getID());
        assertEquals(actividad.getEstado(), clonada.getEstado());
        assertNull(clonada.getLearningPath());
        assertNull(clonada.getPrerrequisitos());
    }

    @Test
    public void testPrerrequisitos() {
        Actividad prerrequisito = new Tarea(
            "Tarea prerrequisito",
            "Completar antes de continuar",
            1, // Nivel de dificultad
            15, // Duración en minutos
            "TARPR001", // ID
            null, // Sin prerrequisitos
            null // Sin LearningPath asociado
        );

        List<Actividad> prerrequisitos = List.of(prerrequisito);
        Actividad actividad = new Tarea(
            "Tarea principal",
            "Completar después del prerrequisito",
            2, // Nivel de dificultad
            60, // Duración en minutos
            "TAR002", // ID
            prerrequisitos, // Con prerrequisitos
            null // Sin LearningPath asociado
        );

        assertEquals(1, actividad.getPrerrequisitos().size());
        assertEquals("TARPR001", actividad.getPrerrequisitos().get(0).getID());
    }

    @Test
    public void testObtenerResultado() {
        Actividad actividad = new Tarea(
            "Tarea de ejemplo",
            "Resolver ejercicios básicos",
            1, // Nivel de dificultad
            30, // Duración en minutos
            "TAR001", // ID
            null, // Sin prerrequisitos
            null // Sin LearningPath asociado
        );

        actividad.setEstado("Pendiente");
        assertEquals("Tarea pendiente de envío.", actividad.obtenerResultado());
        
        actividad.setEstado("Enviada");
        assertEquals("Tarea enviada, pendiente de calificación.", actividad.obtenerResultado());
        
        actividad.setEstado("Calificada");
        assertEquals("Calificación obtenida: 0/100", actividad.obtenerResultado());
    }

    @Test
    public void testLearningPath() {
        LearningPaths learningPath = new LearningPaths("Aprender Java", null, 0, 0, 0, null, null, null, null, "LP001");
        Actividad actividad = new Tarea(
            "Tarea de ejemplo",
            "Resolver ejercicios básicos",
            1, // Nivel de dificultad
            30, // Duración en minutos
            "TAR001", // ID
            null, // Sin prerrequisitos
            learningPath // Asociamos el LearningPath
        );
 
        System.out.println("LearningPath ID: " + actividad.getLearningPath().getIdLearningPath());
        System.out.println("LearningPath Título: " + actividad.getLearningPath().getTitulo());

        assertNotNull(actividad.getLearningPath(), "El LearningPath no debería ser nulo.");
        assertEquals("LP001", actividad.getLearningPath().getIdLearningPath());
        assertEquals("Aprender Java", actividad.getLearningPath().getTitulo());
    }

}
