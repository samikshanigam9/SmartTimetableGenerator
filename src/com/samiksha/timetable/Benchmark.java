package com.samiksha.timetable;

import java.util.Arrays;
import java.util.LinkedList;

public class Benchmark {

    private static final int TOTAL_REQUESTS = 240;
    private static final int WARMUP_RUNS = 200;
    private static final int MEASURED_RUNS = 1000;

    public static void main(String[] args) {

        ClassRequest[] requests = createRequests();
        Classroom[] classrooms = createClassrooms();
        TimeSlot[] timeSlots = createTimeSlots();

        for (int i = 0; i < WARMUP_RUNS; i++) {
            runOptimizedSchedule(requests, classrooms, timeSlots);
            runLinearScanSchedule(requests, classrooms, timeSlots);
        }

        int optimizedScheduled = runOptimizedSchedule(requests, classrooms, timeSlots);
        int baselineScheduled = runLinearScanSchedule(requests, classrooms, timeSlots);

        long optimizedTotalTime = 0L;
        long baselineTotalTime = 0L;

        for (int i = 0; i < MEASURED_RUNS; i++) {
            if (i % 2 == 0) {
                baselineTotalTime += measureLinearScan(requests, classrooms, timeSlots);
                optimizedTotalTime += measureOptimized(requests, classrooms, timeSlots);
            } else {
                optimizedTotalTime += measureOptimized(requests, classrooms, timeSlots);
                baselineTotalTime += measureLinearScan(requests, classrooms, timeSlots);
            }
        }

        double optimizedAverageMs =
                optimizedTotalTime / (double) MEASURED_RUNS / 1_000_000.0;

        double baselineAverageMs =
                baselineTotalTime / (double) MEASURED_RUNS / 1_000_000.0;

        double improvementPercent =
                ((baselineAverageMs - optimizedAverageMs)
                        / baselineAverageMs) * 100.0;

        System.out.println();
        System.out.println("STRESS-TEST COMPARATIVE BENCHMARK");
        System.out.println("----------------------------------------");
        System.out.println("Requests per run: " + TOTAL_REQUESTS);
        System.out.println("Classrooms: " + classrooms.length);
        System.out.println("Time slots: " + timeSlots.length);
        System.out.println("Warm-up runs: " + WARMUP_RUNS);
        System.out.println("Measured runs: " + MEASURED_RUNS);
        System.out.println();
        System.out.println("Optimized scheduled: " + optimizedScheduled);
        System.out.println("Linear-scan scheduled: " + baselineScheduled);
        System.out.printf("Optimized average time: %.4f ms%n", optimizedAverageMs);
        System.out.printf("Linear-scan average time: %.4f ms%n", baselineAverageMs);
        System.out.printf("Measured improvement: %.2f%%%n", improvementPercent);
        System.out.println();
        System.out.println(
                "Note: Runtime results vary by JVM, hardware, and system load."
        );
    }

    private static long measureOptimized(
            ClassRequest[] requests,
            Classroom[] classrooms,
            TimeSlot[] timeSlots
    ) {
        long start = System.nanoTime();
        runOptimizedSchedule(requests, classrooms, timeSlots);
        return System.nanoTime() - start;
    }

    private static long measureLinearScan(
            ClassRequest[] requests,
            Classroom[] classrooms,
            TimeSlot[] timeSlots
    ) {
        long start = System.nanoTime();
        runLinearScanSchedule(requests, classrooms, timeSlots);
        return System.nanoTime() - start;
    }

    private static int runOptimizedSchedule(
            ClassRequest[] requests,
            Classroom[] classrooms,
            TimeSlot[] timeSlots
    ) {
        TimetableGenerator generator =
                new TimetableGenerator(classrooms, timeSlots);

        generator.generateSchedule(requests);
        return generator.getScheduledCount();
    }

    private static int runLinearScanSchedule(
            ClassRequest[] requests,
            Classroom[] classrooms,
            TimeSlot[] timeSlots
    ) {
        ClassRequest[] sortedRequests = requests.clone();
        Classroom[] sortedClassrooms = classrooms.clone();

        Arrays.sort(
                sortedRequests,
                (request1, request2) ->
                        Integer.compare(
                                request2.getStudentCount(),
                                request1.getStudentCount()
                        )
        );

        Arrays.sort(
                sortedClassrooms,
                (room1, room2) ->
                        Integer.compare(
                                room1.getCapacity(),
                                room2.getCapacity()
                        )
        );

        LinkedList<ClassSchedule> schedules = new LinkedList<>();

        for (ClassRequest request : sortedRequests) {
            boolean scheduled = false;

            for (TimeSlot timeSlot : timeSlots) {
                for (Classroom classroom : sortedClassrooms) {
                    if (classroom.getCapacity() < request.getStudentCount()) {
                        continue;
                    }

                    if (hasConflict(
                            schedules,
                            request,
                            classroom,
                            timeSlot
                    )) {
                        continue;
                    }

                    schedules.add(
                            new ClassSchedule(
                                    request.getTeacher(),
                                    classroom,
                                    timeSlot,
                                    request.getSection(),
                                    request.getStudentCount()
                            )
                    );

                    scheduled = true;
                    break;
                }

                if (scheduled) {
                    break;
                }
            }
        }

        return schedules.size();
    }

    private static boolean hasConflict(
            LinkedList<ClassSchedule> schedules,
            ClassRequest request,
            Classroom classroom,
            TimeSlot timeSlot
    ) {
        for (ClassSchedule existing : schedules) {
            if (!sameTimeSlot(existing.getTimeSlot(), timeSlot)) {
                continue;
            }

            if (existing.getTeacher().getTeacherId()
                    == request.getTeacher().getTeacherId()) {
                return true;
            }

            if (existing.getClassroom().getRoomNumber()
                    .equals(classroom.getRoomNumber())) {
                return true;
            }

            if (existing.getSection().equals(request.getSection())) {
                return true;
            }
        }

        return false;
    }

    private static boolean sameTimeSlot(
            TimeSlot first,
            TimeSlot second
    ) {
        return first.getDay().equals(second.getDay())
                && first.getStartTime().equals(second.getStartTime())
                && first.getEndTime().equals(second.getEndTime());
    }

    private static ClassRequest[] createRequests() {
        ClassRequest[] requests = new ClassRequest[TOTAL_REQUESTS];

        for (int i = 0; i < TOTAL_REQUESTS; i++) {
            Teacher teacher = new Teacher(
                    i + 1,
                    "Teacher " + (i + 1),
                    "Subject " + ((i % 12) + 1)
            );

            requests[i] = new ClassRequest(
                    "SECTION-" + (i + 1),
                    teacher,
                    30 + (i % 40)
            );
        }

        return requests;
    }

    private static Classroom[] createClassrooms() {
        Classroom[] classrooms = new Classroom[12];

        for (int i = 0; i < classrooms.length; i++) {
            classrooms[i] = new Classroom(
                    "C" + (101 + i),
                    80
            );
        }

        return classrooms;
    }

    private static TimeSlot[] createTimeSlots() {
        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        };

        String[] startTimes = {
                "09:00 AM",
                "10:00 AM",
                "11:00 AM",
                "12:00 PM"
        };

        String[] endTimes = {
                "10:00 AM",
                "11:00 AM",
                "12:00 PM",
                "01:00 PM"
        };

        TimeSlot[] timeSlots =
                new TimeSlot[days.length * startTimes.length];

        int index = 0;

        for (String day : days) {
            for (int i = 0; i < startTimes.length; i++) {
                timeSlots[index] = new TimeSlot(
                        day,
                        startTimes[i],
                        endTimes[i]
                );
                index++;
            }
        }

        return timeSlots;
    }
}
