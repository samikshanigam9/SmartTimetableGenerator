package com.samiksha.timetable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InteractiveMainTest {
    @Test
    void sameTeacherAcrossSubjectsCannotBeDoubleBooked() {
        String output = run("2\n7\nAmit\nDSA\nA1\n30\n7\nAmit\nJava\nA2\n30\n");
        assertEquals(1, rowsAt(output, "09:00 AM-10:00 AM"));
        assertEquals(1, rowsAt(output, "10:00 AM-11:00 AM"));
        assertTrue(output.contains("DSA"));
        assertTrue(output.contains("Java"));
        assertTrue(output.contains("Total classes scheduled: 2"));
    }

    @Test
    void distinctTeachersWithSameNameCanTeachSimultaneously() {
        String output = run("2\n7\nAmit\nDSA\nA1\n30\n8\nAmit\nJava\nA2\n30\n");
        assertEquals(2, rowsAt(output, "09:00 AM-10:00 AM"));
        assertTrue(output.contains("Total classes scheduled: 2"));
    }

    private static String run(String input) {
        return ConsoleTestSupport.capture(input, () -> InteractiveMain.main(new String[0]));
    }

    private static long rowsAt(String output, String time) {
        return output.lines().filter(line -> line.startsWith("Monday") && line.contains(time)).count();
    }
}
