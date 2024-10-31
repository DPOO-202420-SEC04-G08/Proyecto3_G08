package learningpaths;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Actividades.Actividad;
import proyecto1.estudiante.Estudiante;

public class LearningPaths {
    private String titulo;
    private String descripcion; 
    private int nivelDificultad;
    private int duracion;
    private float rating;
    private List<Actividad> actividades;  // Lista de actividades
    private String objetivo; 
    private String version;
    private int idLearningPath;
	private LocalDate fechaModificacion;

    // Constructor de LearningPaths
    public LearningPaths(String titulo, String descripcion, int nivelDificultad, int duracion, float rating, String objetivo, LocalDate fechaCreacion, LocalDate fechaModificacion, String version, int idLearningPath) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nivelDificultad = nivelDificultad;
        this.duracion = duracion;
        this.rating = rating;
        this.objetivo = objetivo;
        this.version = version;
        this.idLearningPath = idLearningPath;
        this.actividades = new ArrayList<>();  // Inicializamos la lista de actividades
    }

    // Métodos para obtener y establecer el título
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
        actualizarFechaModificacion();
    }

    // Métodos para obtener y establecer la descripción
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        actualizarFechaModificacion();
    }

    // Método para obtener la lista de actividades
    public List<Actividad> getActividades() {
        return actividades;
    }

    // Método para agregar una actividad a la lista
    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    // Métodos para obtener y establecer la duración
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Métodos para obtener y establecer el rating
    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    // Método para establecer la fecha de modificación
    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    // Método para obtener la fecha de modificación
    public LocalDate getFechaModificacion() {
        return this.fechaModificacion;
    }

    // Método para actualizar la fecha de modificación
    private void actualizarFechaModificacion() {
        LocalDate.now();
    }

    // Método para calcular el progreso del estudiante en el Learning Path
    public double calcularProgreso(Estudiante estudiante) {
        long completadas = actividades.stream()
            .filter(actividad -> actividad.estaCompletadaPor(estudiante))
            .count();
        return (double) completadas / actividades.size() * 100;
    }

    // Métodos para obtener y establecer otros atributos (nivelDificultad, objetivo, etc.)
    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIdLearningPath() {
        return idLearningPath;
    }

    public void setIdLearningPath(int idLearningPath) {
        this.idLearningPath = idLearningPath;
    }
}
