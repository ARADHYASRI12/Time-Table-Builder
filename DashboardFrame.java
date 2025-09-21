package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
  public DashboardFrame(model.User user) {
    String role = user.getRole();
    setTitle(role + " Dashboard");
    setSize(800, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Create a container panel to hold dashboard + logout
    JPanel wrapperPanel = new JPanel(new BorderLayout());

    // Top bar with Logout Button
    JButton logoutButton = new JButton("Log Out");
    logoutButton.addActionListener(e -> {
      dispose(); // close this frame
      new LoginFrame(); // show login again
    });

    JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    topBar.add(logoutButton);

    // Load role-based content
    JPanel dashboardPanel = null;
    switch (role) {
      case "Admin":
        dashboardPanel = new AdminPanel(user);
        break;
      case "Teacher":
        dashboardPanel = new TeacherPanel(user);
        break;
      case "Student":
        dashboardPanel = new StudentPanel(user);
        break;
    }

    wrapperPanel.add(topBar, BorderLayout.NORTH);
    wrapperPanel.add(dashboardPanel, BorderLayout.CENTER);

    setContentPane(wrapperPanel);
    setVisible(true);
  }
}
