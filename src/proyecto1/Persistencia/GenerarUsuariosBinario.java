package proyecto1.Persistencia;

import proyecto1.administrador.Administrador;
import proyecto1.estudiante.Estudiante;
import proyecto1.profesor.Profesor;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;

public class GenerarUsuariosBinario {
    public static void main(String[] args) {
        IPersistenciaUsuarios persistencia = new PersistenciaUsuariosBinario();

        Administrador admin = new Administrador("001", "Admin", "admin@example.com", "adminPass");
        Estudiante estudiante1 = new Estudiante("002", "Juan Pérez", "juan@example.com", "pass123", List.of("Matemáticas"));
        Estudiante estudiante2 = new Estudiante("003", "Maria Gomez", "maria@example.com", "pass456", List.of("Física"));
        Profesor profesor1 = new Profesor("004", "Prof. Ana", "ana@example.com", "profPass");
        Profesor profesor2 = new Profesor("005", "Prof. Luis", "luis@example.com", "luisPass");

        try {
            persistencia.salvarUsuarios("usuarios.bin", Arrays.asList(admin, estudiante1, estudiante2, profesor1, profesor2));
            System.out.println("Archivo usuarios.bin generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
}
