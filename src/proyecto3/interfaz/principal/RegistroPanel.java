package proyecto3.interfaz.principal;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RegistroPanel extends JPanel {

    public RegistroPanel(AppMainGUI parent) {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        // Título del panel
        JLabel title = new JLabel("Registro de Usuario", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(20));

        // Campo para el nombre
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField(15);
        namePanel.add(nameField);
        add(namePanel);

        // Campo para el correo
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Correo:"));
        JTextField emailField = new JTextField(15);
        emailPanel.add(emailField);
        add(emailPanel);

        // Campo para la contraseña
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        // Selección de rol
        JPanel rolePanel = new JPanel(new FlowLayout());
        ButtonGroup roleGroup = new ButtonGroup();
        JRadioButton studentRadio = new JRadioButton("Estudiante");
        JRadioButton professorRadio = new JRadioButton("Profesor");
        roleGroup.add(studentRadio);
        roleGroup.add(professorRadio);
        rolePanel.add(studentRadio);
        rolePanel.add(professorRadio);
        add(rolePanel);

        add(Box.createVerticalStrut(20));

        // Botón para registrar al usuario
        JButton registerButton = new JButton("Registrar");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> {
            // Obtener datos ingresados
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String role = studentRadio.isSelected() ? "Estudiante" : professorRadio.isSelected() ? "Profesor" : "";

            // Validar datos
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente como " + role, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                parent.showPanel("MainMenu"); // Regresar al menú principal
            }
        });
        add(registerButton);

        add(Box.createVerticalStrut(10));

        // Botón para volver al menú principal
        JButton backButton = new JButton("Volver");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> parent.showPanel("MainMenu"));
        add(backButton);

        add(Box.createVerticalGlue());
    }
}