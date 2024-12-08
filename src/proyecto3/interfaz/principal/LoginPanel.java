package proyecto3.interfaz.principal;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

    public LoginPanel(AppMainGUI parent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));

        JLabel title = new JLabel("Inicio de Sesión", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(20));

        // Campo de correo
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Correo:"));
        JTextField emailField = new JTextField(15);
        emailPanel.add(emailField);
        add(emailPanel);

        // Campo de contraseña
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        add(Box.createVerticalStrut(20));

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Ejemplo de validación básica
                if (email.equals("admin@example.com") && password.equals("admin123")) {
                    JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como Administrador", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    parent.showPanel("MainMenu"); // Navega al menú principal o panel adecuado
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loginButton);

        add(Box.createVerticalStrut(10));

        // Botón de volver
        JButton backButton = new JButton("Volver");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> parent.showPanel("MainMenu"));
        add(backButton);

        add(Box.createVerticalGlue());
    }
}