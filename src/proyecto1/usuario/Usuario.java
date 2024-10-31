package proyecto1.usuario;
import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;  // Versionado para la serialización

    protected String id;
    protected String nombre;
    protected String email;
    protected String contraseña;
    protected String rol;

    // Constructor
    public Usuario(String id, String nombre, String email, String contraseña, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.rol = rol; // Estudiante, Profesor, Administrador
    }

    // Método abstracto para autenticar las credenciales
    public abstract boolean autenticar(String email, String contraseña);

    // Getters
    public String getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    public String getContraseña() {
        return contraseña;
    }

    public String getId() {
        return id;
    }
    // Método toString para facilitar la impresión de objetos Usuario
    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombre + "', email='" + email + "', rol='" + rol + "'}";
    }
}
