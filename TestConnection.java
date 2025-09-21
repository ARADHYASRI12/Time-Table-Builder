package db;

public class TestConnection {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      java.sql.Connection conn = DBConnection.getConnection();
      System.out.println("✅ Connected successfully to MySQL!");
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
