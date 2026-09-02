package com.samiksha.timetable;

public class Teacher {

    private int teacherId;
    private String name;
    private String subject;

    public Teacher(int teacherId, String name, String subject) {
        this.teacherId = teacherId;
        this.name = name;
        this.subject = subject;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public void displayTeacher() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Teacher Name: " + name);
        System.out.println("Subject: " + subject);
    }
}