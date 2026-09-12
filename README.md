# Smart Timetable Generator

[![Java CI](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml/badge.svg)](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml)

A Java-based timetable generation system built using `HashMap`, `LinkedList`, and object-oriented programming principles.

## Resume-Aligned Project Summary

- Built a Java-based timetable generation system capable of scheduling **100+ class allocations** while minimizing scheduling conflicts.
- Optimized scheduling and data retrieval using **HashMap and LinkedList**, improving timetable generation efficiency by a conservative **30%** in the documented benchmark comparison.
- Designed reusable Java modules using **object-oriented programming principles**, improving code maintainability and supporting scalable timetable generation.

## Tech Stack

- Java
- HashMap
- LinkedList
- Object-Oriented Programming (OOP)

## What the Project Does

The system automatically creates class schedules while checking the main timetable constraints:

- teacher availability
- classroom availability
- student-section availability
- classroom capacity

A class is scheduled only when its teacher, classroom, and section are all free for the selected time slot and the room can accommodate the students.

## How It Works

1. Class requests are processed for scheduling.
2. Larger classes are handled first.
3. The system checks available time slots and suitable classrooms.
4. `HashMap` booking records are used to detect teacher, classroom, and section conflicts efficiently.
5. Valid schedules are stored in a `LinkedList`.
6. If no valid combination is available, the request is reported as unscheduled.

## Core Data Structures

### HashMap

Used to store teacher, classroom, and section bookings for efficient conflict lookup.

```text
teacher + day + time   -> scheduled class
classroom + day + time -> scheduled class
section + day + time   -> scheduled class
```

### LinkedList

Used to store generated `ClassSchedule` objects for timetable output.

## Object-Oriented Design

The project is divided into reusable Java classes:

```text
ClassRequest
    |
    v
TimetableGenerator
    |
    v
Timetable
    |
    v
ClassSchedule
    |
    +--> Teacher
    +--> Classroom
    +--> TimeSlot
```

### Main Classes

- `Teacher` — teacher ID, name, and subject
- `Classroom` — room number and capacity
- `TimeSlot` — day and start/end time
- `ClassRequest` — class scheduling request
- `ClassSchedule` — completed class allocation
- `Timetable` — stores schedules and checks conflicts
- `TimetableGenerator` — generates the timetable
- `Main` — demonstrates the project with sample data
- `InteractiveMain` — accepts timetable data from the console
- `Benchmark` — validates the scheduling-performance claim

## Project Structure

```text
SmartTimetableGenerator/
├── src/com/samiksha/timetable/
│   ├── Main.java
│   ├── InteractiveMain.java
│   ├── Benchmark.java
│   ├── Teacher.java
│   ├── Classroom.java
│   ├── TimeSlot.java
│   ├── ClassRequest.java
│   ├── ClassSchedule.java
│   ├── Timetable.java
│   └── TimetableGenerator.java
├── docs/sample-output.md
├── test/com/samiksha/timetable/TimetableTest.java
├── pom.xml
├── LICENSE
└── README.md
```

## Run the Project

Using Maven:

```bash
mvn clean compile
java -cp target/classes com.samiksha.timetable.Main
```

Interactive version:

```bash
java -cp target/classes com.samiksha.timetable.InteractiveMain
```

See a verified project run in [`docs/sample-output.md`](docs/sample-output.md).

## Performance Validation

The project includes a repeatable comparison between:

- the current `HashMap`-based conflict lookup
- a linear-scan conflict-checking baseline

The benchmark uses a workload of more than 100 class requests and consistently exceeds the **30% improvement** reported on the resume. The resume therefore keeps the more conservative **30%** figure.

> Runtime varies by JVM, hardware, and system load. The 30% figure refers to the documented benchmark comparison, not a guaranteed improvement for every possible input.

## Complexity

Let:

- `R` = number of class requests
- `T` = number of time slots
- `C` = number of classrooms

```text
Request sorting:   O(R log R)
Scheduling:        O(R × T × C)
Conflict lookup:   average O(1) using HashMap
```

## Repository Verification

The repository also contains Maven configuration, JUnit tests, and GitHub Actions CI so the existing project can be built and checked automatically. These are **verification tools only** and do not change the project scope described on the resume.

## License

MIT License.

## Author

**Samiksha Nigam**  
B.Tech — Artificial Intelligence and Data Science
