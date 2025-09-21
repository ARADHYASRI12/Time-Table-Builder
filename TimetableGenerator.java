package util;

import model.*;

import java.util.*;

public class TimetableGenerator {
  private List<Course> courses;
  private List<Classroom> classrooms;
  private List<User> instructors;
  private Map<TimeSlot, ScheduledClass> timetable;

  public TimetableGenerator(List<Course> courses, List<Classroom> classrooms, List<User> instructors) {
    this.courses = courses;
    this.classrooms = classrooms;
    this.instructors = instructors;
    this.timetable = new HashMap<>();
  }

  public Map<TimeSlot, ScheduledClass> generate() {
    String[] days = { "Mon", "Tue", "Wed", "Thu", "Fri" };
    int periodsPerDay = 8;

    Random rand = new Random();

    for (Course course : courses) {
      for (int c = 0; c < course.getCredits(); c++) {
        boolean scheduled = false;

        for (int attempt = 0; attempt < 100 && !scheduled; attempt++) {
          String day = days[rand.nextInt(days.length)];
          int period = rand.nextInt(periodsPerDay) + 1;

          TimeSlot slot = new TimeSlot(day, period);

          boolean instructorFree = true;
          boolean classroomFree = true;

          // Find an available classroom
          Classroom availableRoom = null;
          for (Classroom cl : classrooms) {
            boolean isTaken = false;
            for (ScheduledClass sc : timetable.values()) {
              if (sc.getClassroom().getName().equals(cl.getName()) &&
                  sc.getTimeSlot().equals(slot)) {
                isTaken = true;
                break;
              }
            }
            if (!isTaken) {
              availableRoom = cl;
              break;
            }
          }

          // Check instructor conflict
          for (ScheduledClass sc : timetable.values()) {
            if (sc.getInstructor().getUsername().equals(course.getIC().getUsername()) &&
                sc.getTimeSlot().equals(slot)) {
              instructorFree = false;
              break;
            }
          }

          if (availableRoom != null && instructorFree) {
            ScheduledClass newClass = new ScheduledClass(course, course.getIC(), availableRoom, slot);
            timetable.put(slot, newClass);
            scheduled = true;
          }
        }
      }
    }

    return timetable;
  }

  public void printTimetable() {
    for (Map.Entry<TimeSlot, ScheduledClass> entry : timetable.entrySet()) {
      TimeSlot slot = entry.getKey();
      ScheduledClass sc = entry.getValue();
      System.out.println(slot.getDay() + " Period " + slot.getPeriod() + ": " +
          sc.getCourse().getCode() + " by " +
          sc.getInstructor().getUsername() + " in " +
          sc.getClassroom().getName());
    }
  }
}
