package Actividades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learningpaths.LearningPaths;

public class Encuesta extends Actividad {
    private List<Pregunta> preguntas; // Lista de preguntas de la encuesta
    private Map<Pregunta, String> respuestasEstudiante; // Respuestas del estudiante

    // Constructor 
    public Encuesta(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos,
                    String ID, List<Actividad> prerrequisitos, LearningPaths learningPath, 
                    List<Pregunta> preguntas, int calificacion) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath, calificacion); 
        if (preguntas == null || preguntas.isEmpty()) {
            throw new IllegalArgumentException("La lista de preguntas no puede ser nula o vacía.");
        }
        this.preguntas = preguntas;
        this.respuestasEstudiante = new HashMap<>();
    }


    // Método para que el estudiante responda una pregunta
    public void responderPregunta(Pregunta pregunta, String respuesta) {
        if (pregunta == null || respuesta == null || respuesta.isEmpty()) {
            throw new IllegalArgumentException("La pregunta o la respuesta no pueden ser nulas o vacías.");
        }
        if (!preguntas.contains(pregunta)) {
            throw new IllegalArgumentException("La pregunta no pertenece a esta encuesta.");
        }
        respuestasEstudiante.put(pregunta, respuesta); // Añadimos la respuesta si es válida
    }


    // Método para verificar si la encuesta está completa
    public boolean esCompleta() {
        if (preguntas == null || respuestasEstudiante == null) {
            return false;
        }
        return preguntas.stream().allMatch(respuestasEstudiante::containsKey);
    }


    @Override
    public String obtenerResultado() {
        int respondidas = respuestasEstudiante.size();
        int total = preguntas.size();
        return respondidas == total
                ? "Encuesta completada"
                : "Encuesta incompleta: " + respondidas + "/" + total + " preguntas respondidas.";
    }


    // Getters adicionales
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public Map<Pregunta, String> getRespuestasEstudiante() {
        return respuestasEstudiante;
    }
}
