package proyecto1.estudiante;

import java.util.List;

import Actividades.Actividad;
import Reseñas.Reseña;
import proyecto1.usuario.Usuario;
import learningpaths.LearningPaths;

public class Estudiante extends Usuario {
    
	private static final long serialVersionUID = 1L;
	private List<String> intereses;  // Intereses del estudiante
    private ProgresoEstudiante progreso;  // Objeto para gestionar el progreso del estudiante

    // Constructor de Estudiante
    public Estudiante(String id, String nombre, String email, String contraseña, List<String> intereses) {
        super(id, nombre, email, contraseña, "Estudiante");  // Se pasa el rol "Estudiante"
        this.intereses = intereses;  // Inicializamos los intereses
        this.progreso = new ProgresoEstudiante(this);  // Inicializamos el progreso para el estudiante
    }
    // Método para escribir una reseña sobre una actividad
    public Reseña escribirReseña(Actividad actividad, String comentario, int calificacion) {
        Reseña nuevaReseña = new Reseña(comentario, calificacion, this);  // Creamos una nueva reseña
        System.out.println("Reseña creada por el estudiante para la actividad " + actividad.getDescripcion());
        return nuevaReseña;  // Devolvemos la reseña creada
    }

    // Método para escribir una reseña sobre una ruta de aprendizaje
    public Reseña escribirReseñaRuta(LearningPaths learningPath, String comentario, int calificacion) {
        Reseña nuevaReseña = new Reseña(comentario, calificacion, this);  // Creamos una nueva reseña
        System.out.println("Reseña creada por el estudiante para la ruta " + learningPath.getTitulo());
        return nuevaReseña;  // Devolvemos la reseña creada
    }
    // Getter para la lista de intereses
    public List<String> getIntereses() {
        return intereses;
    }

    // Setter para actualizar los intereses del estudiante
    public void setIntereses(List<String> intereses) {
        this.intereses = intereses;
    }

    // Método para inscribirse en una ruta de aprendizaje (LearningPath)
    public void inscribirLearningPath(LearningPaths path) {
        progreso.inscribirLearningPath(path);  // Inscribir el estudiante y crear su progreso en la ruta
        System.out.println("Inscripción realizada en el Learning Path: " + path.getTitulo());
    }

    // Método para realizar una actividad y actualizar el progreso
    public void realizarActividad(Actividad actividad) {
        progreso.actualizarProgreso(actividad);  // Actualizamos el progreso en la actividad
    }

    // Método para obtener el progreso del estudiante
    public double obtenerProgreso() {
        return progreso.getProgresoTotal();  // Retorna el progreso total en todas las rutas
    }

    @Override
    public boolean autenticar(String email, String contraseña) {
        return this.getEmail().equals(email) && this.getContraseña().equals(contraseña);
    }
    @Override
    public String toString() {
        return "Estudiante{" + super.toString() + "}";
    }
}
