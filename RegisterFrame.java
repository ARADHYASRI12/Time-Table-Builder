package ui;

import javax.swing.*;

import db.DBConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterFrame extends JFrame {
  public RegisterFrame() {
    setTitle("Register User");
    setSize(800, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JLabel titleLabel = new JLabel("Register New User", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    add(titleLabel, BorderLayout.NORTH);

    // Form components
    JPanel formPanel = new JPanel(new GridLayout(10, 1, 5, 5));

    JTextField nameField = new JTextField();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailField = new JTextField();
    JTextField departmentField = new JTextField();

    // Role Selection
    JRadioButton adminBtn = new JRadioButton("Admin");
    JRadioButton teacherBtn = new JRadioButton("Teacher");
    JRadioButton studentBtn = new JRadioButton("Student");
    ButtonGroup group = new ButtonGroup();
    group.add(adminBtn);
    group.add(teacherBtn);
    group.add(studentBtn);
    studentBtn.setSelected(true); // default

    JPanel rolePanel = new JPanel(new FlowLayout());
    rolePanel.add(adminBtn);
    rolePanel.add(teacherBtn);
    rolePanel.add(studentBtn);

    // Add to form
    formPanel.add(new JLabel("Full Name:"));
    formPanel.add(nameField);
    formPanel.add(new JLabel("Username:"));
    formPanel.add(usernameField);
    formPanel.add(new JLabel("Password:"));
    formPanel.add(passwordField);
    formPanel.add(new JLabel("Email:"));
    formPanel.add(emailField);
    formPanel.add(new JLabel("Department:"));
    formPanel.add(departmentField);
    formPanel.add(new JLabel("Select Role:"));
    formPanel.add(rolePanel);

    JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
    centerWrapper.add(formPanel);
    add(centerWrapper, BorderLayout.CENTER);

    // Register button logic
    JButton registerBtn = new JButton("Register");
    registerBtn.addActionListener(e -> {
      String name = nameField.getText();
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());
      String email = emailField.getText();
      String dept = departmentField.getText();
      String role = adminBtn.isSelected() ? "Admin" : teacherBtn.isSelected() ? "Teacher" : "Student";

      if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || dept.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // Insert to DB
      try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO users (name, username, password, email, department, role) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, username);
        stmt.setString(3, password);
        stmt.setString(4, email);
        stmt.setString(5, dept);
        stmt.setString(6, role);
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Registered successfully!");
        new LoginFrame();
        dispose();

      } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
      }

    });

    add(registerBtn, BorderLayout.SOUTH);
    setVisible(true);
  }
}
