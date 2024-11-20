package proyecto1.profesor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import learningpaths.LearningPaths;
import Actividades.Actividad; 
import Reseñas.Reseña;
import proyecto1.estudiante.ProgresoEstudiante;
import proyecto1.usuario.Usuario;

public class Profesor extends Usuario {
    private static final long serialVersionUID = 1L;
    private List<LearningPaths> listaLearningPaths;  // Lista de rutas de aprendizaje

    public Profesor(String id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña, "Profesor");
        this.listaLearningPaths = new ArrayList<>();
    }

    public void responderReseña(Reseña reseña, String respuesta) {
        if (reseña == null || respuesta == null || respuesta.isEmpty()) {
            throw new IllegalArgumentException("La reseña y la respuesta no pueden ser nulas o vacías.");
        }
        reseña.responderProfesor(respuesta);
    }

    public LearningPaths crearLearningPath(String titulo, String descripcion) {
        if (titulo == null || titulo.isEmpty() || descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("El título y la descripción no pueden ser nulos o vacíos.");
        }
        LearningPaths path = inicializarLearningPath(titulo, descripcion);
        listaLearningPaths.add(path);
        return path;
    }

    private LearningPaths inicializarLearningPath(String titulo, String descripcion) {
        String idLearningPath = generarIdLearningPath();
        return new LearningPaths(titulo, descripcion, 0, 0, 0.0f, descripcion, null, null, "1.0", idLearningPath);
    }

    private String generarIdLearningPath() {
        return "LP" + System.currentTimeMillis(); // Genera un ID único basado en el tiempo
    }


    public void editarLearningPath(LearningPaths path, String nuevoTitulo, String nuevaDescripcion, int nuevoNivelDificultad, 
                                   int nuevaDuracion, float nuevoRating, String nuevoObjetivo, LocalDate nuevaFechaModificacion, 
                                   String nuevaVersion) {
        if (path == null || !listaLearningPaths.contains(path)) {
            throw new IllegalArgumentException("El LearningPath no existe en la lista del profesor.");
        }

        if (nuevoTitulo == null || nuevoTitulo.isEmpty() || nuevaDescripcion == null || nuevaDescripcion.isEmpty()) {
            throw new IllegalArgumentException("El título y la descripción no pueden ser nulos o vacíos.");
        }

        path.setTitulo(nuevoTitulo);
        path.setDescripcion(nuevaDescripcion);
        path.setNivelDificultad(nuevoNivelDificultad);
        path.setDuracion(nuevaDuracion);
        path.setRating(nuevoRating);
        path.setObjetivo(nuevoObjetivo);
        path.setFechaModificacion(nuevaFechaModificacion);
        path.setVersion(nuevaVersion);
    }

    public void calificarActividad(Actividad actividad, ProgresoEstudiante progresoEstudiante, int calificacion) {
        if (actividad == null || progresoEstudiante == null) {
            throw new IllegalArgumentException("La actividad y el progreso del estudiante no pueden ser nulos.");
        }

        if (calificacion < 0 || calificacion > 100) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 100.");
        }

        progresoEstudiante.setCalificacion(actividad, calificacion);
    }

    public Actividad clonarActividad(Actividad actividad) {
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula.");
        }

        return actividad.clone();
    }

    public List<LearningPaths> getListaLearningPaths() {
        return new ArrayList<>(listaLearningPaths); // Devuelve una copia para proteger los datos internos
    }

    @Override
    public boolean autenticar(String email, String contraseña) {
        return getEmail().equals(email) && getContraseña().equals(contraseña);
    }

    @Override
    public String toString() {
        return "Profesor{" + super.toString() + "}";
    }
}

