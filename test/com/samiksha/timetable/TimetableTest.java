package com.samiksha.timetable;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TimetableTest {

    private static TimeSlot slot(String day, String start, String end) {
        return new TimeSlot(day, start, end);
    }

    @Test
    void rejectsTeacherConflictInSameSlot() {
        Timetable timetable = new Timetable();
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");
        Teacher teacher = new Teacher(1, "A", "DSA");

        assertTrue(timetable.addSchedule(new ClassSchedule(
                teacher, new Classroom("C101", 60), slot, "AIDS-A", 50)));

        assertFalse(timetable.addSchedule(new ClassSchedule(
                teacher, new Classroom("C102", 60), slot, "CSE-A", 40)));
    }

    @Test
    void rejectsClassroomConflictInSameSlot() {
        Timetable timetable = new Timetable();
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");
        Classroom room = new Classroom("C101", 60);

        assertTrue(timetable.addSchedule(new ClassSchedule(
                new Teacher(1, "A", "DSA"), room, slot, "AIDS-A", 50)));

        assertFalse(timetable.addSchedule(new ClassSchedule(
                new Teacher(2, "B", "DBMS"), room, slot, "CSE-A", 40)));
    }

    @Test
    void rejectsSectionConflictInSameSlot() {
        Timetable timetable = new Timetable();
        TimeSlot slot = slot("Monday", "09:00 AM", "10:00 AM");

        assertTrue(timetable.addSchedule(new ClassSchedule(
                new Teacher(1, "A", "DSA"), new Classroom("C101", 60),
                slot, "AIDS-A", 50)));

        assertFalse(timetable.addSchedule(new ClassSchedule(
                new Teacher(2, "B", "DBMS"), new Classroom("C102", 60),
                slot, "AIDS-A", 40)));
    }

    @Test
    void allowsSameTeacherAtDifferentTimes() {
        Timetable timetable = new Timetable();
        Teacher teacher = new Teacher(1, "A", "DSA");

        assertTrue(timetable.addSchedule(new ClassSchedule(
                teacher, new Classroom("C101", 60),
                slot("Monday", "09:00 AM", "10:00 AM"), "AIDS-A", 50)));

        assertTrue(timetable.addSchedule(new ClassSchedule(
                teacher, new Classroom("C101", 60),
                slot("Monday", "10:00 AM", "11:00 AM"), "AIDS-B", 50)));
    }

    @Test
    void generatorUsesSmallestSuitableRoom() {
        Classroom[] rooms = {
                new Classroom("LARGE", 100),
                new Classroom("SMALL", 40),
                new Classroom("MEDIUM", 60)
        };

        TimeSlot[] slots = {
                slot("Monday", "09:00 AM", "10:00 AM")
        };

        TimetableGenerator generator = new TimetableGenerator(rooms, slots);
        generator.generateSchedule(new ClassRequest[]{
                new ClassRequest("AIDS-A", new Teacher(1, "A", "DSA"), 35)
        });

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output));
        try {
            generator.displayGeneratedTimetable();
        } finally {
            System.setOut(original);
        }

        assertTrue(output.toString().contains("SMALL"));
    }

    @Test
    void rejectsRequestWhenNoRoomHasEnoughCapacity() {
        TimetableGenerator generator = new TimetableGenerator(
                new Classroom[]{new Classroom("C101", 40)},
                new TimeSlot[]{slot("Monday", "09:00 AM", "10:00 AM")}
        );

        generator.generateSchedule(new ClassRequest[]{
                new ClassRequest("AIDS-A", new Teacher(1, "A", "DSA"), 70)
        });

        assertEquals(0, generator.getScheduledCount());
    }
}
