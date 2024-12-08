package proyecto3.interfaz.principal;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings({ "serial", "unused" })
public class MainMenuPanel extends JPanel {

    public MainMenuPanel(AppMainGUI parent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));

        // Título
        JLabel title = new JLabel("Bienvenido al Sistema de Learning Paths", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

       
        add(Box.createVerticalStrut(20));

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setAlignmentX(CENTER_ALIGNMENT); // Centrar botón
        registerButton.addActionListener(e -> parent.showPanel("Registro")); // Navegar al panel de registro
        add(registerButton);

        add(Box.createVerticalStrut(10));

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(CENTER_ALIGNMENT); // Centrar botón
        loginButton.addActionListener(e -> parent.showPanel("Login")); // Navegar al panel de inicio de sesión
        add(loginButton);

        add(Box.createVerticalStrut(10));

        // Botón de salir
        JButton exitButton = new JButton("Salir");
        exitButton.setAlignmentX(CENTER_ALIGNMENT); // Centrar botón
        exitButton.addActionListener(e -> System.exit(0)); // Salir de la aplicación
        add(exitButton);

        add(Box.createVerticalGlue());
    }
}