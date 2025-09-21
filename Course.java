package model;

import java.util.*;

public class Course {
  String code;
  String name;
  User IC;
  int credits;
  List<TimeSlot> timeSlots;

  public Course(String code, String name, User IC, List<User> instructors, int credits) {
    this.code = code;
    this.name = name;
    this.IC = IC;
    this.credits = credits;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public List<TimeSlot> getTimeSlots() {
    return timeSlots;
  }

  public int getCredits() {
    return credits;
  }

  public User getIC() {
    return IC;
  }
}
