package model;

public class Classroom {
  private int id;
  private String name;
  private boolean isLab;

  public Classroom(int id, String name, boolean isLab) {
    this.id = id;
    this.name = name;
    this.isLab = isLab;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isLab() {
    return isLab;
  }

}
