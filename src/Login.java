
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Login {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private AdminDAO adminDAO;

    public Login() {

        adminDAO = new AdminDAO();

        frame = new JFrame("Student Management System - Admin Login");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));

        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        JLabel title = new JLabel("ADMIN LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        panel.add(title);
        panel.add(new JLabel(""));

        panel.add(usernameLabel);
        panel.add(usernameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(loginButton);
        panel.add(exitButton);

        loginButton.addActionListener(e -> login());
        passwordField.addActionListener(e -> login());

        exitButton.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void login() {

        String username = usernameField.getText().trim();

        char[] passwordChars = passwordField.getPassword();

        if (username.isEmpty() || passwordChars.length == 0) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter username and password!"
            );

            Arrays.fill(passwordChars, '\0');
            return;
        }

        String password = new String(passwordChars);
        Arrays.fill(passwordChars, '\0');

        System.out.println("Entered username: [" + username + "]");

        boolean valid = adminDAO.authenticateAdmin(
                username,
                password
        );

        System.out.println("Authentication result: " + valid);

        if (valid) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Login successful!"
            );

            frame.dispose();

            new StudentGUI();

        } else {

            JOptionPane.showMessageDialog(
                    frame,
                    "Invalid username or password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Login::new);
    }
}
