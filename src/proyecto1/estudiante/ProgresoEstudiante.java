package proyecto1.estudiante;

import Actividades.Actividad;
import learningpaths.LearningPaths;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProgresoEstudiante implements Serializable {
	private static final long serialVersionUID = 1L;
    private Estudiante estudiante;  // Referencia al estudiante
    private Map<LearningPaths, Double> progresoEnRutas;  // Mapa para el progreso por ruta de aprendizaje
    private Map<Actividad, Double> calificaciones;  // Mapa para almacenar calificaciones por actividad

    // Constructor que recibe el estudiante
    public ProgresoEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.progresoEnRutas = new HashMap<>();  // Inicializamos el mapa de progreso en rutas
        this.calificaciones = new HashMap<>();  // Inicializamos el mapa de calificaciones
    }

    // Método para inscribir al estudiante en una ruta de aprendizaje
    private static final boolean DEBUG = false; // Cambia a 'true' para habilitar mensajes

    public void inscribirLearningPath(LearningPaths path) {
        if (!progresoEnRutas.containsKey(path)) {
            progresoEnRutas.put(path, 0.0);
            if (DEBUG) {
                System.out.println("Estudiante " + estudiante.getNombre() + " inscrito en la ruta: " + path.getTitulo());
            }
        }
    }



    // Método para actualizar el progreso del estudiante en una actividad
    public void actualizarProgreso(Actividad actividad) {
        LearningPaths learningPath = actividad.getLearningPath();
        if (learningPath == null) {
            throw new IllegalArgumentException("La actividad no está asociada a un LearningPath.");
        }

        // Calcula progreso en base a actividades realizadas
        double progresoActual = progresoEnRutas.getOrDefault(learningPath, 0.0);
        double nuevoProgreso = progresoActual + (1.0 / learningPath.getActividades().size()) * 100;
        progresoEnRutas.put(learningPath, Math.min(nuevoProgreso, 100.0)); // Máximo 100%
        calificaciones.put(actividad, 100.0); // Marca la actividad como completada
    }



    // Método para verificar si el estudiante ha completado todas las actividades de la ruta
    public boolean haCompletadoRuta(LearningPaths path) {
        return progresoEnRutas.getOrDefault(path, 0.0) == 100.0;
    }

    // Método para establecer la calificación de una actividad
    public void setCalificacion(Actividad actividad, double calificacion) {
        calificaciones.put(actividad, calificacion);  // Almacena la calificación
        System.out.println("Calificación para la actividad " + actividad.getDescripcion() + ": " + calificacion);
    }

    // Método para obtener la calificación de una actividad
    public double getCalificacion(Actividad actividad) {
        return calificaciones.getOrDefault(actividad, 0.0);  // Retorna 0.0 si no hay calificación
    }

    // Método para obtener el promedio de calificaciones de todas las actividades
    public double getPromedioCalificaciones() {
        return calificaciones.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    // Obtener el progreso total en todas las rutas (promedio de progreso en todas)
    public double getProgresoTotal() {
        return progresoEnRutas.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    // Método para obtener el progreso en una ruta específica
    public double getProgresoEnRuta(LearningPaths path) {
        return progresoEnRutas.getOrDefault(path, 0.0);
    }


    public String getEstadoActividad(Actividad actividad) {
        // Supongamos que calificaciones almacena los estados de las actividades
        if (!calificaciones.containsKey(actividad)) {
            return "No iniciado"; // Actividad no encontrada
        }
        double calificacion = calificaciones.get(actividad);
        return calificacion >= 60 ? "Completado" : "En progreso"; //  basado en calificación
    }

}
