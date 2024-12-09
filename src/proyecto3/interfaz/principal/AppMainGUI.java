package proyecto3.interfaz.principal;


import proyecto1.Persistencia.IPersistenciaUsuarios;
import proyecto1.Persistencia.PersistenciaUsuariosBinario;
import proyecto1.estudiante.Estudiante;
import proyecto1.profesor.Profesor;
import proyecto1.usuario.Usuario;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

import learningpaths.LearningPaths;

import java.awt.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class AppMainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<Usuario> usuarios; 
    private IPersistenciaUsuarios persistencia; 
    private List<LearningPaths> learningPaths;

    public AppMainGUI(IPersistenciaUsuarios persistencia) {
        this.persistencia = persistencia;

        // Cargar usuarios al iniciar la aplicación
        try {
            usuarios = persistencia.cargarUsuarios("usuarios.bin");
        } catch (IOException e) {
            usuarios = new ArrayList<>();
            System.err.println("No se pudieron cargar los usuarios. Se iniciará con una lista vacía.");
        }

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
        mainPanel.add("ProfesorPanel", new ProfesorPanel(this)); 
        mainPanel.add(new EstudiantePanel(this), "EstudiantePanel");

        add(mainPanel);
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        try {
            persistencia.salvarUsuarios("usuarios.bin", usuarios); 
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Bienvenido al Sistema de Learning Paths", SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(title);

        JButton registerButton = new JButton("Registrarse");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> showPanel("Registro"));
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(registerButton);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> showPanel("Login"));
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(loginButton);

        JButton exitButton = new JButton("Salir");
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));
        mainMenuPanel.add(Box.createVerticalStrut(20));
        mainMenuPanel.add(exitButton);

        mainMenuPanel.add(Box.createVerticalGlue());
        return mainMenuPanel;
    }

    private JPanel createRegistroPanel() {
        JPanel registroPanel = new JPanel();
        registroPanel.setLayout(new BoxLayout(registroPanel, BoxLayout.Y_AXIS));

        registroPanel.add(Box.createVerticalStrut(20));
        JLabel title = new JLabel("Registro");
        title.setAlignmentX(CENTER_ALIGNMENT);
        registroPanel.add(title);
        registroPanel.add(Box.createVerticalStrut(20));

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Correo:"));
        JTextField emailField = new JTextField(15);
        emailPanel.add(emailField);
        registroPanel.add(emailPanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        registroPanel.add(passwordPanel);

        JPanel rolePanel = new JPanel(new FlowLayout());
        ButtonGroup roleGroup = new ButtonGroup();
        JRadioButton studentRadio = new JRadioButton("Estudiante");
        JRadioButton professorRadio = new JRadioButton("Profesor");
        roleGroup.add(studentRadio);
        roleGroup.add(professorRadio);
        rolePanel.add(studentRadio);
        rolePanel.add(professorRadio);
        registroPanel.add(rolePanel);

        JButton registerButton = new JButton("Registrar");
        registerButton.setAlignmentX(CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String role = studentRadio.isSelected() ? "Estudiante" : professorRadio.isSelected() ? "Profesor" : "";

            if (email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Usuario nuevoUsuario;

                if (role.equals("Estudiante")) {
                    // Crear un nuevo estudiante
                    nuevoUsuario = new Estudiante(
                        "E" + System.currentTimeMillis(), 
                        "Estudiante", 
                        email, 
                        password, 
                        List.of("Sin intereses") 
                    );
                } else {
                    // Crear un nuevo profesor
                    nuevoUsuario = new Profesor(
                        "P" + System.currentTimeMillis(), 
                        "Profesor", 
                        email, 
                        password
                    );
                }

                // Agregar a la lista de usuarios y guardar en archivo
                usuarios.add(nuevoUsuario);
                try {
                    persistencia.salvarUsuarios("usuarios.bin", usuarios); 
                    JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    showPanel("MainMenu");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        registroPanel.add(registerButton);

        JButton backButton = new JButton("Volver");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> showPanel("MainMenu"));
        registroPanel.add(Box.createVerticalStrut(10));
        registroPanel.add(backButton);

        registroPanel.add(Box.createVerticalGlue());
        return registroPanel;
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        loginPanel.add(Box.createVerticalStrut(20));
        JLabel title = new JLabel("Inicio de Sesión");
        title.setAlignmentX(CENTER_ALIGNMENT);
        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(20));

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(new JLabel("Correo:"));
        JTextField emailField = new JTextField(15);
        emailPanel.add(emailField);
        loginPanel.add(emailPanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        loginPanel.add(passwordPanel);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            boolean valid = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(password)) {
                    valid = true;
                    JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso como " + usuario.getRol(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    showPanel(usuario.getRol().equals("Profesor") ? "ProfesorPanel" : "MainMenu");
                    break;
                }
            }

            if (!valid) {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginPanel.add(loginButton);

        JButton backButton = new JButton("Volver");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> showPanel("MainMenu"));
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(backButton);

        loginPanel.add(Box.createVerticalGlue());
        return loginPanel;
    }
    
    public List<LearningPaths> getLearningPaths() {
        return learningPaths;
    }

    public static void main(String[] args) {
        IPersistenciaUsuarios persistencia = new PersistenciaUsuariosBinario(); 
        SwingUtilities.invokeLater(() -> {
            AppMainGUI app = new AppMainGUI(persistencia);
            app.setVisible(true);
        });
    }
}