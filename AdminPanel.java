package ui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
  public AdminPanel(model.User user) {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Admin Dashboard", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.NORTH);

    JTabbedPane tabbedPane = new JTabbedPane();

    tabbedPane.addTab("Add Classroom", new ui.panels.ClassroomEntryPanel());
    tabbedPane.addTab("Add Course", new ui.panels.CourseEntryPanel());
    tabbedPane.addTab("Manual Timetable", new ui.panels.ManualTimetablePanel());
    tabbedPane.addTab("Courses", new ui.panels.CourseListPanel());
    tabbedPane.addTab("Students", new ui.panels.StudentListPanel());
    tabbedPane.addTab("Teachers", new ui.panels.TeacherListPanel());

    add(tabbedPane, BorderLayout.CENTER);
  }
}
