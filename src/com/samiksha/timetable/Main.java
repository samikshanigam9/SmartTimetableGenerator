package com.samiksha.timetable;

public class Main {

    public static void main(String[] args) {

        Teacher[] teachers = {

                new Teacher(1, "Amit Sharma", "DSA"),
                new Teacher(2, "Riya Verma", "DBMS"),
                new Teacher(3, "Rahul Singh", "Artificial Intelligence"),
                new Teacher(4, "Neha Jain", "Operating Systems"),
                new Teacher(5, "Vikram Patel", "Computer Networks"),
                new Teacher(6, "Pooja Mehta", "Java Programming"),
                new Teacher(7, "Arjun Rao", "Python Programming"),
                new Teacher(8, "Sneha Gupta", "Software Engineering"),
                new Teacher(9, "Karan Malhotra", "Cloud Computing"),
                new Teacher(10, "Anjali Joshi", "Cyber Security")
        };

        ClassRequest[] requests = {

                new ClassRequest(
                        "AIDS-A",
                        teachers[0],
                        55
                ),

                new ClassRequest(
                        "AIDS-B",
                        teachers[1],
                        45
                ),

                new ClassRequest(
                        "CSE-A",
                        teachers[2],
                        30
                ),

                new ClassRequest(
                        "CSE-B",
                        teachers[3],
                        52
                ),

                new ClassRequest(
                        "AIML-A",
                        teachers[4],
                        40
                ),

                new ClassRequest(
                        "AIML-B",
                        teachers[5],
                        48
                ),

                new ClassRequest(
                        "IT-A",
                        teachers[6],
                        25
                ),

                new ClassRequest(
                        "IT-B",
                        teachers[7],
                        58
                ),

                new ClassRequest(
                        "EC-A",
                        teachers[8],
                        35
                ),

                new ClassRequest(
                        "EC-B",
                        teachers[9],
                        46
                )
        };

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
                ),

                new TimeSlot(
                        "Wednesday",
                        "09:00 AM",
                        "10:00 AM"
                ),

                new TimeSlot(
                        "Wednesday",
                        "10:00 AM",
                        "11:00 AM"
                ),

                new TimeSlot(
                        "Wednesday",
                        "11:00 AM",
                        "12:00 PM"
                )
        };

        TimetableGenerator generator =
                new TimetableGenerator(
                        classrooms,
                        timeSlots
                );

        generator.generateSchedule(requests);

        generator.displayGeneratedTimetable();
    }
}