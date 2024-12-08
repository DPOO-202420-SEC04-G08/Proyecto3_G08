package Actividades;

import learningpaths.LearningPaths;
import proyecto1.estudiante.Estudiante;

import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Cloneable {  // Implementa Cloneable para soportar la clonación
    private String ID;
    private String descripcion;
    private String objetivo;
    private int nivelDificultad;
    private int duracionMinutos;
    private String estado;
    private List<Actividad> prerrequisitos;  // Lista de actividades que son prerrequisitos
    private LearningPaths learningPath;  // La ruta de aprendizaje a la que pertenece la actividad
    private int calificacion;

    // Constructor de Actividad
    public Actividad(String descripcion, String objetivo, int nivelDificultad, int duracionMinutos, String ID, String estado, List<Actividad> prerrequisitos, LearningPaths learningPath, int calificacion) {
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.nivelDificultad = nivelDificultad;
        this.duracionMinutos = duracionMinutos;
        this.ID = ID;
        this.estado = estado;
        this.prerrequisitos = prerrequisitos;  // Inicializamos la lista de prerrequisitos
        this.learningPath = learningPath;  // Asociamos la ruta de aprendizaje a la actividad
        this.calificacion = calificacion;
    }

    // Método abstracto que debe implementarse en las subclases
    public abstract String obtenerResultado();

    // Método para verificar si la actividad ha sido completada por un estudiante
    public boolean estaCompletadaPor(Estudiante estudiante) {
        // Si el estado de la actividad es "Completado", devuelve true
        return "Completado".equals(this.estado);
    }
    public boolean tienePrerrequisitos() {
        return prerrequisitos != null && !prerrequisitos.isEmpty();
    }


    // Implementación del método clone
    @Override
    public Actividad clone() {
        try {
            Actividad cloned = (Actividad) super.clone();
            // Realiza una copia profunda de los prerrequisitos
            if (this.prerrequisitos != null) {
                cloned.prerrequisitos = new ArrayList<>(this.prerrequisitos.size());
                for (Actividad actividad : this.prerrequisitos) {
                    cloned.prerrequisitos.add(actividad.clone());
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Método para obtener los prerrequisitos de una actividad
    public List<Actividad> getPrerrequisitos() {
        return prerrequisitos;
    }

    // Método para obtener la ruta de aprendizaje a la que pertenece la actividad
    public LearningPaths getLearningPath() {
        return learningPath;
    }

    // Getters y setters para los atributos de la clase
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(int nivelDificultad) {
        if (nivelDificultad < 0) throw new IllegalArgumentException("El nivel de dificultad no puede ser negativo.");
        this.nivelDificultad = nivelDificultad;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos <= 0) throw new IllegalArgumentException("La duración debe ser mayor a cero.");
        this.duracionMinutos = duracionMinutos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPrerrequisitos(List<Actividad> prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

    public void setLearningPath(LearningPaths learningPath) {
        if (learningPath == null) throw new IllegalArgumentException("El LearningPath no puede ser nulo.");
        this.learningPath = learningPath;
    }

    
    public int getCalificacion() {
        return calificacion;
    }


	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
		
	}
}