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
    
    // Atributo: lista de rutas de aprendizaje que el profesor puede crear y gestionar
    private List<LearningPaths> listaLearningPaths;

    // Constructor de Profesor, llamando al constructor de Usuario
    public Profesor(String id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña, "Profesor");  // Se pasa el rol "Profesor"
        this.listaLearningPaths = new ArrayList<>();  // Inicializamos la lista de rutas
    }
    
    // Método para responder a una reseña
    public void responderReseña(Reseña reseña, String respuesta) {
        reseña.responderProfesor(respuesta);  // El profesor responde a la reseña
        System.out.println("Respuesta del profesor agregada a la reseña.");
    }

    // Método para crear una nueva ruta de aprendizaje
    public LearningPaths crearLearningPath(String titulo, String descripcion) {
        LearningPaths path = new LearningPaths(titulo, descripcion, 0, 0, 0, descripcion, null, null, "1.0", 0);  // Creamos una nueva ruta
        listaLearningPaths.add(path);  // Añadimos la ruta a la lista del profesor
        return path;
    }

    // Método para editar una ruta de aprendizaje existente
    public void editarLearningPath(LearningPaths path, String nuevoTitulo, String nuevaDescripcion, int nuevoNivelDificultad, 
                                   int nuevaDuracion, float nuevoRating, String nuevoObjetivo, LocalDate nuevaFechaModificacion, 
                                   String nuevaVersion) {
        if (listaLearningPaths.contains(path)) { 
            path.setTitulo(nuevoTitulo);
            path.setDescripcion(nuevaDescripcion);
            path.setNivelDificultad(nuevoNivelDificultad);
            path.setDuracion(nuevaDuracion);
            path.setRating(nuevoRating);
            path.setObjetivo(nuevoObjetivo);
            path.setFechaModificacion(nuevaFechaModificacion);
            path.setVersion(nuevaVersion);
            System.out.println("LearningPath actualizado: " + path.getTitulo());
        } else {
            System.out.println("El LearningPath no existe en la lista del profesor.");
        }
    }

    // Método para calificar una actividad realizada por un estudiante
    public void calificarActividad(Actividad actividad, ProgresoEstudiante progresoEstudiante, int calificacion) {
        if (calificacion >= 0 && calificacion <= 100) {  // Validación de la calificación
            progresoEstudiante.setCalificacion(actividad, calificacion);  // Asigna la calificación a la actividad
            System.out.println("Calificación asignada a la actividad " + actividad.getDescripcion() + ": " + calificacion);
        } else {
            System.out.println("La calificación debe estar entre 0 y 100.");
        }
    }

    // Método para clonar una actividad dentro de una ruta de aprendizaje
    public Actividad clonarActividad(Actividad actividad) {
        Actividad actividadClonada = actividad.clone();  // Creamos una copia de la actividad existente
        System.out.println("Actividad clonada: " + actividadClonada.getDescripcion());
        return actividadClonada;
    }

    // Método para listar todas las rutas de aprendizaje que ha creado el profesor
    public List<LearningPaths> getListaLearningPaths() {
        return listaLearningPaths;  // Devolvemos la lista de rutas de aprendizaje
    }

    // Implementación del método abstracto autenticar para Profesor
    @Override
    public boolean autenticar(String email, String contraseña) {
        return getEmail().equals(email) && getContraseña().equals(contraseña);  // Verificamos la autenticación
    }

    @Override
    public String toString() {
        return "Profesor{" + super.toString() + "}";
    }
}
