package Actividades;

import java.util.List;
import learningpaths.LearningPaths;

public class RevisionDeRecursos extends Actividad {
    private String tipoRecurso;  // Tipo de recurso, como "Video", "Artículo", "PDF", etc.
    private String enlace;  // Enlace o referencia al recurso

    // Constructor modificado que incluye el parámetro LearningPath
    public RevisionDeRecursos(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, List<Actividad> prerrequisitos, LearningPaths learningPath, String tipoRecurso, String enlace) {
        super(descripcion, objetivo, nivelDificultad, duracionMinutos, ID, "Pendiente", prerrequisitos, learningPath);  // Inicializamos el estado como "Pendiente"
        this.tipoRecurso = tipoRecurso;
        this.enlace = enlace;
    }

    // Método para marcar la revisión como completada
    public void completarRevision() {
        setEstado("Completado");  // Cambiamos el estado a "Completado"
    }

    @Override
    public String obtenerResultado() {
        return "Revisión de recurso: " + (getEstado().equals("Completado") ? "Completada" : "Pendiente");
    }

    // Getters y setters adicionales
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}
