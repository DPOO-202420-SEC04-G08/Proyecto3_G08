package proyecto1.Persistencia;

import proyecto1.exceptions.TipoInvalidoException;

public class CentralPersistenciaUsuarios {
    
    // Identificador para persistencia binaria
    public static final String BINARIO = "Binario";

    // Identificador para JSON (puedes implementarlo más adelante)
    public static final String JSON = "JSON";

    /**
     * Método que selecciona el tipo de persistencia para los usuarios
     * 
     * @param tipoArchivo El tipo de archivo que será usado para la persistencia.
     * @return El objeto de persistencia correspondiente al tipo de archivo.
     * @throws TipoInvalidoException Se lanza si el tipo de archivo no es válido.
     */
    public static IPersistenciaUsuarios getPersistenciaUsuarios(String tipoArchivo) throws TipoInvalidoException {
        if (BINARIO.equals(tipoArchivo)) {
            return new PersistenciaUsuariosBinario();
        } else if (JSON.equals(tipoArchivo)) {
            // Implementa PersistenciaUsuariosJson si decides usar JSON más adelante
            throw new TipoInvalidoException("El tipo JSON no está implementado aún.");
        } else {
            throw new TipoInvalidoException(tipoArchivo);
        }
    }
}
