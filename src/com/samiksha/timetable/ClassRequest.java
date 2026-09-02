package com.samiksha.timetable;

public class ClassRequest {

    private String section;
    private Teacher teacher;
    private int studentCount;

    public ClassRequest(
            String section,
            Teacher teacher,
            int studentCount
    ) {
        this.section = section;
        this.teacher = teacher;
        this.studentCount = studentCount;
    }

    public String getSection() {
        return section;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getStudentCount() {
        return studentCount;
    }
}