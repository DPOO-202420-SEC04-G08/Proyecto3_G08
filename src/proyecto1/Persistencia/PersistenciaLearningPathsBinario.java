package proyecto1.Persistencia;

import learningpaths.LearningPaths;
import java.io.*;
import java.util.List;

public class PersistenciaLearningPathsBinario {

    // Método para cargar Learning Paths desde un archivo binario
    @SuppressWarnings("unchecked")
	public List<LearningPaths> cargarLearningPaths(String archivo) throws IOException {
        File file = new File(archivo);
        if (!file.exists()) {
            throw new IOException("Archivo de Learning Paths no encontrado: " + archivo);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<LearningPaths>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Error al deserializar los Learning Paths.", e);
        }
    }

    // Método para guardar Learning Paths en un archivo binario
    public void salvarLearningPaths(String archivo, List<LearningPaths> learningPaths) throws IOException {
        // Crear carpeta si no existe
        File file = new File(archivo);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // Crear directorio si es necesario
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(learningPaths);
            System.out.println("Learning Paths guardados exitosamente en: " + archivo);
        }
    }
}
