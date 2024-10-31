package Actividades;

import java.util.List;
import learningpaths.LearningPaths;

public class Tarea extends Actividad {
    private String respuestaEstudiante;
    private int calificacion;

    // Constructor modificado que incluye el parámetro LearningPath
    public Tarea(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, List<Actividad> prerrequisitos, LearningPaths learningPath) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath);  // El estado se pasa como "Pendiente" al constructor de Actividad
        this.calificacion = -1; // Inicialmente sin calificar
    }

    // Método para que el estudiante envíe la tarea
    public void enviarTarea(String respuesta) {
        this.respuestaEstudiante = respuesta;
        setEstado("Enviada");  // Usamos setEstado() de la clase padre
    }

    // Método para que el profesor califique la tarea
    public void calificarTarea(int calificacion) {
        if ("Enviada".equals(getEstado())) {  // Usamos getEstado() de la clase padre
            if (calificacion >= 0 && calificacion <= 100) {  // Aseguramos que la calificación sea válida
                this.calificacion = calificacion;
                setEstado("Calificada");  // Cambiamos el estado a "Calificada"
            } else {
                System.out.println("La calificación debe estar entre 0 y 100.");
            }
        } else {
            System.out.println("La tarea debe ser enviada antes de calificarla.");
        }
    }

    @Override
    public String obtenerResultado() {
        if ("Calificada".equals(getEstado())) {  // Usamos getEstado() de la clase padre
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
