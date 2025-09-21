package model;

public class TimeSlot {
  private String day;
  private int period;

  public TimeSlot(String day, int period) {
    this.day = day;
    this.period = period;
  }

  public String getDay() {
    return day;
  }

  public int getPeriod() {
    return period;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TimeSlot that = (TimeSlot) o;
    return period == that.period && day.equals(that.day);
  }

  @Override
  public int hashCode() {
    return day.hashCode() * 31 + period;
  }
}