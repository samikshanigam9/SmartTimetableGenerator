package com.samiksha.timetable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimetableGeneratorTest {
    private static TimetableGenerator generator() {
        return new TimetableGenerator(
                new Classroom[]{new Classroom("LARGE", 80), new Classroom("SMALL", 35)},
                new TimeSlot[]{new TimeSlot("Monday", "09:00 AM", "10:00 AM")});
    }

    @Test
    void selectsSmallestSuitableRoomFromUnsortedRooms() {
        TimetableGenerator generator = generator();
        generator.generateSchedule(new ClassRequest[]{request("A", 35)});
        String output = ConsoleTestSupport.capture("", generator::displayGeneratedTimetable);
        assertEquals(1, generator.getScheduledCount());
        assertTrue(output.lines().anyMatch(line -> line.startsWith("Monday") && line.contains("SMALL")));
    }

    @Test
    void skipsRoomsWithInsufficientCapacity() {
        TimetableGenerator generator = generator();
        generator.generateSchedule(new ClassRequest[]{request("A", 36)});
        String output = ConsoleTestSupport.capture("", generator::displayGeneratedTimetable);
        assertEquals(1, generator.getScheduledCount());
        assertTrue(output.lines().anyMatch(line -> line.startsWith("Monday") && line.contains("LARGE")));
    }

    @Test
    void reportsUnscheduledRequestWhenAllRoomsAreTooSmall() {
        TimetableGenerator generator = generator();
        String output = ConsoleTestSupport.capture("", () ->
                generator.generateSchedule(new ClassRequest[]{request("A", 81)}));
        assertEquals(0, generator.getScheduledCount());
        assertTrue(output.contains("No suitable schedule found for A - DSA"));
    }

    private static ClassRequest request(String section, int size) {
        return new ClassRequest(section, new Teacher(1, "Amit", "DSA"), size);
    }
}
