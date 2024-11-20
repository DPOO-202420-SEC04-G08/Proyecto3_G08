package proyecto2.consola;

import proyecto1.administrador.Administrador;
import proyecto1.estudiante.Estudiante;
import proyecto1.estudiante.ProgresoEstudiante;
import proyecto1.profesor.Profesor;
import proyecto1.usuario.Usuario;
import proyecto1.Persistencia.PersistenciaUsuariosBinario;
import learningpaths.LearningPaths;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Actividades.Actividad;
import Actividades.Quiz;
import Actividades.Tarea;

public class ConsolaPrincipal {

    public static void main(String[] args) {
        PersistenciaUsuariosBinario persistencia = new PersistenciaUsuariosBinario();
        List<Usuario> usuarios;
        List<LearningPaths> learningPaths = new ArrayList<>();

        try {
            usuarios = persistencia.cargarUsuarios("usuarios.bin");
        } catch (IOException e) {
            usuarios = new ArrayList<>();
            System.err.println("No se encontraron usuarios guardados. Se iniciará una lista vacía.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al Sistema de Learning Paths");

        while (true) {
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> registrarUsuario(scanner, persistencia, usuarios);
                case 2 -> iniciarSesion(scanner, usuarios, learningPaths, persistencia);
                case 3 -> {
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void registrarUsuario(Scanner scanner, PersistenciaUsuariosBinario persistencia, List<Usuario> usuarios) {
        System.out.println("Registro de Usuario");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Rol (1: Profesor, 2: Estudiante): ");
        int rol = scanner.nextInt();
        scanner.nextLine();

        Usuario nuevoUsuario;
        if (rol == 1) {
            nuevoUsuario = new Profesor("P" + System.currentTimeMillis(), nombre, email, password);
        } else if (rol == 2) {
            nuevoUsuario = new Estudiante("E" + System.currentTimeMillis(), nombre, email, password, List.of("General"));
        } else {
            System.out.println("Rol inválido.");
            return;
        }

        usuarios.add(nuevoUsuario);
        try {
            persistencia.salvarUsuarios("usuarios.bin", usuarios);
            System.out.println("Usuario registrado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    private static void iniciarSesion(Scanner scanner, List<Usuario> usuarios, List<LearningPaths> learningPaths, PersistenciaUsuariosBinario persistencia) {
        System.out.println("Inicio de Sesión");
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.autenticar(email, password)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido " + usuario.getNombre());
                if (usuario instanceof Profesor) {
                    mostrarMenuProfesor((Profesor) usuario, scanner, learningPaths);
                } else if (usuario instanceof Estudiante) {
                    mostrarMenuEstudiante((Estudiante) usuario, scanner, learningPaths);
                } else if (usuario instanceof Administrador) {
                    mostrarMenuAdministrador((Administrador) usuario, scanner, usuarios, persistencia);
                }
                return;
            }
        }
        System.out.println("Credenciales incorrectas.");
    }

    private static void mostrarMenuProfesor(Profesor profesor, Scanner scanner, List<LearningPaths> learningPaths) {
        while (true) {
            System.out.println("\nMenú Profesor");
            System.out.println("1. Crear Learning Path");
            System.out.println("2. Agregar Actividad");
            System.out.println("3. Calificar Actividad");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> crearLearningPathConDatos(profesor, scanner, learningPaths);
                case 2 -> agregarActividad(profesor, scanner);
                case 3 -> calificarActividad(profesor, scanner);
                case 4 -> {
                    return; // Salir al menú principal
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    
    private static void crearLearningPathConDatos(Profesor profesor, Scanner scanner, List<LearningPaths> learningPaths) {
        System.out.println("Creación de un nuevo Learning Path");

        // Título
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        // Descripción
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        // Nivel de Dificultad
        System.out.print("Nivel de Dificultad (1: Básico, 2: Intermedio, 3: Avanzado): ");
        int nivelDificultad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Duración
        System.out.print("Duración en minutos: ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Objetivo
        System.out.print("Objetivo: ");
        String objetivo = scanner.nextLine();

        // Fecha de Creación (Automática)
        LocalDate fechaCreacion = LocalDate.now();

        // Versión
        System.out.print("Versión: ");
        String version = scanner.nextLine();

        // Generar ID automáticamente
        String idLearningPath = "LP" + System.currentTimeMillis();

        // Crear el Learning Path
        LearningPaths nuevoPath = new LearningPaths(
            titulo,
            descripcion,
            nivelDificultad,
            duracion,
            0.0f, // Rating inicial
            objetivo,
            fechaCreacion,
            fechaCreacion, // Fecha de modificación inicial
            version,
            idLearningPath
        );

        // Agregar a la lista del profesor
        profesor.getListaLearningPaths().add(nuevoPath);
        learningPaths.add(nuevoPath); // Agregar a la lista general

        System.out.println("Learning Path creado exitosamente:");
        System.out.println("Título: " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Nivel de Dificultad: " + nivelDificultad);
        System.out.println("Duración: " + duracion + " minutos");
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Versión: " + version);
        System.out.println("ID del Learning Path: " + idLearningPath);
    }


    private static void agregarActividad(Profesor profesor, Scanner scanner) {
        System.out.println("Seleccione el Learning Path:");
        List<LearningPaths> paths = profesor.getListaLearningPaths();
        for (int i = 0; i < paths.size(); i++) {
            System.out.println((i + 1) + ". " + paths.get(i).getTitulo());
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        LearningPaths path = paths.get(index);

        System.out.println("Tipo de actividad (1: Tarea, 2: Quiz, 3: Examen):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Objetivo: ");
        String objetivo = scanner.nextLine();

        Actividad nuevaActividad;
        switch (tipo) {
            case 1 -> nuevaActividad = new Tarea(descripcion, objetivo, 1, 30, "T" + System.currentTimeMillis(), null, path);
            case 2 -> nuevaActividad = new Quiz(descripcion, objetivo, 2, 20, "Q" + System.currentTimeMillis(), null, null, 50, List.of(), 0);
            default -> throw new IllegalArgumentException("Tipo de actividad inválido.");
        }

        path.agregarActividad(nuevaActividad);
        System.out.println("Actividad agregada exitosamente.");
    }

    private static void calificarActividad(Profesor profesor, Scanner scanner) {
        System.out.println("Seleccione el Learning Path:");
        List<LearningPaths> paths = profesor.getListaLearningPaths();
        for (int i = 0; i < paths.size(); i++) {
            System.out.println((i + 1) + ". " + paths.get(i).getTitulo());
        }
        int indexPath = scanner.nextInt() - 1;
        scanner.nextLine();

        LearningPaths path = paths.get(indexPath);

        System.out.println("Seleccione la actividad:");
        for (int i = 0; i < path.getActividades().size(); i++) {
            System.out.println((i + 1) + ". " + path.getActividades().get(i).getDescripcion());
        }
        int indexActividad = scanner.nextInt() - 1;
        scanner.nextLine();

        Actividad actividad = path.getActividades().get(indexActividad);

        System.out.print("Ingrese la calificación (0-100): ");
        int calificacion = scanner.nextInt();
        scanner.nextLine();

        ProgresoEstudiante progreso = new ProgresoEstudiante(new Estudiante("E001", "Estudiante1", "estu1@test.com", "1234", List.of("Java")));
        profesor.calificarActividad(actividad, progreso, calificacion);

        System.out.println("Actividad calificada exitosamente.");
    }

    private static void mostrarMenuEstudiante(Estudiante estudiante, Scanner scanner, List<LearningPaths> learningPaths) {
        while (true) {
            System.out.println("\nMenú Estudiante");
            System.out.println("1. Inscribirse en Learning Path");
            System.out.println("2. Completar Actividad");
            System.out.println("3. Ver Progreso");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> inscribirseLearningPath(estudiante, scanner, learningPaths);
                case 2 -> completarActividad(estudiante, scanner);
                case 3 -> verProgreso(estudiante);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void inscribirseLearningPath(Estudiante estudiante, Scanner scanner, List<LearningPaths> learningPaths) {
        System.out.println("Learning Paths disponibles:");
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println((i + 1) + ". " + learningPaths.get(i).getTitulo());
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        LearningPaths path = learningPaths.get(index);
        estudiante.inscribirLearningPath(path);

        System.out.println("Inscripción realizada exitosamente.");
    }

    private static void completarActividad(Estudiante estudiante, Scanner scanner) {
        System.out.println("Completar actividad (simulación).");
    }

    private static void verProgreso(Estudiante estudiante) {
        System.out.println("Progreso general: " + estudiante.obtenerProgreso() + "%");
    }

    private static void mostrarMenuAdministrador(Administrador administrador, Scanner scanner, List<Usuario> usuarios, PersistenciaUsuariosBinario persistencia) {
        while (true) {
            System.out.println("\nMenú Administrador");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarUsuario(scanner, persistencia, usuarios);
                case 2 -> administrador.listarUsuarios();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}
