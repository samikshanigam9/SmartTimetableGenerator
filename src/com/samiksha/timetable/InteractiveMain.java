package com.samiksha.timetable;

import java.util.Scanner;

public class InteractiveMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Classroom[] classrooms = {

                new Classroom("C103", 35),
                new Classroom("C102", 50),
                new Classroom("C101", 60)
        };

        TimeSlot[] timeSlots = {

                new TimeSlot(
                        "Monday",
                        "09:00 AM",
                        "10:00 AM"
                ),

                new TimeSlot(
                        "Monday",
                        "10:00 AM",
                        "11:00 AM"
                ),

                new TimeSlot(
                        "Monday",
                        "11:00 AM",
                        "12:00 PM"
                ),

                new TimeSlot(
                        "Tuesday",
                        "09:00 AM",
                        "10:00 AM"
                ),

                new TimeSlot(
                        "Tuesday",
                        "10:00 AM",
                        "11:00 AM"
                ),

                new TimeSlot(
                        "Tuesday",
                        "11:00 AM",
                        "12:00 PM"
                )
        };

        System.out.println("SMART TIMETABLE GENERATOR");
        System.out.println("-------------------------");

        System.out.print("Enter number of classes: ");

        int totalClasses =
                Integer.parseInt(scanner.nextLine());

        ClassRequest[] requests =
                new ClassRequest[totalClasses];

        for (int i = 0; i < totalClasses; i++) {

            System.out.println();
            System.out.println(
                    "Enter details for class " + (i + 1)
            );

            System.out.print("Teacher ID (reuse for the same teacher): ");
            int teacherId = Integer.parseInt(scanner.nextLine());

            System.out.print("Teacher name: ");
            String teacherName = scanner.nextLine();

            System.out.print("Subject: ");
            String subject = scanner.nextLine();

            System.out.print("Section: ");
            String section = scanner.nextLine();

            System.out.print("Number of students: ");

            int studentCount =
                    Integer.parseInt(scanner.nextLine());

            Teacher teacher = new Teacher(
                    teacherId,
                    teacherName,
                    subject
            );

            requests[i] = new ClassRequest(
                    section,
                    teacher,
                    studentCount
            );
        }

        TimetableGenerator generator =
                new TimetableGenerator(
                        classrooms,
                        timeSlots
                );

        generator.generateSchedule(requests);

        generator.displayGeneratedTimetable();

        scanner.close();
    }
}
