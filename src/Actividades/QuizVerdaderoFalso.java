package Actividades;
import learningpaths.LearningPaths;

import java.util.ArrayList;
import java.util.List;

public class QuizVerdaderoFalso extends Actividad {
    private List<PreguntaVerdaderoFalso> preguntas;
    private int puntajeObtenido;

    public QuizVerdaderoFalso(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, String estado, List<Actividad> actividad, LearningPaths learningPath) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, estado, actividad, learningPath);
        this.preguntas = new ArrayList<>();
        this.puntajeObtenido = 0;
    }

    // Método para agregar preguntas al quiz
    public void agregarPregunta(PreguntaVerdaderoFalso pregunta) {
        preguntas.add(pregunta);
    }

    // Método para responder una pregunta
    public void responderPregunta(int indicePregunta, boolean respuesta) {
        if (indicePregunta >= 0 && indicePregunta < preguntas.size()) {
            PreguntaVerdaderoFalso pregunta = preguntas.get(indicePregunta);
            if (pregunta.getRespuestaCorrecta() == respuesta) {
                puntajeObtenido++;
            }
        } else {
            System.out.println("Índice de pregunta inválido.");
        }
    }

    // Método para calcular el resultado
    public String calcularResultado() {
        int totalPreguntas = preguntas.size();
        return "Puntaje obtenido: " + puntajeObtenido + " / " + totalPreguntas + " (" + (puntajeObtenido * 100 / totalPreguntas) + "%)";
    }

    @Override
    public String obtenerResultado() {
        return calcularResultado();
    }

    // Getters y setters
    public List<PreguntaVerdaderoFalso> getPreguntas() {
        return preguntas;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }
}