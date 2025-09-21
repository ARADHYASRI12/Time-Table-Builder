package ui;

import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {
  public StudentPanel(model.User user) {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Student Dashboard", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.NORTH);

    JTabbedPane tabbedPane = new JTabbedPane();

    tabbedPane.addTab("My Timetable", new ui.panels.StudentTimetablePanel(user.getUsername()));
    tabbedPane.addTab("Available Courses", new ui.panels.AvailableCoursesPanel());
    tabbedPane.addTab("My Profile", new ui.panels.ProfilePanel(user));

    add(tabbedPane, BorderLayout.CENTER);
  }
}
