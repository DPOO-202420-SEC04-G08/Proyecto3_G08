package Actividades;

import java.util.List;

public class Pregunta {
    private String enunciado;  // El texto de la pregunta
    private List<String> opciones;  // Opciones de respuesta (si aplica)
    private String respuestaCorrecta;  // La respuesta correcta de la pregunta
    private int puntaje;  // Puntaje asignado a la pregunta

    // Constructor para Pregunta
    public Pregunta(String enunciado, List<String> opciones, String respuestaCorrecta, int puntaje) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.puntaje = puntaje;
    }

    // Método para verificar si una respuesta es correcta
    public boolean esRespuestaCorrecta(String respuestaEstudiante) {
        return respuestaEstudiante != null && respuestaEstudiante.equals(respuestaCorrecta);  // Comparamos la respuesta del estudiante con la correcta
    }

    // Getters y Setters
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
