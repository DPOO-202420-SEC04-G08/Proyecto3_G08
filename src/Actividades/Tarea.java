package Actividades;

import java.util.List;
import learningpaths.LearningPaths;

public class Tarea extends Actividad {
    private String respuestaEstudiante;
    private int calificacion;

    // Constructor modificado que incluye el parámetro LearningPath y calificación
    public Tarea(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, 
                 String ID, List<Actividad> prerrequisitos, LearningPaths learningPath) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath, 0); 
        this.calificacion = 0; // Inicialmente sin calificar
    }

    // Método para que el estudiante envíe la tarea
    public void enviarTarea(String respuesta) {
        if (respuesta == null || respuesta.isEmpty()) {
            throw new IllegalArgumentException("La respuesta no puede ser nula o vacía.");
        }
        this.respuestaEstudiante = respuesta;
        setEstado("Enviada");
    }

    // Método para que el profesor califique la tarea
    public void calificarTarea(int calificacion) {
        if ("Enviada".equals(getEstado())) {
            if (calificacion >= 0 && calificacion <= 100) {
                this.calificacion = calificacion;
                setEstado("Calificada");
            } else {
                throw new IllegalArgumentException("La calificación debe estar entre 0 y 100.");
            }
        } else {
            throw new IllegalStateException("La tarea debe ser enviada antes de calificarla.");
        }
    }

    @Override
    public String obtenerResultado() {
        if ("Calificada".equals(getEstado())) {
            return "Calificación obtenida: " + calificacion + "/100";
        } else if ("Enviada".equals(getEstado())) {
            return "Tarea enviada, pendiente de calificación.";
        } else {
            return "Tarea pendiente de envío.";
        }
    }

    // Getters adicionales
    public String getRespuestaEstudiante() {
        return respuestaEstudiante;
    }

    public int getCalificacion() {
        return calificacion;
    }
}
