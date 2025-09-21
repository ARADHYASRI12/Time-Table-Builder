package db;

import java.sql.*;

public class DBConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/timetable_db?useSSL=false&serverTimezone=UTC";
  private static final String USER = "root"; // your DB user
  private static final String PASS = "Ojus@132"; // your DB password

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASS);
  }
}
