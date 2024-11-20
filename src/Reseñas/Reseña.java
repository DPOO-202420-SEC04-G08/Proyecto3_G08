package Reseñas;

import proyecto1.usuario.Usuario;

import java.time.LocalDate;

public class Reseña {
    private String comentario;  // El comentario o feedback proporcionado por el estudiante
    private int calificacion;  // Calificación numérica (0-100)
    private Usuario estudiante;  // El estudiante que escribió la reseña
    private LocalDate fecha;  // Fecha de la reseña
    private String respuestaProfesor;  // Posible respuesta del profesor

    // Constructor de la reseña
    public Reseña(String comentario, int calificacion, Usuario estudiante) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();  // Fecha actual por defecto
        this.respuestaProfesor = "";  // Inicialmente sin respuesta
    }

    // Método para que el profesor responda la reseña
    public void responderProfesor(String respuesta) {
        this.respuestaProfesor = respuesta;
    }

    // Getters y Setters
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Usuario getEstudiante() {
        return estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getRespuestaProfesor() {
        return respuestaProfesor;
    }
}