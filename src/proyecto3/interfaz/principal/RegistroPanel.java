package proyecto3.interfaz.principal;

import javax.swing.JPanel;

class RegistroPanel extends JPanel {
    public RegistroPanel(AppMainGUI parent) {
        setLayout(new GridLayout(6, 2, 5, 5));
        add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField();
        add(nameField);

        add(new JLabel("Correo:"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        ButtonGroup roleGroup = new ButtonGroup();
        JRadioButton studentRadio = new JRadioButton("Estudiante");
        JRadioButton professorRadio = new JRadioButton("Profesor");
        roleGroup.add(studentRadio);
        roleGroup.add(professorRadio);

        add(new JLabel("Rol:"));
        JPanel rolePanel = new JPanel();
        rolePanel.add(studentRadio);
        rolePanel.add(professorRadio);
        add(rolePanel);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(e -> {
            // Lógica para registrar usuario
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String role = studentRadio.isSelected() ? "Estudiante" : "Profesor";
            System.out.println("Registro exitoso: " + name + " - " + role);
            parent.showPanel("MainMenu");
        });
        add(registerButton);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> parent.showPanel("MainMenu"));
        add(backButton);
    }
}