package model;

import java.util.*;

public class User {
  String name;
  String username;
  String password;
  String email;
  String department;
  String role;
  List<Course> enrolledCourses;

  public User(String name, String username, String password, String email, String department, String role) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.email = email;
    this.department = department;
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getDepartment() {
    return department;
  }

  public String getRole() {
    return role;
  }

  public List<Course> getEnrolledCourses() {
    return enrolledCourses;
  }

  public void setEnrolledCourses(List<Course> enrolledCourses) {
    this.enrolledCourses = enrolledCourses;
  }
}
