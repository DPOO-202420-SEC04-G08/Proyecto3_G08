package proyecto3.interfaz.principal;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class AppMainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AppMainGUI() {
        setTitle("Sistema Learning Paths");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Agregar los paneles al CardLayout
        mainPanel.add("MainMenu", createMainMenuPanel());
        mainPanel.add("Registro", createRegistroPanel());
        mainPanel.add("Login", createLoginPanel());

        add(mainPanel);
    }

    // Método para cambiar entre paneles
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    // Crear el panel del menú principal con botones verticales
    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));

        // Título
        JLabel title = new JLabel("Bienvenido al Sistema de Learning Paths", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainMenuPanel.add(Box.createVerticalStrut(20)); // Espaciado superior
        mainMenuPanel.add(title);

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> showPanel("Registro"));
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(registerButton);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> showPanel("Login"));
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(loginButton);

        // Botón de salir
        JButton exitButton = new JButton("Salir");
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(exitButton);

        mainMenuPanel.add(Box.createVerticalGlue()); // Espaciado inferior flexible
        return mainMenuPanel;
    }

    // Crear el panel de registro
    private JPanel createRegistroPanel() {
        JPanel registroPanel = new JPanel();
        registroPanel.setLayout(new BoxLayout(registroPanel, BoxLayout.Y_AXIS));

        registroPanel.add(Box.createVerticalStrut(20)); // Espaciado superior
        JLabel title = new JLabel("Registro");
        title.setAlignmentX(CENTER_ALIGNMENT);
        registroPanel.add(title);
        registroPanel.add(Box.createVerticalStrut(20));

        // Campo de nombre
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField(15);
        namePanel.add(nameField);
        registroPanel.add(namePanel);

        // Campo de correo
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Correo:"));
        JTextField emailField = new JTextField(15);
        emailPanel.add(emailField);
        registroPanel.add(emailPanel);

        // Campo de contraseña
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        registroPanel.add(passwordPanel);

        // Selección de rol
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new FlowLayout());
        ButtonGroup roleGroup = new ButtonGroup();
        JRadioButton studentRadio = new JRadioButton("Estudiante");
        JRadioButton professorRadio = new JRadioButton("Profesor");
        roleGroup.add(studentRadio);
        roleGroup.add(professorRadio);
        rolePanel.add(studentRadio);
        rolePanel.add(professorRadio);
        registroPanel.add(rolePanel);

        // Botón de registro
        JButton registerButton = new JButton("Registrar");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String role = studentRadio.isSelected() ? "Estudiante" : "Profesor";

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || (!studentRadio.isSelected() && !professorRadio.isSelected())) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                showPanel("MainMenu");
            }
        });
        registroPanel.add(registerButton);

        // Botón de volver
        JButton backButton = new JButton("Volver");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> showPanel("MainMenu"));
        registroPanel.add(Box.createVerticalStrut(10));
        registroPanel.add(backButton);

        registroPanel.add(Box.createVerticalGlue()); // Espaciado inferior flexible
        return registroPanel;
    }

    // Crear el panel de inicio de sesión
    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        loginPanel.add(Box.createVerticalStrut(20)); // Espaciado superior
        JLabel title = new JLabel("Inicio de Sesión");
        title.setAlignmentX(CENTER_ALIGNMENT);
        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(20));

        // Campo de correo
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Correo:"));
        JTextField emailField = new JTextField(15);
        emailPanel.add(emailField);
        loginPanel.add(emailPanel);

        // Campo de contraseña
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        loginPanel.add(passwordPanel);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            showPanel("MainMenu");
        });
        loginPanel.add(loginButton);

        // Botón de volver
        JButton backButton = new JButton("Volver");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> showPanel("MainMenu"));
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(backButton);

        loginPanel.add(Box.createVerticalGlue()); // Espaciado inferior flexible
        return loginPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppMainGUI app = new AppMainGUI();
            app.setVisible(true);
        });
    }
}
