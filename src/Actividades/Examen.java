package Actividades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learningpaths.LearningPaths;

public class Examen extends Actividad {
    private Map<Pregunta, String> respuestasEstudiante;  // Guarda las respuestas de cada pregunta
    private Map<Pregunta, Integer> calificaciones;  // Guarda la calificación de cada respuesta
    private int puntajeTotal;  // Puntaje total del examen
    private int puntajeObtenido;  // Puntaje obtenido por el estudiante

    // Constructor que incluye el parámetro LearningPath
    public Examen(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, List<Actividad> prerrequisitos, LearningPaths learningPath, int puntajeTotal) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath);  // El estado se inicializa como "Pendiente"
        this.respuestasEstudiante = new HashMap<>();
        this.calificaciones = new HashMap<>();
        this.puntajeTotal = puntajeTotal;
        this.puntajeObtenido = 0;
    }

    // Método para que el estudiante envíe una respuesta a una pregunta
    public void responderPregunta(Pregunta pregunta, String respuesta) {
        if (pregunta != null && respuesta != null && !respuesta.isEmpty()) {
            respuestasEstudiante.put(pregunta, respuesta);  // Añadimos la respuesta si es válida
        } else {
            System.out.println("Pregunta o respuesta inválida.");
        }
    }

    // Método para que el profesor califique una respuesta usando esRespuestaCorrecta()
    public void calificarPregunta(Pregunta pregunta) {
        if (respuestasEstudiante.containsKey(pregunta)) {
            String respuestaEstudiante = respuestasEstudiante.get(pregunta);
            
            if (pregunta.esRespuestaCorrecta(respuestaEstudiante)) {
                int puntaje = pregunta.getPuntaje();  // Si la respuesta es correcta, usamos el puntaje de la pregunta
                calificaciones.put(pregunta, puntaje);
                puntajeObtenido += puntaje;  // Actualizamos el puntaje obtenido
            } else {
                calificaciones.put(pregunta, 0);  // Si la respuesta es incorrecta, el puntaje es 0
            }
        } else {
            System.out.println("El estudiante no ha respondido esta pregunta.");
        }
    }

    // Método para determinar si el examen está aprobado
    public boolean esAprobado() {
        return puntajeObtenido >= (puntajeTotal * 0.6);  // Aprobación con el 60% del puntaje total
    }

    @Override
    public String obtenerResultado() {
        return "Puntaje obtenido: " + puntajeObtenido + " / " + puntajeTotal + ", Estado: " + (esAprobado() ? "Aprobado" : "No Aprobado");
    }

    // Getters adicionales
    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public Map<Pregunta, String> getRespuestasEstudiante() {
        return respuestasEstudiante;
    }

    public Map<Pregunta, Integer> getCalificaciones() {
        return calificaciones;
    }
}
