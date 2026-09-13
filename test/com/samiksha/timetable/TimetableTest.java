package com.samiksha.timetable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimetableTest {

    @Test
    void acceptsSameTeacherRoomAndSectionAtDifferentTimes() {
        Timetable timetable = new Timetable();
        Teacher teacher = new Teacher(1, "A", "DSA");
        Classroom room = new Classroom("C101", 60);
        assertTrue(timetable.addSchedule(new ClassSchedule(
                teacher, room, slot("Monday", "09:00 AM", "10:00 AM"), "A1", 40)));
        assertTrue(timetable.addSchedule(new ClassSchedule(
                teacher, room, slot("Monday", "10:00 AM", "11:00 AM"), "A1", 40)));
        assertEquals(2, timetable.getScheduleCount());
    }

    private static TimeSlot slot(String day, String start, String end) {
        return new TimeSlot(day, start, end);
    }

    @Test
    void acceptsNonConflictingSchedules() {
        Timetable timetable = new Timetable();
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");

        ClassSchedule first = new ClassSchedule(
                new Teacher(1, "A", "DSA"),
                new Classroom("C101", 60),
                slot,
                "A1",
                40
        );

        ClassSchedule second = new ClassSchedule(
                new Teacher(2, "B", "DBMS"),
                new Classroom("C102", 60),
                slot,
                "A2",
                40
        );

        assertTrue(timetable.addSchedule(first));
        assertTrue(timetable.addSchedule(second));
        assertEquals(2, timetable.getScheduleCount());
    }

    @Test
    void rejectsTeacherConflict() {
        Timetable timetable = new Timetable();
        Teacher teacher = new Teacher(1, "A", "DSA");
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");

        assertTrue(timetable.addSchedule(new ClassSchedule(
                teacher,
                new Classroom("C101", 60),
                slot,
                "A1",
                40
        )));

        assertFalse(timetable.addSchedule(new ClassSchedule(
                teacher,
                new Classroom("C102", 60),
                slot,
                "A2",
                40
        )));
    }

    @Test
    void rejectsClassroomConflict() {
        Timetable timetable = new Timetable();
        Classroom room = new Classroom("C101", 60);
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");

        assertTrue(timetable.addSchedule(new ClassSchedule(
                new Teacher(1, "A", "DSA"),
                room,
                slot,
                "A1",
                40
        )));

        assertFalse(timetable.addSchedule(new ClassSchedule(
                new Teacher(2, "B", "DBMS"),
                room,
                slot,
                "A2",
                40
        )));
    }

    @Test
    void rejectsSectionConflict() {
        Timetable timetable = new Timetable();
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");

        assertTrue(timetable.addSchedule(new ClassSchedule(
                new Teacher(1, "A", "DSA"),
                new Classroom("C101", 60),
                slot,
                "A1",
                40
        )));

        assertFalse(timetable.addSchedule(new ClassSchedule(
                new Teacher(2, "B", "DBMS"),
                new Classroom("C102", 60),
                slot,
                "A1",
                40
        )));
    }
}
