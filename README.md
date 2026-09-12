# Smart Timetable Generator

[![Java CI](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml/badge.svg)](https://github.com/samikshanigam9/SmartTimetableGenerator/actions/workflows/ci.yml)

A Java timetable scheduling system that assigns classes to teachers, student sections, classrooms, and time slots while preventing collisions and respecting classroom capacity.

## Highlights

- Greedy scheduling with larger classes handled first
- Teacher, classroom, and section conflict detection
- Average `O(1)` conflict checks using `HashMap`
- Smallest-suitable-room allocation
- Capacity validation and unscheduled-request reporting
- Interactive console mode
- Maven build and JUnit 5 test suite
- GitHub Actions CI
- Comparative benchmark against a linear-scan baseline

## Problem

A valid timetable must avoid assigning one teacher, classroom, or student section to two classes at the same time while also ensuring the selected room has enough capacity. This project models those constraints and generates a valid schedule using a greedy allocation strategy.

## Scheduling Strategy

1. Sort class requests by student count in descending order.
2. Sort classrooms by capacity in ascending order.
3. For each request, inspect available time slots.
4. Choose the smallest classroom that can hold the class.
5. Check teacher, classroom, and section availability.
6. Add the schedule and update booking indexes when all constraints pass.
7. Report the request if no valid slot/room combination exists.

## Conflict Detection

Three `HashMap` booking indexes are maintained:

```text
teacherId + day + time   -> scheduled class
roomNumber + day + time -> scheduled class
section + day + time    -> scheduled class
```

This gives average `O(1)` conflict lookup instead of scanning every previously scheduled class.

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
├── .github/workflows/ci.yml
├── docs/sample-output.md
├── src/com/samiksha/timetable/
├── test/com/samiksha/timetable/TimetableTest.java
├── LICENSE
├── pom.xml
└── README.md
```

## Requirements

- JDK 11+
- Maven 3.8+

The benchmark results below were validated in a Java 21 environment.

## Build and Test

Run the automated tests:

```bash
mvn clean test
```

Build the project:

```bash
mvn package
```

GitHub Actions runs the Maven test suite automatically for pushes and pull requests targeting `main`.

## Run

### Demo

```bash
java -cp target/classes com.samiksha.timetable.Main
```

### Interactive mode

```bash
java -cp target/classes com.samiksha.timetable.InteractiveMain
```

### Benchmark

```bash
java -cp target/classes com.samiksha.timetable.Benchmark
```

See a verified demo run in [`docs/sample-output.md`](docs/sample-output.md).

## Automated Tests

The JUnit suite verifies:

- teacher collision rejection
- classroom collision rejection
- section collision rejection
- valid reuse of the same teacher at a different time
- smallest-suitable-room selection
- failure when no room has enough capacity

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
- identical workload for both implementations

Three reproduced Java 21 runs measured:

```text
56.85% improvement
56.61% improvement
56.18% improvement
```

Both implementations scheduled all 240 requests in the benchmark workload.

This supports a conservative resume claim of **30% improvement under the documented stress-test benchmark** compared with the linear-scan baseline.

> Runtime varies by JVM, hardware, and system load. This benchmark is a workload-specific comparison, not a universal speedup guarantee.

## Complexity

Let `R` = requests, `T` = time slots, and `C` = classrooms.

```text
Request sorting:       O(R log R)
Classroom sorting:     O(C log C)
Greedy allocation:     O(R × T × C)
Conflict lookup:       average O(1)
```

Overall:

```text
O(R log R + C log C + R × T × C)
```

## Concepts Demonstrated

`Java` · `OOP` · `HashMap` · `LinkedList` · `Arrays` · `Greedy Algorithms` · `Conflict Detection` · `JUnit` · `Maven` · `GitHub Actions` · `Benchmarking`

## Future Improvements

- CSV import/export
- database persistence
- faculty preferences and blocked slots
- lab-specific scheduling rules
- configurable breaks
- GUI or REST API layer
- advanced optimization approaches for larger scheduling instances

## License

This project is licensed under the MIT License. See `LICENSE` for details.

## Author

**Samiksha Nigam**  
B.Tech — Artificial Intelligence and Data Science
