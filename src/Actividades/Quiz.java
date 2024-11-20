package Actividades;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends Actividad {
    private List<Pregunta> preguntas; // Lista de preguntas del quiz
    private int puntajeMinimo; // Puntaje mínimo para aprobar
    private int puntajeObtenido; // Puntaje obtenido por el estudiante

    public Quiz(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, 
                String ID, String estado, List<Actividad> prerrequisitos, int puntajeMinimo, 
                List<Pregunta> preguntas, int calificacion) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, estado, prerrequisitos, null, calificacion);
        
        if (preguntas == null || preguntas.isEmpty()) {
            throw new IllegalArgumentException("La lista de preguntas no puede ser nula o vacía.");
        }
        
        if (puntajeMinimo < 0) {
            throw new IllegalArgumentException("El puntaje mínimo debe ser mayor o igual a 0.");
        }

        this.preguntas = preguntas;
        this.puntajeMinimo = puntajeMinimo;
        this.puntajeObtenido = 0;
    }

    // Método para agregar una pregunta al quiz
    public void agregarPregunta(Pregunta pregunta) {
        if (pregunta == null) {
            throw new IllegalArgumentException("La pregunta no puede ser nula.");
        }
        preguntas.add(pregunta);
    }

    // Método para evaluar el quiz y calcular el puntaje obtenido
    public void evaluarQuiz(List<String> respuestasEstudiante) {
        if (respuestasEstudiante == null || respuestasEstudiante.size() != preguntas.size()) {
            throw new IllegalArgumentException("El número de respuestas debe coincidir con el número de preguntas.");
        }

        int puntaje = 0;
        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String respuestaEstudiante = respuestasEstudiante.get(i);
            
            if (pregunta.esRespuestaCorrecta(respuestaEstudiante)) {
                puntaje += pregunta.getPuntaje(); // Sumamos el puntaje si es correcta
            }
        }

        this.puntajeObtenido = puntaje;
        actualizarEstado(); // Llamamos al método para actualizar el estado
    }

    // Método privado para actualizar el estado del quiz
    private void actualizarEstado() {
        setEstado(puntajeObtenido >= puntajeMinimo ? "Aprobado" : "No Aprobado");
    }

    @Override
    public String obtenerResultado() {
        return "Puntaje obtenido: " + puntajeObtenido + ", Estado: " + getEstado();
    }

    // Getters y setters adicionales
    public int getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(int puntajeMinimo) {
        if (puntajeMinimo < 0) {
            throw new IllegalArgumentException("El puntaje mínimo debe ser mayor o igual a 0.");
        }
        this.puntajeMinimo = puntajeMinimo;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }
    public void setPuntajeObtenido(int puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }


    public List<Pregunta> getPreguntas() {
        return new ArrayList<>(preguntas); // Retornamos una copia de la lista para proteger los datos
    }
}
