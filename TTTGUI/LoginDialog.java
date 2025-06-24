package TTTGUI;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean succeeded;
    private ImageIcon backgroundIcon;

    public LoginDialog(Frame parent) {
        super(parent, "Login", true);

        // Load background image
        try {
            backgroundIcon = new ImageIcon(getClass().getResource("/TTTGUI/background.jpg")); // or background.png
        } catch (Exception e) {
            System.err.println("Background image not found.");
            e.printStackTrace();
        }

        // Custom panel with background
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundIcon != null) {
                    g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // Allows us to center the form

        // ===== Login Form Panel =====
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setOpaque(false); // transparent so background shows
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        formPanel.add(loginButton);
        formPanel.add(cancelButton);

        // Add form panel to background panel
        backgroundPanel.add(formPanel); // centered due to GridBagLayout

        setContentPane(backgroundPanel);

        // ===== Button Actions =====
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            try {
                if (password.equals(GameMain.retrievePassword(username))) {
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                    succeeded = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        cancelButton.addActionListener(e -> {
            succeeded = false;
            dispose();
        });

        pack();
        setSize(400, 300); // fixed size for better background scaling
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
