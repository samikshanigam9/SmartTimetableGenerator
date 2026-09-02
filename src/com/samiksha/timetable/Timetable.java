package com.samiksha.timetable;

import java.util.HashMap;
import java.util.LinkedList;

public class Timetable {

    private LinkedList<ClassSchedule> schedules;

    private HashMap<String, ClassSchedule> teacherBookings;
    private HashMap<String, ClassSchedule> classroomBookings;
    private HashMap<String, ClassSchedule> sectionBookings;

    public Timetable() {
        schedules = new LinkedList<>();
        teacherBookings = new HashMap<>();
        classroomBookings = new HashMap<>();
        sectionBookings = new HashMap<>();
    }

    public boolean addSchedule(ClassSchedule schedule) {

        int teacherId =
                schedule.getTeacher().getTeacherId();

        String roomNumber =
                schedule.getClassroom().getRoomNumber();

        String section =
                schedule.getSection();

        String day =
                schedule.getTimeSlot().getDay();

        String startTime =
                schedule.getTimeSlot().getStartTime();

        String endTime =
                schedule.getTimeSlot().getEndTime();

        String teacherKey =
                teacherId + "|" + day + "|" + startTime + "|" + endTime;

        String classroomKey =
                roomNumber + "|" + day + "|" + startTime + "|" + endTime;

        String sectionKey =
                section + "|" + day + "|" + startTime + "|" + endTime;

        if (teacherBookings.containsKey(teacherKey)) {
            return false;
        }

        if (classroomBookings.containsKey(classroomKey)) {
            return false;
        }

        if (sectionBookings.containsKey(sectionKey)) {
            return false;
        }

        schedules.add(schedule);

        teacherBookings.put(teacherKey, schedule);
        classroomBookings.put(classroomKey, schedule);
        sectionBookings.put(sectionKey, schedule);

        return true;
    }

    public int getScheduleCount() {
        return schedules.size();
    }

    public void displayTimetable() {

        String line = "-".repeat(116);

        System.out.println();
        System.out.println("SMART TIMETABLE");
        System.out.println(line);

        System.out.printf(
                "%-10s %-21s %-10s %-24s %-18s %-9s %-8s %-8s%n",
                "DAY",
                "TIME",
                "SECTION",
                "SUBJECT",
                "TEACHER",
                "STUDENTS",
                "ROOM",
                "CAPACITY"
        );

        System.out.println(line);

        for (ClassSchedule schedule : schedules) {
            schedule.displaySchedule();
        }

        System.out.println(line);

        System.out.println(
                "Total classes scheduled: " + getScheduleCount()
        );
    }
}