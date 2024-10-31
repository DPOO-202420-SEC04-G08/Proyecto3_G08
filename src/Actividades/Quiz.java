package Actividades;

import java.util.List;

public class Quiz extends Actividad {
    private List<Pregunta> preguntas;
    private int puntajeMinimo;
    private int puntajeObtenido;

    public Quiz(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, String estado, List<Actividad> actividad, int puntajeMinimo, List<Pregunta> preguntas) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, estado, actividad, null);  // El estado se inicializa
        this.preguntas = preguntas;  // Se inicializa la lista de preguntas
        this.puntajeMinimo = puntajeMinimo;
        this.puntajeObtenido = 0;
    }

    // Método para agregar una pregunta al quiz
    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    // Método para evaluar el quiz y calcular el puntaje obtenido usando esRespuestaCorrecta()
    public void evaluarQuiz(List<String> respuestasEstudiante) {
        int puntaje = 0;
        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String respuestaEstudiante = respuestasEstudiante.get(i);
            
            if (pregunta.esRespuestaCorrecta(respuestaEstudiante)) {
                puntaje += pregunta.getPuntaje();  // Si la respuesta es correcta, sumamos el puntaje
            }
        }
        this.puntajeObtenido = puntaje;
        setEstado(puntaje >= puntajeMinimo ? "Aprobado" : "No Aprobado");
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
        this.puntajeMinimo = puntajeMinimo;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
}
