package Actividades;

public class PreguntaVerdaderoFalso {
    private String enunciado;
    private boolean respuestaCorrecta;

    public PreguntaVerdaderoFalso(String enunciado, boolean respuestaCorrecta) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public boolean getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}