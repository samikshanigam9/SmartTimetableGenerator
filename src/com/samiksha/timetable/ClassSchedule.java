package com.samiksha.timetable;

public class ClassSchedule {

    private Teacher teacher;
    private Classroom classroom;
    private TimeSlot timeSlot;

    private String section;
    private int studentCount;

    public ClassSchedule(
            Teacher teacher,
            Classroom classroom,
            TimeSlot timeSlot
    ) {
        this(
                teacher,
                classroom,
                timeSlot,
                "General",
                0
        );
    }

    public ClassSchedule(
            Teacher teacher,
            Classroom classroom,
            TimeSlot timeSlot,
            String section,
            int studentCount
    ) {
        this.teacher = teacher;
        this.classroom = classroom;
        this.timeSlot = timeSlot;
        this.section = section;
        this.studentCount = studentCount;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getSection() {
        return section;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void displaySchedule() {

        System.out.printf(
                "%-10s %-21s %-10s %-24s %-18s %-9d %-8s %-8d%n",
                timeSlot.getDay(),
                timeSlot.getStartTime() + "-" + timeSlot.getEndTime(),
                section,
                teacher.getSubject(),
                teacher.getName(),
                studentCount,
                classroom.getRoomNumber(),
                classroom.getCapacity()
        );
    }
}