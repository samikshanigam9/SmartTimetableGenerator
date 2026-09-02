package com.samiksha.timetable;

public class Benchmark {

    public static void main(String[] args) {

        int totalRequests = 120;

        ClassRequest[] requests =
                new ClassRequest[totalRequests];

        for (int i = 0; i < totalRequests; i++) {

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

        Classroom[] classrooms = {

                new Classroom("C101", 40),
                new Classroom("C102", 50),
                new Classroom("C103", 60),
                new Classroom("C104", 80)
        };

        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        };

        String[] startTimes = {
                "08:00 AM",
                "09:00 AM",
                "10:00 AM",
                "11:00 AM",
                "12:00 PM",
                "01:00 PM",
                "02:00 PM",
                "03:00 PM"
        };

        String[] endTimes = {
                "09:00 AM",
                "10:00 AM",
                "11:00 AM",
                "12:00 PM",
                "01:00 PM",
                "02:00 PM",
                "03:00 PM",
                "04:00 PM"
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

        TimetableGenerator generator =
                new TimetableGenerator(
                        classrooms,
                        timeSlots
                );

        long startTime = System.nanoTime();

        generator.generateSchedule(requests);

        long endTime = System.nanoTime();

        int scheduled = generator.getScheduledCount();
        int failed = totalRequests - scheduled;

        double timeInMilliseconds =
                (endTime - startTime) / 1_000_000.0;

        System.out.println();
        System.out.println("BENCHMARK RESULTS");
        System.out.println("----------------------------");
        System.out.println("Requests received: " + totalRequests);
        System.out.println("Successfully scheduled: " + scheduled);
        System.out.println("Failed requests: " + failed);

        System.out.printf(
                "Generation time: %.3f ms%n",
                timeInMilliseconds
        );

        if (scheduled == totalRequests) {
            System.out.println("Benchmark status: PASSED");
        } else {
            System.out.println("Benchmark status: PARTIALLY PASSED");
        }
    }
}
