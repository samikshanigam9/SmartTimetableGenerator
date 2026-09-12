# Smart Timetable Generator

[![Java CI](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml/badge.svg)](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml)

A Java-based scheduling system that assigns classes to teachers, student sections, classrooms, and time slots while preventing timetable conflicts and respecting classroom capacity.

## Why this project

Manual timetable creation becomes difficult when multiple constraints must be satisfied at the same time. This project models those constraints in Java and generates a valid schedule using a greedy allocation strategy supported by fast conflict checks.

## Key Features

- Automatic timetable generation
- Teacher conflict detection
- Classroom conflict detection
- Student-section conflict detection
- Classroom-capacity validation
- Greedy scheduling of larger classes first
- Smallest-suitable-room allocation
- Interactive console input
- Table-formatted timetable output
- Comparative benchmark with 120 class requests
- Automated unit tests and GitHub Actions CI

## Tech Stack

- Java 11+
- Object-Oriented Programming
- HashMap
- LinkedList
- Arrays
- Greedy Algorithm
- Maven
- JUnit 5
- GitHub Actions
- IntelliJ IDEA

## Requirements

- JDK 11 or later
- Maven 3.8+ for the Maven build and tests

The comparative benchmark results documented below were validated in a Java 21 environment.

## How the Scheduling Works

1. Class requests are copied and sorted by student strength in descending order.
2. Classrooms are sorted by capacity in ascending order.
3. For each class request, the generator checks available time slots.
4. It chooses the smallest classroom that can hold the class.
5. Before adding the schedule, it checks whether the teacher, classroom, or section is already booked for that slot.
6. Valid schedules are stored and the corresponding booking maps are updated.
7. If no valid room/time-slot combination exists, the request is reported as unscheduled.

## Conflict Detection

The project uses three `HashMap` structures to track bookings:

- teacher + day + time
- classroom + day + time
- section + day + time

A new schedule is rejected if any of those keys already exists.

## Data Structures Used

### HashMap
Used for average `O(1)` lookup of teacher, classroom, and section bookings.

### LinkedList
Stores the generated `ClassSchedule` objects in insertion order for timetable display.

### Arrays
Used for teachers, classrooms, time slots, and class requests.

## Project Structure

```text
SmartTimetableGenerator/
├── .github/workflows/ci.yml
├── LICENSE
├── pom.xml
├── README.md
├── .gitignore
├── src/
│   └── com/samiksha/timetable/
│       ├── Main.java
│       ├── InteractiveMain.java
│       ├── Benchmark.java
│       ├── Teacher.java
│       ├── Classroom.java
│       ├── TimeSlot.java
│       ├── ClassRequest.java
│       ├── ClassSchedule.java
│       ├── Timetable.java
│       └── TimetableGenerator.java
└── test/
    └── com/samiksha/timetable/
        └── TimetableTest.java
```

## Main Classes

- `Teacher` — stores teacher ID, name, and subject.
- `Classroom` — stores room number and seating capacity.
- `TimeSlot` — represents a day and start/end time.
- `ClassRequest` — represents a section, teacher, and student count to schedule.
- `ClassSchedule` — stores one completed timetable allocation.
- `Timetable` — stores schedules and performs conflict checks.
- `TimetableGenerator` — applies the greedy scheduling strategy.
- `InteractiveMain` — accepts user input from the console.
- `Benchmark` — compares HashMap-based conflict checks with a linear-scan baseline.

## Build and Test

Run the automated tests:

```bash
mvn clean test
```

The test suite checks successful scheduling plus teacher, classroom, and student-section conflict rejection.

GitHub Actions runs the Maven test suite on Java 11 and Java 21 for pushes and pull requests targeting `main`.

## How to Run

Compile from the project root:

```bash
javac -d out src/com/samiksha/timetable/*.java
```

Run the demo:

```bash
java -cp out com.samiksha.timetable.Main
```

Run the interactive version:

```bash
java -cp out com.samiksha.timetable.InteractiveMain
```

Run the comparative benchmark:

```bash
java -cp out com.samiksha.timetable.Benchmark
```

## Comparative Benchmark

`Benchmark.java` evaluates two implementations on the same high-contention scheduling workload:

1. **Optimized version** — uses `HashMap` booking indexes for average `O(1)` conflict checks.
2. **Baseline version** — scans previously created schedules linearly to detect conflicts.

### Benchmark configuration

- 120 class requests
- 12 classrooms
- 10 weekly time slots
- Every classroom can accommodate every generated class request
- 200 JVM warm-up runs
- 1,000 measured runs
- Execution order alternates between implementations to reduce ordering bias

Both implementations schedule the same 120 requests. Only the conflict-detection strategy changes.

### Validation result

Three full validation runs in a Java 21 environment measured approximately:

- **46.13% lower average runtime**
- **47.20% lower average runtime**
- **46.45% lower average runtime**

This supports a conservative claim of **30%+ runtime improvement under the documented high-contention benchmark** compared with the linear-scan baseline.

> Microbenchmark results vary by JVM, hardware, and system load. The percentage above applies specifically to the documented comparative benchmark and should not be interpreted as a universal speedup for every timetable workload.

## Time Complexity

Let:

- `R` = number of class requests
- `T` = number of time slots
- `C` = number of classrooms

Sorting requests costs:

```text
O(R log R)
```

Sorting classrooms costs:

```text
O(C log C)
```

In the worst case, each request may inspect every time slot and classroom:

```text
O(R × T × C)
```

Overall:

```text
O(R log R + C log C + R × T × C)
```

Conflict checks use `HashMap.containsKey()`, which is average `O(1)`.

## Concepts Demonstrated

- Object-Oriented Programming
- Encapsulation
- Constructor overloading
- Collections and arrays
- Hash-based lookup
- Greedy allocation
- Lambda expressions
- Conflict detection
- Complexity analysis
- Unit testing with JUnit 5
- Continuous integration with GitHub Actions
- Comparative benchmarking with `System.nanoTime()`

## Future Improvements

- CSV import/export
- Persistent database storage
- Faculty preference handling
- Laboratory scheduling
- Break-time constraints
- GUI or web dashboard
- Alternative optimization approaches for larger scheduling problems

## License

This project is licensed under the MIT License. See `LICENSE` for details.

## Author

**Samiksha Nigam**  
B.Tech — Artificial Intelligence and Data Science
