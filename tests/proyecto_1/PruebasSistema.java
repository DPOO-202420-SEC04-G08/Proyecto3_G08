package proyecto_1;

import proyecto1.administrador.Administrador;
import proyecto1.estudiante.Estudiante;
import proyecto1.profesor.Profesor;
import proyecto1.usuario.Usuario;
import learningpaths.LearningPaths;
import Actividades.*;
import proyecto1.Persistencia.PersistenciaUsuariosBinario;
import proyecto1.Persistencia.IPersistenciaUsuarios;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class PruebasSistema {

    public static void main(String[] args) {
        try {
            // Usar la persistencia binaria para cargar usuarios desde "datos/usuarios.bin"
            IPersistenciaUsuarios persistencia = new PersistenciaUsuariosBinario();
            List<Usuario> usuarios = persistencia.cargarUsuarios("datos/usuarios.bin");

            // Separar usuarios por tipo
            Administrador admin = null;
            List<Estudiante> estudiantes = new ArrayList<>();
            List<Profesor> profesores = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                if (usuario instanceof Administrador) {
                    admin = (Administrador) usuario;
                } else if (usuario instanceof Estudiante) {
                    estudiantes.add((Estudiante) usuario);
                } else if (usuario instanceof Profesor) {
                    profesores.add((Profesor) usuario);
                }
            }

            // Cargar LearningPaths y Actividades desde "datos/actividades_prueba.txt"
            List<Object> datosActividades = cargarDatosActividadesDesdeArchivo("datos/actividades_prueba.txt");
            LearningPaths learningPath = (LearningPaths) datosActividades.get(0);
            @SuppressWarnings("unchecked")
            List<Actividad> list = (List<Actividad>) datosActividades.get(1);
            List<Actividad> actividades = list;
            learningPath.getActividades().addAll(actividades);

            // Ejecutar pruebas
            if (admin != null) {
                testAutenticacion(admin, estudiantes, profesores);
            }
            testLearningPath(learningPath);
            for (Actividad actividad : actividades) {
                testActividad(actividad);
            }

            // Prueba para verificar el método editarLearningPath
            if (!profesores.isEmpty()) {
                Profesor profesor = profesores.get(0); // Tomar el primer profesor para la prueba
                testEditarLearningPath(profesor, learningPath);
            }

        } catch (IOException e) {
            System.out.println("Error en la persistencia de usuarios: " + e.getMessage());
        }
    }

    // Cargar datos de actividades y LearningPaths desde un archivo de texto
    public static List<Object> cargarDatosActividadesDesdeArchivo(String archivo) throws IOException {
        List<Actividad> actividades = new ArrayList<>();
        LearningPaths learningPath = null;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("#") || linea.isEmpty()) continue;

                String[] datos = linea.split(";");
                switch (datos[0]) {
                    case "LearningPath":
                        learningPath = new LearningPaths(datos[1], datos[2], Integer.parseInt(datos[3]),
                                Integer.parseInt(datos[4]), Float.parseFloat(datos[5]), datos[6], null, null, "1.0", 1);
                        break;
                    case "Quiz":
                        String[] quizInfo = datos[6].split("\\|");
                        List<Pregunta> preguntasQuiz = new ArrayList<>();
                        preguntasQuiz.add(new Pregunta(quizInfo[0].split(":")[1], Arrays.asList(quizInfo[1].split(":")[1].split(",")), quizInfo[2].split(":")[1], 5));
                        actividades.add(new Quiz(datos[2], datos[3], Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), datos[1], "Pendiente", new ArrayList<>(), 5, preguntasQuiz));
                        break;
                    case "Tarea":
                        actividades.add(new Tarea(datos[2], datos[3], Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), datos[1], new ArrayList<>(), learningPath));
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + datos[0]);
                }
            }
        }
        return Arrays.asList(learningPath, actividades);
    }

    public static void testAutenticacion(Administrador admin, List<Estudiante> estudiantes, List<Profesor> profesores) {
        System.out.println("=== Prueba de Autenticación ===");

        for (Estudiante estudiante : estudiantes) {
            admin.registrarUsuario(estudiante);
            Usuario autenticarUsuario = admin.autenticarUsuario(estudiante.getEmail(), estudiante.getContraseña());
            System.out.println("Autenticación de Estudiante " + estudiante.getNombre() + ": " + (autenticarUsuario != null));
        }
        for (Profesor profesor : profesores) {
            admin.registrarUsuario(profesor);
            Usuario autenticarUsuario = admin.autenticarUsuario(profesor.getEmail(), profesor.getContraseña());
            System.out.println("Autenticación de Profesor " + profesor.getNombre() + ": " + (autenticarUsuario != null));
        }
    }

    public static void testLearningPath(LearningPaths learningPath) {
        System.out.println("=== Prueba de Learning Path ===");
        System.out.println("Learning Path: " + learningPath.getTitulo() + ", Descripción: " + learningPath.getDescripcion());
        System.out.println("Total de actividades en el Learning Path: " + learningPath.getActividades().size());
    }

    public static void testActividad(Actividad actividad) {
        System.out.println("=== Prueba de Actividad ===");
        if (actividad instanceof Quiz) {
            Quiz quiz = (Quiz) actividad;
            quiz.evaluarQuiz(Arrays.asList("Espacio de memoria"));
            System.out.println("Resultado del Quiz: " + quiz.obtenerResultado());
        } else if (actividad instanceof Tarea) {
            Tarea tarea = (Tarea) actividad;
            tarea.enviarTarea("Respuesta de prueba");
            tarea.calificarTarea(85);
            System.out.println("Resultado de la Tarea: " + tarea.obtenerResultado());
        }
    }

    public static void testEditarLearningPath(Profesor profesor, LearningPaths learningPath) {
        System.out.println("=== Prueba de Edición de Learning Path ===");

        // Nuevos valores para la edición
        String nuevoTitulo = "Java Avanzado";
        String nuevaDescripcion = "Curso avanzado de Java";
        int nuevoNivelDificultad = 3;
        int nuevaDuracion = 180;
        float nuevoRating = 4.8f;
        String nuevoObjetivo = "Profundizar conocimientos avanzados de Java";
        LocalDate nuevaFechaModificacion = LocalDate.now();
        String nuevaVersion = "2.0";
        // Asegurarse de que el LearningPath está en la lista del profesor
        if (!profesor.getListaLearningPaths().contains(learningPath)) {
            profesor.getListaLearningPaths().add(learningPath);
            System.out.println("LearningPath agregado a la lista del profesor.");
        }

        // Llamar al método de edición
        profesor.editarLearningPath(learningPath, nuevoTitulo, nuevaDescripcion, nuevoNivelDificultad, 
                                    nuevaDuracion, nuevoRating, nuevoObjetivo, nuevaFechaModificacion, nuevaVersion);

        // Verificar que los cambios se aplicaron correctamente
        System.out.println("Título: " + (learningPath.getTitulo().equals(nuevoTitulo)));
        System.out.println("Descripción: " + (learningPath.getDescripcion().equals(nuevaDescripcion)));
        System.out.println("Nivel de Dificultad: " + (learningPath.getNivelDificultad() == nuevoNivelDificultad));
        System.out.println("Duración: " + (learningPath.getDuracion() == nuevaDuracion));
        System.out.println("Rating: " + (learningPath.getRating() == nuevoRating));
        System.out.println("Objetivo: " + (learningPath.getObjetivo().equals(nuevoObjetivo)));

        if (learningPath.getFechaModificacion() != null) {
            System.out.println("Fecha de Modificación: " + learningPath.getFechaModificacion().equals(nuevaFechaModificacion));
        } else {
            System.out.println("Fecha de Modificación no fue establecida.");
        }

        System.out.println("Versión: " + (learningPath.getVersion().equals(nuevaVersion)));
    }

}
