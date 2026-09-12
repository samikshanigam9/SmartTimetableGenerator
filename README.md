# Smart Timetable Generator

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
- Benchmark scenario with 120 class requests

## Tech Stack

- Java
- Object-Oriented Programming
- HashMap
- LinkedList
- Arrays
- Greedy Algorithm
- IntelliJ IDEA

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

## Main Classes

- `Teacher` — stores teacher ID, name, and subject.
- `Classroom` — stores room number and seating capacity.
- `TimeSlot` — represents a day and start/end time.
- `ClassRequest` — represents a section, teacher, and student count to schedule.
- `ClassSchedule` — stores one completed timetable allocation.
- `Timetable` — stores schedules and performs conflict checks.
- `TimetableGenerator` — applies the greedy scheduling strategy.
- `InteractiveMain` — accepts user input from the console.
- `Benchmark` — generates a larger test scenario.

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

Run the benchmark:

```bash
java -cp out com.samiksha.timetable.Benchmark
```

## Benchmark Scenario

`Benchmark.java` creates:

- 120 class requests
- 5 working days
- 8 time slots per day
- 4 classrooms with capacities from 40 to 80

The benchmark measures the schedule-generation time using `System.nanoTime()` and reports the number of successfully scheduled and failed requests.

> Runtime can vary across machines and JVM runs, so this repository does not claim a fixed performance improvement percentage.

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
- Benchmarking with `System.nanoTime()`

## Future Improvements

- CSV import/export
- Persistent database storage
- Faculty preference handling
- Laboratory scheduling
- Break-time constraints
- GUI or web dashboard
- Automated unit tests
- Alternative optimization approaches for larger scheduling problems

## Author

**Samiksha Nigam**  
B.Tech — Artificial Intelligence and Data Science
