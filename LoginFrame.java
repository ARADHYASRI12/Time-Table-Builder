package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {
  private model.User authenticateUser(String username, String password, String role) {
    try (Connection conn = db.DBConnection.getConnection()) {
      String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, username);
      stmt.setString(2, password);
      stmt.setString(3, role);

      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String name = rs.getString("username");
        String uname = rs.getString("username");
        String pass = rs.getString("password");
        String mail = rs.getString("email");
        String dep = rs.getString("department");
        String rol = rs.getString("role");
        return new model.User(name, uname, pass, mail, dep, rol);
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
    }
    return null;
  }

  public LoginFrame() {
    setTitle("Time Table Builder - Login");
    setSize(800, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    add(titleLabel, BorderLayout.NORTH);

    // --- Center Panel: Login Form ---
    JPanel formPanel = new JPanel(new GridLayout(5, 1, 5, 5));
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    // Role Selection
    JRadioButton adminBtn = new JRadioButton("Admin");
    JRadioButton teacherBtn = new JRadioButton("Teacher");
    JRadioButton studentBtn = new JRadioButton("Student");

    ButtonGroup group = new ButtonGroup();
    group.add(adminBtn);
    group.add(teacherBtn);
    group.add(studentBtn);
    adminBtn.setSelected(true);

    JPanel radioPanel = new JPanel(new FlowLayout());
    radioPanel.add(adminBtn);
    radioPanel.add(teacherBtn);
    radioPanel.add(studentBtn);

    formPanel.add(new JLabel("Username:"));
    formPanel.add(usernameField);
    formPanel.add(new JLabel("Password:"));
    formPanel.add(passwordField);
    formPanel.add(radioPanel);

    // adding formPanel
    formPanel.setPreferredSize(new Dimension(300, 200)); // or whatever size you want
    JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
    centerWrapper.add(formPanel);
    add(centerWrapper, BorderLayout.CENTER);

    // --- Bottom Panel: Login + Register ---
    JPanel bottomPanel = new JPanel(new GridLayout(2, 1));

    JButton loginBtn = new JButton("Login");
    loginBtn.addActionListener(e -> {
      String selectedRole = adminBtn.isSelected() ? "Admin" : teacherBtn.isSelected() ? "Teacher" : "Student";
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());

      // Auth Logic
      model.User user = authenticateUser(username, password, selectedRole);
      if (user != null) {
        JOptionPane.showMessageDialog(this, "Login successful! Welcome " + user.getUsername());
        new DashboardFrame(user); // Or pass user object if DashboardFrame supports it
        dispose();
      } else {
        JOptionPane.showMessageDialog(this, "Invalid username/password");
        usernameField.setText("");
        passwordField.setText("");
      }

    });

    JPanel registerPanel = new JPanel(new FlowLayout());
    JLabel regLabel = new JLabel("New user?");
    JButton regBtn = new JButton("Register Here");
    regBtn.setBorderPainted(false);
    regBtn.setForeground(Color.BLUE);
    regBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    regBtn.addActionListener(e -> {
      new RegisterFrame();
      dispose();
    });

    registerPanel.add(regLabel);
    registerPanel.add(regBtn);

    bottomPanel.add(loginBtn);
    bottomPanel.add(registerPanel);

    add(bottomPanel, BorderLayout.SOUTH);

    setVisible(true);
  }
}
