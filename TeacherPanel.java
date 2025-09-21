package ui;

import javax.swing.*;
import java.awt.*;

public class TeacherPanel extends JPanel {
  public TeacherPanel(model.User user) {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.NORTH);

    JTabbedPane tabbedPane = new JTabbedPane();

    tabbedPane.addTab("My Schedule", new ui.panels.TeacherTimetablePanel(user.getUsername()));
    tabbedPane.addTab("My Profile", new ui.panels.ProfilePanel(user));

    add(tabbedPane, BorderLayout.CENTER);
  }
}
