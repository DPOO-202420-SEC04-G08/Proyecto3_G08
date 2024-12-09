package proyecto3.interfaz.principal;

import javax.swing.*;

import learningpaths.LearningPaths;

import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class EstudiantePanel extends JPanel {

    private AppMainGUI parent;

    public EstudiantePanel(AppMainGUI parent) {
        this.parent = parent;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20)); 
        JLabel title = new JLabel("Panel del Estudiante", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(20));

        // Botón para inscribirse en Learning Paths
        JButton inscribirButton = new JButton("Inscribirse en Learning Path");
        inscribirButton.setAlignmentX(CENTER_ALIGNMENT);
        inscribirButton.addActionListener(e -> showInscribirDialog());
        add(inscribirButton);

        add(Box.createVerticalStrut(10)); 

        // Botón para completar actividades
        JButton completarButton = new JButton("Completar Actividades");
        completarButton.setAlignmentX(CENTER_ALIGNMENT);
        completarButton.addActionListener(e -> showCompletarDialog());
        add(completarButton);

        add(Box.createVerticalStrut(10)); 

        // Botón para ver progreso
        JButton progresoButton = new JButton("Ver Progreso");
        progresoButton.setAlignmentX(CENTER_ALIGNMENT);
        progresoButton.addActionListener(e -> showProgresoDialog());
        add(progresoButton);

        add(Box.createVerticalStrut(10)); // Espaciado entre botones

        // Botón para volver al menú principal
        JButton backButton = new JButton("Volver al Menú Principal");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> parent.showPanel("MainMenu"));
        add(backButton);

        add(Box.createVerticalGlue()); 
    }

    // Diálogo para inscribirse en Learning Paths
    private void showInscribirDialog() {
        List<LearningPaths> learningPaths = parent.getLearningPaths(); 
        if (learningPaths.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Learning Paths disponibles.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] options = learningPaths.stream().map(LearningPaths::getTitulo).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un Learning Path para inscribirse:",
                "Inscribirse en Learning Path",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selected != null) {
            JOptionPane.showMessageDialog(this, "Te has inscrito en: " + selected, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Diálogo para completar actividades
    private void showCompletarDialog() {
        JOptionPane.showMessageDialog(this, "Completar actividades estará disponible próximamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Diálogo para ver progreso
    private void showProgresoDialog() {
        JOptionPane.showMessageDialog(this, "Tu progreso actual es: 75% (ejemplo).", "Progreso", JOptionPane.INFORMATION_MESSAGE);
    }
}