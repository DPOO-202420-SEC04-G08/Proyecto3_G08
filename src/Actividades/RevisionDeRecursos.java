package Actividades;

import java.io.Serializable;
import java.util.List;
import learningpaths.LearningPaths;

public class RevisionDeRecursos extends Actividad implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipoRecurso;  // Tipo de recurso, como "Video", "Artículo", "PDF", etc.
    private String enlace;  // Enlace o referencia al recurso

    // Constructor modificado que incluye el parámetro LearningPath y calificación
    public RevisionDeRecursos(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, 
                               String ID, List<Actividad> prerrequisitos, LearningPaths learningPath, 
                               String tipoRecurso, String enlace, int calificacion) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath, calificacion);
        if (tipoRecurso == null || tipoRecurso.isEmpty()) {
            throw new IllegalArgumentException("El tipo de recurso no puede ser nulo o vacío.");
        }
        if (enlace == null || enlace.isEmpty()) {
            throw new IllegalArgumentException("El enlace no puede ser nulo o vacío.");
        }
        this.tipoRecurso = tipoRecurso;
        this.enlace = enlace;
    }

    // Método para marcar la revisión como completada
    public void completarRevision() {
        if (this.enlace == null || this.enlace.isEmpty()) {
            throw new IllegalStateException("No se puede completar la revisión sin un enlace válido.");
        }
        setEstado("Completado");
    }

    @Override
    public String obtenerResultado() {
        return "Revisión de recurso [" + tipoRecurso + "] con enlace: " + enlace +
               ". Estado: " + (getEstado().equals("Completado") ? "Completada" : "Pendiente");
    }

    // Getters y setters adicionales con validaciones
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        if (tipoRecurso == null || tipoRecurso.isEmpty()) {
            throw new IllegalArgumentException("El tipo de recurso no puede ser nulo o vacío.");
        }
        this.tipoRecurso = tipoRecurso;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        if (enlace == null || enlace.isEmpty()) {
            throw new IllegalArgumentException("El enlace no puede ser nulo o vacío.");
        }
        this.enlace = enlace;
    }

    @Override
    public String toString() {
        return "RevisionDeRecursos{" +
               "tipoRecurso='" + tipoRecurso + '\'' +
               ", enlace='" + enlace + '\'' +
               ", estado='" + getEstado() + '\'' +
               ", descripcion='" + getDescripcion() + '\'' +
               ", objetivo='" + getObjetivo() + '\'' +
               ", calificacion=" + getCalificacion() +
               '}';
    }
}
