# Smart Timetable Generator

A Java-based timetable generation system that automatically assigns teachers, subjects, student sections, classrooms, and time slots while preventing scheduling conflicts.

## Features

- Automatic timetable generation
- Teacher conflict detection
- Classroom conflict detection
- Student-section conflict detection
- Classroom-capacity validation
- Greedy room-allocation optimization
- Largest classes scheduled first
- Interactive console input
- Professional table-style timetable output
- Benchmark testing with 120 class requests

## Technologies Used

- Java
- Object-Oriented Programming
- HashMap
- LinkedList
- Arrays
- Greedy Algorithm
- IntelliJ IDEA

## Scheduling Logic

The generator follows these steps:

1. Sort class requests by student strength in descending order.
2. Select an available time slot.
3. Find the smallest classroom with sufficient capacity.
4. Check teacher, classroom, and section availability.
5. Add the class if no conflict exists.
6. Try the next room or time slot if a conflict is found.

## Conflict Rules

A schedule is rejected when:

- The teacher is already assigned at the same time.
- The classroom is already occupied at the same time.
- The student section already has another class at the same time.
- The classroom capacity is smaller than the number of students.

## Benchmark

The project was tested using:

- 120 class-allocation requests
- Five working days
- Eight time slots per day
- Four classrooms

### Benchmark Result

- Requests received: 120
- Successfully scheduled: 120
- Failed requests: 0
- Benchmark status: Passed

## Project Structure

```text
SmartTimetableGenerator/
├── README.md
├── .gitignore
└── src/
    └── com/
        └── samiksha/
            └── timetable/
                ├── Main.java
                ├── InteractiveMain.java
                ├── Benchmark.java
                ├── Teacher.java
                ├── Classroom.java
                ├── TimeSlot.java
                ├── ClassRequest.java
                ├── ClassSchedule.java
                ├── Timetable.java
                └── TimetableGenerator.java
```

## How to Run

### Demo Version

Run:

```text
Main.java
```

This runs the project using predefined teachers, sections, classrooms, and time slots.

### Interactive Version

Run:

```text
InteractiveMain.java
```

Enter the following information:

- Teacher name
- Subject
- Student section
- Number of students

The system will automatically generate and display the timetable.

### Benchmark Version

Run:

```text
Benchmark.java
```

This tests the timetable generator using 120 class-allocation requests.

## Algorithm Complexity

Let:

- `R` represent the number of class requests.
- `T` represent the number of available time slots.
- `C` represent the number of classrooms.

The approximate time complexity is:

```text
O(R log R + R × T × C)
```

Sorting the requests requires `O(R log R)` time.

For every request, the generator may check multiple time slots and classrooms, requiring approximately `O(R × T × C)` time.

HashMap provides average `O(1)` conflict checking.

## Optimization Strategy

The system uses a greedy optimization strategy:

1. Class requests are sorted by student strength in descending order.
2. Larger classes are scheduled before smaller classes.
3. The smallest suitable classroom is selected whenever possible.
4. This reduces classroom-capacity wastage and prevents larger classes from losing access to suitable rooms.

## Concepts Used

- Classes and objects
- Constructors
- Encapsulation
- Method overloading
- Arrays
- LinkedList
- HashMap
- Enhanced for loops
- Greedy sorting
- Lambda expressions
- Conflict detection
- Time-complexity analysis

## Future Improvements

- Graphical user interface
- Database integration
- CSV import and export
- Genetic Algorithm optimization
- Faculty preference handling
- Break-time handling
- Laboratory scheduling
- Timetable export to PDF
- Web-based timetable dashboard

## Author

Samiksha Nigam  
B.Tech - Artificial Intelligence and Data Science