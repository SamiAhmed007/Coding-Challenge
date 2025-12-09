# Intuit Build Challenge – Java

This project has two independent Java problems:

1. **Problem 1 – Producer–Consumer** (`src/pc`)
2. **Problem 2 – Sales Analytics (CSV + Streams)** (`src/sales`)

Requires **Java 11+**.

---

## Folder Structure

```text
src/
  pc/
    BoundedBlockingQueue.java
    Producer.java
    Consumer.java
    ProducerConsumerDemo.java   # main() for Problem 1

  sales/
    SalesRecord.java
    SalesCsvReader.java
    SalesAnalytics.java
    SalesAnalyticsApp.java      # main() for Problem 2
    SalesAnalyticsTest.java     # JUnit tests (optional)
    sales.csv                   # sample data
```

---

## Problem 1 – Producer–Consumer

- Implements a **bounded blocking queue** using `synchronized`, `wait()`, `notifyAll()`.
- `Producer` pushes items into the queue; `Consumer` pulls them out.
- Uses a **poison pill** sentinel to signal completion.

**Run (from project root):**

```bash
cd src/pc
javac *.java
java ProducerConsumerDemo
```

Expected Output:

```text
Source data     : [1, 2, 3, 4, 5]
Destination data: [1, 2, 3, 4, 5]
```

---

## Problem 2 – Sales Analytics

- Reads `sales.csv` and maps each row to `SalesRecord`.
- `SalesAnalytics` uses Java Streams to compute:
  - Total revenue
  - Revenue by region / product
  - Top N products by revenue
  - Average order value
  - Daily revenue
  - Units sold by product

**Run (from project root):**

```bash
cd src/sales
javac *.java
java sales.SalesAnalyticsApp ./sales.csv
```

---

## Tests (Optional)

If using VS Code + Java Test Runner:

- Open `SalesAnalyticsTest.java`
- Click **Run Test** to execute all analytics unit tests.
