package proyecto1.Persistencia;

import proyecto1.usuario.Usuario;
import java.io.IOException;
import java.util.List;

public interface IPersistenciaUsuarios {

    // Método para cargar los usuarios desde un archivo
    public List<Usuario> cargarUsuarios(String archivo) throws IOException;

    // Método para guardar la lista de usuarios en un archivo
    public void salvarUsuarios(String archivo, List<Usuario> usuarios) throws IOException;
}
