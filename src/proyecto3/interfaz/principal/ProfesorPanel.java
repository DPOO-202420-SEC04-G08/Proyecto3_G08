package proyecto3.interfaz.principal;

import javax.swing.*;
import java.awt.*;

public class ProfesorPanel extends JPanel {

    public ProfesorPanel(AppMainGUI parent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        // Título
        JLabel title = new JLabel("Panel de Profesor", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(20));

        // Botón para crear un Learning Path
        JButton createLPButton = new JButton("Crear Learning Path");
        createLPButton.setAlignmentX(CENTER_ALIGNMENT);
        createLPButton.addActionListener(e -> showCreateLearningPathDialog(parent));
        add(createLPButton);

        add(Box.createVerticalStrut(10));

        // Botón para agregar actividades
        JButton addActivityButton = new JButton("Agregar Actividad");
        addActivityButton.setAlignmentX(CENTER_ALIGNMENT);
        addActivityButton.addActionListener(e -> showAddActivityDialog(parent));
        add(addActivityButton);

        add(Box.createVerticalStrut(10));

        // Botón para ver estadísticas
        JButton viewStatsButton = new JButton("Ver Estadísticas");
        viewStatsButton.setAlignmentX(CENTER_ALIGNMENT);
        viewStatsButton.addActionListener(e -> showStatisticsDialog(parent));
        add(viewStatsButton);

        add(Box.createVerticalStrut(10));

        // Botón para regresar al menú principal
        JButton backButton = new JButton("Volver al Menú Principal");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> parent.showPanel("MainMenu"));
        add(backButton);

        add(Box.createVerticalGlue());
    }

    // Método para mostrar el diálogo de creación de Learning Path
    private void showCreateLearningPathDialog(AppMainGUI parent) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField difficultyField = new JTextField();
        JTextField durationField = new JTextField();

        panel.add(new JLabel("Título:"));
        panel.add(titleField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Nivel de Dificultad (1-3):"));
        panel.add(difficultyField);
        panel.add(new JLabel("Duración (minutos):"));
        panel.add(durationField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Crear Learning Path", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            @SuppressWarnings("unused")
			String title = titleField.getText();
            @SuppressWarnings("unused")
			String description = descriptionField.getText();
            @SuppressWarnings("unused")
			int difficulty = Integer.parseInt(difficultyField.getText());
            @SuppressWarnings("unused")
			int duration = Integer.parseInt(durationField.getText());

            // Aquí puedes agregar el Learning Path a la lista de AppMainGUI
            JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para mostrar el diálogo de agregar actividad
    private void showAddActivityDialog(AppMainGUI parent) {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JTextField titleField = new JTextField();
        JTextField objectiveField = new JTextField();
        JTextField durationField = new JTextField();

        panel.add(new JLabel("Título de la Actividad:"));
        panel.add(titleField);
        panel.add(new JLabel("Objetivo:"));
        panel.add(objectiveField);
        panel.add(new JLabel("Duración (minutos):"));
        panel.add(durationField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Actividad", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String objective = objectiveField.getText();
            int duration = Integer.parseInt(durationField.getText());

            JOptionPane.showMessageDialog(this, "Actividad agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para mostrar las estadísticas
    private void showStatisticsDialog(AppMainGUI parent) {
        // Aquí puedes mostrar estadísticas reales de los Learning Paths o actividades
        JOptionPane.showMessageDialog(this, "Aquí se mostrarán las estadísticas.", "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }
}