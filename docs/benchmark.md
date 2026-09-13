# Comparative Benchmark

## Reproduce

Use JDK 11+ and Maven 3.8+. From the repository root:

```bash
mvn clean compile
java -cp target/classes com.samiksha.timetable.Benchmark
```

The Java 21 CI job also runs this command after the tests. Its log contains the measured times and scheduled counts for that run; timing is not a pass/fail threshold.

## Method

| Setting | Value |
| --- | --- |
| Requests per schedule | 240 |
| Rooms | 12, each capacity 80 |
| Time slots | 20: four per day, Monday–Friday |
| Students per request | 30–69 |
| Teacher and section IDs | Unique per request |
| JVM warm-up | 200 runs per implementation |
| Measurement | 1,000 runs per implementation |
| Ordering | Alternating baseline-first and optimized-first |
| Timer | `System.nanoTime()` |

Both implementations sort requests by descending size and rooms by ascending capacity. Both store schedules in a `LinkedList`. The current generator uses three HashMap booking indexes; the baseline scans previously scheduled classes. Each timed run constructs a fresh schedule and includes sorting, allocation, and conflict checking. Console reporting is outside the measured interval for this fully schedulable fixture.

Both implementations must schedule all 240 requests before timing proceeds; a mismatch fails the benchmark. Matching counts alone do not prove full schedule equivalence. Separate JUnit tests verify core scheduling constraints.

Runtime reduction (%) = `(baseline average − optimized average) / baseline average × 100`.

## Previously recorded results

The September 12, 2026 [README revision](https://github.com/samikshanigam9/SmartTimetableGenerator/blob/dc2744625b46c670093ef801710f7e328f6fa4c9/README.md) recorded these three Java 21 runs:

| Run | Reported runtime reduction |
| --- | ---: |
| 1 | 56.85% |
| 2 | 56.61% |
| 3 | 56.18% |

That revision reported 240 scheduled requests for both implementations. These are historical reported results, not new measurements from this documentation update. The original record did not preserve absolute times, CPU/OS details, or raw logs, so it is incomplete performance evidence.

For a resume or interview, describe the measured workload and baseline explicitly. A fixed 30% improvement should not be presented as universal; use a fresh recorded run on your own machine to support any percentage you quote.

## Interpretation and limits

- The workload mainly exercises room contention. Unique teachers/sections and equal room capacities do not represent every real timetable.
- The comparison supports HashMap indexing versus linear scanning, not a separately measured LinkedList improvement.
- JVM compilation, garbage collection, CPU, system load, and run order can affect results. This is a simple educational benchmark, not a JMH study.
- There is no timing threshold in CI: correctness is checked, but a speedup is not guaranteed on every runner or input.
- The ten-class demo in [sample-output.md](sample-output.md) is a separate fixture, not the 240-request stress benchmark.

When recording a new result, retain the commit SHA, full `java -version`, OS/CPU, complete benchmark output, and several independent process runs. Report baseline and optimized milliseconds along with the percentage.
