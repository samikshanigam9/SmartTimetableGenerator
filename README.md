# Smart Timetable Generator

[![Java CI](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml/badge.svg)](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml)

A Java-based timetable generation system built using `HashMap`, `LinkedList`, and object-oriented programming principles.

## Project Highlights

- Built a Java-based timetable generation system capable of scheduling **100+ class allocations** while minimizing scheduling conflicts.
- Uses **HashMap** booking indexes for average constant-time conflict lookup and **LinkedList** to store schedules. Includes a reproducible comparison against linear-scan conflict checks.
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

## Requirements

- JDK 11 or newer
- Maven 3.8 or newer

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

In interactive mode, enter the **same teacher ID** for every class taught by the same person, even across different subjects. Give different teachers distinct IDs, including teachers who share a name. Each class retains its own subject.

See a verified project run in [`docs/sample-output.md`](docs/sample-output.md).

## Performance Validation

The project includes a repeatable comparison between:

- the current `HashMap`-based conflict lookup
- a linear-scan conflict-checking baseline

The stress workload contains **240 class requests, 12 rooms, and 20 weekly slots**. It uses 200 warm-up runs and 1,000 measured runs per implementation, alternating execution order. Both implementations use the same greedy ordering and a `LinkedList`; the comparison measures the effect of HashMap booking indexes versus scanning prior schedules, including their construction overhead.

Run it after compiling:

```bash
java -cp target/classes com.samiksha.timetable.Benchmark
```

See [benchmark methodology and recorded results](docs/benchmark.md). Runtime reduction is workload- and environment-specific; no fixed 30% improvement is guaranteed. The stress fixture uses unique teachers/sections and equal-capacity rooms, so it mainly exercises room contention, not every real timetable constraint.

## Scheduling Assumptions

- Supply predefined, non-overlapping time slots with consistent day/time strings. Conflict keys compare exact slots; arbitrary overlapping intervals are not detected.
- Teacher availability means absence of another booking in the supplied slot; personal availability calendars are not modeled.
- Teacher IDs uniquely identify people, and section/room identifiers must be consistent.
- The greedy strategy tries earlier slots first and chooses the smallest available suitable room in that slot. It does not guarantee the maximum possible number of allocations.

## Complexity

Let:

- `R` = number of class requests
- `T` = number of time slots
- `C` = number of classrooms

```text
Request sorting:   O(R log R)
Classroom sorting: O(C log C)
Scheduling:        O(R × T × C)
Conflict lookup:   average O(1) using HashMap
```

Overall expected time: `O(R log R + C log C + R × T × C)`, assuming bounded identifier lengths and average constant-time HashMap operations. Auxiliary space: `O(R + C + T)` for copied inputs, sorting, schedules, and booking indexes.

## Build and Test

```bash
mvn clean test
mvn package
```

GitHub Actions runs the JUnit suite on Java 11 and 21 for pushes and pull requests targeting `main`. Tests cover teacher, room, and section conflicts; teacher reuse at another time; room-capacity selection and rejection; and teacher identity across interactive class entries.

## License

MIT License.

## Author

**Samiksha Nigam**  
B.Tech — Artificial Intelligence and Data Science
