# Assignment 2 - Student B (SelectionSort, HeapSort, Kadane, MaxHeap)

This archive contains a ready-to-build Java Maven project skeleton implementing
Student B roles for Assignment 2 along with tests, a simple PerformanceTracker,
a CLI benchmark runner, and a report template.

## Structure
```
assignment2_studentB/
├── pom.xml
├── README.md
├── src/
│   ├── main/java/algorithms/SelectionSort.java
│   ├── main/java/algorithms/HeapSort.java
│   ├── main/java/algorithms/Kadane.java
│   ├── main/java/algorithms/MaxHeap.java
│   ├── main/java/metrics/PerformanceTracker.java
│   └── main/java/cli/BenchmarkRunner.java
├── src/test/java/algorithms/SelectionSortTest.java
├── docs/analysis-report.md
└── .gitignore
```

## How to build
Requirements: JDK 11+ and Maven.

Build and run tests:
```
mvn -q -DskipTests=false test package
```

Run benchmark example:
```
java -cp target/assignment2-studentB-1.0-SNAPSHOT.jar cli.BenchmarkRunner selection 1000 random 3 results-selection.csv
```

## Notes
- The PerformanceTracker uses simple CSV export (append). For accurate microbenchmarks consider JMH.
- Edit `docs/analysis-report.md` to fill empirical results and conclusions after running benchmarks.
