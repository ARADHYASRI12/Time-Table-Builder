package model;

public class ScheduledClass {
  private Course course;
  private User instructor;
  private Classroom classroom;
  private TimeSlot timeSlot;

  public ScheduledClass(Course course, User instructor, Classroom classroom, TimeSlot timeSlot) {
    this.course = course;
    this.instructor = instructor;
    this.classroom = classroom;
    this.timeSlot = timeSlot;
  }

  public Course getCourse() {
    return course;
  }

  public User getInstructor() {
    return instructor;
  }

  public Classroom getClassroom() {
    return classroom;
  }

  public TimeSlot getTimeSlot() {
    return timeSlot;
  }
}