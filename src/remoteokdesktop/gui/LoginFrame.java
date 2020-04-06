package remoteokdesktop.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.miginfocom.swing.MigLayout;
import remoteokdesktop.model.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class LoginFrame extends JFrame {

    private ObjectMapper mapper = new ObjectMapper();
    private final String AUTHENTICATION_PATH = "Authentication.txt";

    public LoginFrame() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(250, 220));
        createComponents();
        this.setVisible(true);
        this.getContentPane().setBackground(Color.WHITE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void createComponents()  {
        JPanel credentialsPanel = new WhitePanel(new MigLayout("fillx"));
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField(15);
        credentialsPanel.add(usernameLabel, "wrap");
        credentialsPanel.add(usernameField, "wrap");

        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField(15);
        credentialsPanel.add(passwordLabel, "wrap");
        credentialsPanel.add(passwordField, "wrap");

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ae) -> {
            try {
                String result = Files.readAllLines(Paths.get(AUTHENTICATION_PATH)).stream().collect(Collectors.joining("\n"));
                User user = mapper.readValue(result, User.class);
                if(usernameField.getText().equals(user.getUsername()) && passwordField.getText().equals(user.getPassword())) {
                    Preferences userPreferences = Preferences.userRoot();

                    loginButton.setText("Loading...");
                    loginButton.setEnabled(false);
                    new Thread(() -> {
                        ListFrame listFrame = new ListFrame(user.getEmail());
                        LoginFrame.this.dispose();
                    }).start();
                } else {
                    JOptionPane.showMessageDialog(new JFrame("Wrong credentials"), "Wrong username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        credentialsPanel.add(loginButton, "wrap");

        this.add(credentialsPanel);
    }
}
