package proyecto1.Persistencia;

import java.io.*;
import java.util.List;
import proyecto1.usuario.Usuario;

public class PersistenciaUsuariosBinario implements IPersistenciaUsuarios {

    @Override
    @SuppressWarnings("unchecked")  // Anotación para suprimir advertencias de casting
    public List<Usuario> cargarUsuarios(String archivo) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();  // Cargar la lista de usuarios
        } catch (ClassNotFoundException e) {
            throw new IOException("Error al deserializar los usuarios.", e);
        }
    }

    @Override
    public void salvarUsuarios(String archivo, List<Usuario> usuarios) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuarios);  // Guardar la lista de usuarios
        }
    }
}
