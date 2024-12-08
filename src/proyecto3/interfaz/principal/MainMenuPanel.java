package proyecto3.interfaz.principal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class MainMenuPanel extends JPanel {
    public MainMenuPanel(AppMainGUI parent) {
        setLayout(new GridLayout(4, 1, 10, 10));
        JLabel title = new JLabel("Bienvenido al Sistema de Learning Paths", SwingConstants.CENTER);
        add(title);

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(e -> parent.showPanel("Registro"));
        add(registerButton);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> parent.showPanel("Login"));
        add(loginButton);

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }
}