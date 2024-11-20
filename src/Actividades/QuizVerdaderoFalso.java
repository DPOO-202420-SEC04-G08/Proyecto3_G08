package Actividades;

import java.util.List;

public class QuizVerdaderoFalso extends Quiz {
    private List<Boolean> respuestasCorrectas; // Lista de respuestas correctas (true/false)

    // Constructor
    public QuizVerdaderoFalso(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos,
                              String ID, String estado, List<Actividad> prerrequisitos, int puntajeMinimo,
                              List<Pregunta> preguntas, List<Boolean> respuestasCorrectas, int calificacion) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, estado, prerrequisitos, puntajeMinimo, preguntas, calificacion);
        
        if (respuestasCorrectas == null || respuestasCorrectas.size() != preguntas.size()) {
            throw new IllegalArgumentException("Las respuestas correctas deben coincidir con el número de preguntas.");
        }
        this.respuestasCorrectas = respuestasCorrectas;
    }

    // Método para evaluar las respuestas del estudiante
    public boolean evaluar(List<Boolean> respuestasEstudiante) {
        if (respuestasEstudiante == null || respuestasCorrectas == null) {
            throw new IllegalArgumentException("Las respuestas no pueden ser nulas.");
        }
        if (respuestasEstudiante.size() != respuestasCorrectas.size()) {
            throw new IllegalArgumentException("El número de respuestas no coincide con las preguntas.");
        }

        int puntaje = 0;
        for (int i = 0; i < respuestasCorrectas.size(); i++) {
            if (respuestasCorrectas.get(i).equals(respuestasEstudiante.get(i))) {
                puntaje += getPreguntas().get(i).getPuntaje(); // Sumar puntaje si la respuesta es correcta
            }
        }

        actualizarEstado(puntaje);
        return puntaje >= getPuntajeMinimo();
    }

    // Método privado para actualizar estado y puntaje
    private void actualizarEstado(int puntaje) {
        setEstado(puntaje >= getPuntajeMinimo() ? "Aprobado" : "No Aprobado");
        setPuntajeObtenido(puntaje);
    }

    // Getters y setters
    public List<Boolean> getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(List<Boolean> respuestasCorrectas) {
        if (respuestasCorrectas == null || respuestasCorrectas.size() != getPreguntas().size()) {
            throw new IllegalArgumentException("Las respuestas correctas deben coincidir con el número de preguntas.");
        }
        this.respuestasCorrectas = respuestasCorrectas;
    }

    @Override
    public String obtenerResultado() {
        return "Puntaje obtenido: " + getPuntajeObtenido() + ", Estado: " + getEstado();
    }
}
