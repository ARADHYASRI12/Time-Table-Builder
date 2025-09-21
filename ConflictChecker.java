package util;

import model.ScheduledClass;

import java.util.*;

public class ConflictChecker {

  public static boolean hasConflicts(List<ScheduledClass> classes) {
    Set<String> instructorSlots = new HashSet<>();
    Set<String> roomSlots = new HashSet<>();
    Set<String> courseConflicts = new HashSet<>();

    for (ScheduledClass cls : classes) {
      String instKey = cls.getInstructor().getUsername() + "_" + cls.getTimeSlot().getDay() + "_" + cls.getTimeSlot().getPeriod();
      String roomKey = cls.getClassroom().getName() + "_" + cls.getTimeSlot().getDay() + "_" + cls.getTimeSlot().getPeriod();
      String courseKey = cls.getCourse().getCode() + "_" + cls.getTimeSlot().getDay() + "_" + cls.getTimeSlot().getPeriod();

      if (!instructorSlots.add(instKey) || !roomSlots.add(roomKey) || !courseConflicts.add(courseKey)) {
        return true;
      }
    }
    return false;
  }
}