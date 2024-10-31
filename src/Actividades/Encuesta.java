package Actividades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learningpaths.LearningPaths;

public class Encuesta extends Actividad {
    private List<Pregunta> preguntas; // Lista de preguntas de la encuesta
    private Map<Pregunta, String> respuestasEstudiante; // Respuestas del estudiante

    // Constructor modificado que incluye el parámetro LearningPath
    public Encuesta(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, List<Actividad> prerrequisitos, LearningPaths learningPath, List<Pregunta> preguntas) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath);  // El estado se inicializa como "Pendiente"
        this.preguntas = preguntas;
        this.respuestasEstudiante = new HashMap<>();
    }

    // Método para que el estudiante responda una pregunta
    public void responderPregunta(Pregunta pregunta, String respuesta) {
        if (preguntas.contains(pregunta)) {
            if (respuesta != null && !respuesta.isEmpty()) {
                respuestasEstudiante.put(pregunta, respuesta);  // Añadimos la respuesta si es válida
            } else {
                System.out.println("La respuesta no puede estar vacía.");
            }
        } else {
            System.out.println("La pregunta no pertenece a esta encuesta.");
        }
    }

    // Método para verificar si la encuesta está completa
    public boolean esCompleta() {
        return respuestasEstudiante.size() == preguntas.size();  // Considera completa si todas las preguntas tienen respuesta
    }

    @Override
    public String obtenerResultado() {
        return esCompleta() ? "Encuesta completada" : "Encuesta incompleta";
    }

    // Getters adicionales
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public Map<Pregunta, String> getRespuestasEstudiante() {
        return respuestasEstudiante;
    }
}
