package Actividades;

import java.io.Serializable;

public class PreguntaVerdaderoFalso implements Serializable {
    private static final long serialVersionUID = 1L;

    private String enunciado; // Enunciado de la pregunta
    private boolean respuestaCorrecta; // Respuesta correcta (true/false)
    private int puntaje; // Puntaje de la pregunta

    // Constructor
    public PreguntaVerdaderoFalso(String enunciado, boolean respuestaCorrecta, int puntaje) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("El enunciado no puede ser nulo o vacío.");
        }
        if (puntaje <= 0) {
            throw new IllegalArgumentException("El puntaje debe ser mayor que cero.");
        }

        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.puntaje = puntaje;
    }

    // Método para verificar si una respuesta es correcta
    public boolean esRespuestaCorrecta(boolean respuestaEstudiante) {
        return this.respuestaCorrecta == respuestaEstudiante;
    }

    // Getters y Setters
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("El enunciado no puede ser nulo o vacío.");
        }
        this.enunciado = enunciado;
    }

    public boolean getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(boolean respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        if (puntaje <= 0) {
            throw new IllegalArgumentException("El puntaje debe ser mayor que cero.");
        }
        this.puntaje = puntaje;
    }

    // Método toString para depuración y presentación
    @Override
    public String toString() {
        return "PreguntaVerdaderoFalso{" +
               "enunciado='" + enunciado + '\'' +
               ", respuestaCorrecta=" + respuestaCorrecta +
               ", puntaje=" + puntaje +
               '}';
    }
}
