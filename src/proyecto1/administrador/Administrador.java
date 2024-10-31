package proyecto1.administrador;

import proyecto1.usuario.Usuario;
import proyecto1.estudiante.Estudiante;
import proyecto1.profesor.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Administrador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;  // Versión de serialización

	private List<Usuario> listaUsuarios;  // Lista de todos los usuarios registrados

    // Constructor del Administrador
    public Administrador(String id, String nombre, String email, String contraseña) {
        super(id, nombre, email, contraseña, "Administrador");  // Se pasa el rol "Administrador"
        this.listaUsuarios = new ArrayList<>();  // Inicializamos la lista de usuarios
    }

    // Método para registrar un usuario en el sistema
    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
        System.out.println("Usuario registrado: " + usuario.getNombre() + " con rol: " + usuario.getRol());
    }

    // Método para autenticar un usuario y redirigir dependiendo del rol
    public Usuario autenticarUsuario(String email, String contraseña) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.autenticar(email, contraseña)) {
                System.out.println("Usuario autenticado: " + usuario.getNombre());

                // Redirigir según el rol del usuario
                switch (usuario.getRol()) {
                    case "Estudiante":
                        System.out.println("Redirigiendo a la clase Estudiante...");
                        return (Estudiante) usuario;  // Se redirige al estudiante
                    case "Profesor":
                        System.out.println("Redirigiendo a la clase Profesor...");
                        return (Profesor) usuario;  // Se redirige al profesor
                    case "Administrador":
                        System.out.println("Redirigiendo a la clase Administrador...");
                        return this;  // Si es administrador, retorna el propio administrador
                    default:
                        System.out.println("Rol no reconocido.");
                        return null;
                }
            }
        }
        System.out.println("Credenciales incorrectas.");
        return null;  // Si las credenciales no son correctas
    }

    // Implementación del método abstracto autenticar para Administrador
    @Override
    public boolean autenticar(String email, String contraseña) {
        // Lógica de autenticación específica del Administrador
        return this.email.equals(email) && this.contraseña.equals(contraseña);
    }

    // Método para listar todos los usuarios registrados
    public void listarUsuarios() {
        System.out.println("Lista de usuarios registrados:");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("Usuario: " + usuario.getNombre() + " - Rol: " + usuario.getRol());
        }
    }
    // Getters y Setters
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
