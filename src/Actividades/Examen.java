package Actividades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learningpaths.LearningPaths;

public class Examen extends Actividad {
    private List<Pregunta> preguntas; // Lista de preguntas del examen
    private Map<Pregunta, String> respuestasEstudiante; // Guarda las respuestas de cada pregunta
    private Map<Pregunta, Integer> calificaciones; // Guarda la calificación de cada respuesta
    private int puntajeTotal; // Puntaje total del examen
    private int puntajeObtenido; // Puntaje obtenido por el estudiante

    // Constructor que incluye el parámetro LearningPath
    public Examen(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, 
            List<Actividad> prerrequisitos, LearningPaths learningPath, List<Pregunta> preguntas, int puntajeTotal, int calificacion) {
	  super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath, calificacion);
	  if (puntajeTotal <= 0) {
	      throw new IllegalArgumentException("El puntaje total debe ser mayor a 0.");
	  }
	  if (preguntas == null || preguntas.isEmpty()) {
	      throw new IllegalArgumentException("La lista de preguntas no puede estar vacía.");
	  }
	  this.preguntas = preguntas;
	  this.respuestasEstudiante = new HashMap<>();
	  this.calificaciones = new HashMap<>();
	  this.puntajeTotal = puntajeTotal;
	  this.puntajeObtenido = 0;
	}


    // Método para que el estudiante envíe una respuesta a una pregunta
    public void responderPregunta(Pregunta pregunta, String respuesta) {
        if (pregunta == null || respuesta == null || respuesta.isEmpty()) {
            throw new IllegalArgumentException("La pregunta o la respuesta no pueden ser nulas o vacías.");
        }
        if (!preguntas.contains(pregunta)) {
            throw new IllegalArgumentException("La pregunta no pertenece a este examen.");
        }

        respuestasEstudiante.put(pregunta, respuesta); // Registramos la respuesta
    }

    // Método para que el profesor califique una respuesta
    public void calificarPregunta(Pregunta pregunta) {
        if (!respuestasEstudiante.containsKey(pregunta)) {
            throw new IllegalArgumentException("El estudiante no ha respondido esta pregunta.");
        }
        if (calificaciones.containsKey(pregunta)) {
            throw new IllegalStateException("Esta pregunta ya ha sido calificada.");
        }

        String respuestaEstudiante = respuestasEstudiante.get(pregunta);

        if (pregunta.esRespuestaCorrecta(respuestaEstudiante)) {
            int puntaje = pregunta.getPuntaje(); // Si la respuesta es correcta, usamos el puntaje de la pregunta
            calificaciones.put(pregunta, puntaje);
            puntajeObtenido += puntaje; // Actualizamos el puntaje obtenido
        } else {
            calificaciones.put(pregunta, 0); // Si la respuesta es incorrecta, el puntaje es 0
        }
    }

    // Método para determinar si el examen está aprobado
    public boolean esAprobado() {
        return puntajeObtenido >= (puntajeTotal * 0.6); // Aprobación con el 60% del puntaje total
    }

    @Override
    public String obtenerResultado() {
        return "Puntaje obtenido: " + puntajeObtenido + " / " + puntajeTotal 
                + ", Estado: " + (esAprobado() ? "Aprobado" : "No Aprobado");
    }

    // Método para verificar si todas las preguntas han sido respondidas
    public boolean todasLasPreguntasRespondidas() {
        return respuestasEstudiante.size() == preguntas.size();
    }

    // Método para obtener la lista de preguntas
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    // Método para obtener las respuestas del estudiante
    public Map<Pregunta, String> getRespuestasEstudiante() {
        return respuestasEstudiante;
    }

    // Método para obtener las calificaciones de las preguntas
    public Map<Pregunta, Integer> getCalificaciones() {
        return calificaciones;
    }

    // Método para obtener el puntaje total del examen
    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    // Método para obtener el puntaje obtenido por el estudiante
    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }
}