package Actividades;

import java.io.Serializable;
import java.util.List;

public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String enunciado;
    private List<String> opciones;
    private String respuestaCorrecta;
    private int puntaje;

    // Constructor
    public Pregunta(String enunciado, List<String> opciones, String respuestaCorrecta, int puntaje) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("El enunciado no puede ser nulo o vacío.");
        }
        if (opciones != null && opciones.size() < 2) {
            throw new IllegalArgumentException("Debe haber al menos dos opciones si se utilizan opciones.");
        }
        if (opciones != null && !opciones.contains(respuestaCorrecta)) {
            throw new IllegalArgumentException("La respuesta correcta debe estar dentro de las opciones.");
        }
        if (puntaje <= 0) {
            throw new IllegalArgumentException("El puntaje debe ser mayor que cero.");
        }

        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.puntaje = puntaje;
    }

    public boolean esRespuestaCorrecta(String respuestaEstudiante) {
        if (respuestaEstudiante == null) {
            return false;
        }
        if (opciones != null && !opciones.contains(respuestaEstudiante)) {
            return false;
        }
        return respuestaEstudiante.equals(respuestaCorrecta);
    }

    // Getters y setters
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        if (enunciado == null || enunciado.isEmpty()) {
            throw new IllegalArgumentException("El enunciado no puede ser nulo o vacío.");
        }
        this.enunciado = enunciado;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        if (opciones == null || opciones.size() < 2) {
            throw new IllegalArgumentException("Debe haber al menos dos opciones.");
        }
        this.opciones = opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        if (opciones != null && !opciones.contains(respuestaCorrecta)) {
            throw new IllegalArgumentException("La respuesta correcta debe estar dentro de las opciones.");
        }
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

    @Override
    public String toString() {
        return "Pregunta{" +
               "enunciado='" + enunciado + '\'' +
               ", opciones=" + opciones +
               ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
               ", puntaje=" + puntaje +
               '}';
    }
}
