package proyecto3.interfaz.principal;

class LoginPanel extends JPanel {
    public LoginPanel(AppMainGUI parent) {
        setLayout(new GridLayout(4, 1, 10, 10));
        add(new JLabel("Correo:"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            // Aquí se agregará la lógica para autenticar el usuario
            System.out.println("Inicio de sesión: " + email);
            parent.showPanel("EstudianteMenu"); // Cambia según el rol del usuario
        });
        add(loginButton);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> parent.showPanel("MainMenu"));
        add(backButton);
    }
}