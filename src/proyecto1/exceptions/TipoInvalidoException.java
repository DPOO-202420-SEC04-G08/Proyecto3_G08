package proyecto1.exceptions;

public class TipoInvalidoException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoInvalidoException(String tipoArchivo) {
        super("Tipo de archivo no válido: " + tipoArchivo);
    }
}
