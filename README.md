# Smart Timetable Generator

![Java CI](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Maven](https://img.shields.io/badge/Build-Maven-blue)

A Java timetable scheduling system that assigns classes to teachers, student sections, classrooms, and time slots while preventing collisions and respecting classroom capacity.

## Highlights

- Greedy scheduling with larger classes handled first
- Teacher, classroom, and section conflict detection
- Average `O(1)` conflict checks using `HashMap`
- Smallest-suitable-room allocation
- Capacity validation and unscheduled-request reporting
- Interactive console mode
- Maven build and JUnit 5 test suite
- GitHub Actions CI on pushes and pull requests
- Comparative benchmark against a linear-scan baseline

## Problem

Timetable generation is a constrained allocation problem. A valid schedule must avoid assigning:

- one teacher to two classes at the same time,
- one classroom to two classes at the same time,
- one student section to two classes at the same time,
- a class to a room that is too small.

The project models these constraints and generates a valid schedule using a greedy strategy.

## Scheduling Strategy

1. Copy class requests and sort them by student count in descending order.
2. Sort classrooms by capacity in ascending order.
3. For each request, inspect available time slots.
4. Pick the smallest classroom that can hold the class.
5. Check teacher, classroom, and section availability.
6. Add the schedule and update booking indexes when all constraints pass.
7. Report the request if no valid slot/room combination exists.

## Why `HashMap`

Three booking indexes are maintained:

```text
teacherId + day + time   -> scheduled class
roomNumber + day + time -> scheduled class
section + day + time    -> scheduled class
```

This allows average `O(1)` conflict lookups instead of scanning every previously scheduled class.

## Architecture

```text
ClassRequest
    |
    v
TimetableGenerator
    |  sorts requests and classrooms
    |  applies greedy allocation
    v
Timetable
    |  validates conflicts with HashMap indexes
    v
ClassSchedule
    |
    +--> Teacher
    +--> Classroom
    +--> TimeSlot
```

### Main classes

- `Teacher` — teacher ID, name, and subject
- `Classroom` — room number and capacity
- `TimeSlot` — day and start/end time
- `ClassRequest` — section, teacher, and student count to schedule
- `ClassSchedule` — one successful timetable allocation
- `Timetable` — stores schedules and enforces conflict rules
- `TimetableGenerator` — greedy scheduling engine
- `InteractiveMain` — console-driven input mode
- `Benchmark` — optimized-vs-linear-scan performance comparison

## Project Structure

```text
SmartTimetableGenerator/
├── .github/
│   └── workflows/
│       └── ci.yml
├── docs/
│   └── sample-output.md
├── src/
│   └── com/samiksha/timetable/
│       ├── Benchmark.java
│       ├── ClassRequest.java
│       ├── ClassSchedule.java
│       ├── Classroom.java
│       ├── InteractiveMain.java
│       ├── Main.java
│       ├── Teacher.java
│       ├── TimeSlot.java
│       ├── Timetable.java
│       └── TimetableGenerator.java
├── test/
│   └── com/samiksha/timetable/
│       └── TimetableTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Build and Run

### Requirements

- Java 17+
- Maven 3.9+

### Run tests

```bash
mvn test
```

### Compile

```bash
mvn package
```

### Run the demo

```bash
java -cp target/classes com.samiksha.timetable.Main
```

### Run the interactive version

```bash
java -cp target/classes com.samiksha.timetable.InteractiveMain
```

### Run the benchmark

```bash
java -cp target/classes com.samiksha.timetable.Benchmark
```

A verified demo run is available in [`docs/sample-output.md`](docs/sample-output.md).

## Automated Tests

The JUnit suite covers the core scheduling rules:

- teacher collision rejection
- classroom collision rejection
- section collision rejection
- reuse of the same teacher at a different time
- smallest-suitable-room selection
- failure when no classroom has enough capacity

GitHub Actions runs the test suite automatically for pushes and pull requests targeting `main`.

## Comparative Benchmark

The benchmark compares:

1. **Optimized implementation** — `HashMap` booking indexes for average `O(1)` conflict lookup.
2. **Baseline implementation** — linear scan over previously scheduled classes.

### Stress-test configuration

- 240 class requests
- 12 classrooms
- 20 weekly time slots
- 200 JVM warm-up runs
- 1,000 measured runs
- alternating execution order to reduce ordering bias
- identical scheduling workload for both implementations

On a Java 21 validation environment, three runs measured approximately:

```text
56.85% improvement
56.61% improvement
56.18% improvement
```

Both implementations successfully scheduled all 240 requests in the test workload.

This supports a conservative resume claim of **30% improvement under the documented stress-test benchmark** compared with the linear-scan baseline.

> Runtime results vary by JVM, hardware, and system load. The benchmark is a comparative test for the documented workload, not a universal speedup guarantee.

## Complexity

Let:

- `R` = class requests
- `T` = time slots
- `C` = classrooms

Request sorting:

```text
O(R log R)
```

Classroom sorting:

```text
O(C log C)
```

Greedy allocation worst case:

```text
O(R × T × C)
```

Overall:

```text
O(R log R + C log C + R × T × C)
```

Conflict checks use `HashMap.containsKey()`, which is average `O(1)`.

## Concepts Demonstrated

`Java` · `OOP` · `HashMap` · `LinkedList` · `Arrays` · `Greedy Algorithms` · `Conflict Detection` · `JUnit` · `Maven` · `GitHub Actions` · `Benchmarking`

## Future Improvements

- CSV import/export
- database persistence
- faculty preferences and blocked slots
- lab-specific scheduling rules
- configurable breaks
- GUI or REST API layer
- advanced optimization approaches for large scheduling instances

## Author

**Samiksha Nigam**  
B.Tech — Artificial Intelligence and Data Science
