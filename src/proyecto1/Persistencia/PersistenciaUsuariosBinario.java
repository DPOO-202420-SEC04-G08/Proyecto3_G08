package proyecto1.Persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import proyecto1.usuario.Usuario;

public class PersistenciaUsuariosBinario implements IPersistenciaUsuarios {

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> cargarUsuarios(String archivo) throws IOException {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
	        // Aseguramos que la lista devuelta sea mutable
	        return new ArrayList<>((List<Usuario>) ois.readObject());
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
