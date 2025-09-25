
---

# Template Method Design Pattern

## 🔹 What is it?

The **Template Method Pattern** defines the **skeleton of an algorithm in a base class**, but lets subclasses **override specific steps** without changing the overall algorithm’s structure.

It’s about **code reuse with controlled customization**.

---

## 🔹 Why use it?

* To enforce a fixed sequence of steps in an algorithm.
* To allow subclasses to redefine certain steps without rewriting the algorithm.
* To reduce code duplication (common parts in base class, variable parts in subclasses).

---

## 🔹 How it works?

1. Create an **abstract class** with a **template method** (final) that defines the algorithm steps.
2. Implement common steps in the abstract class.
3. Mark specific steps as **abstract methods** → subclasses must implement them.
4. Subclasses provide variations of these steps.

---

## ✅ Example (Java)

```java
// Abstract Class (Template)
abstract class DataProcessor {
    // Template Method (final to prevent override)
    public final void process() {
        readData();
        processData();
        saveData();
    }

    protected abstract void readData();     // step to customize
    protected abstract void processData();  // step to customize

    protected void saveData() {             // common step
        System.out.println("Saving processed data...");
    }
}

// Concrete Class 1
class CSVDataProcessor extends DataProcessor {
    protected void readData() {
        System.out.println("Reading data from CSV file");
    }
    protected void processData() {
        System.out.println("Processing CSV data");
    }
}

// Concrete Class 2
class DatabaseDataProcessor extends DataProcessor {
    protected void readData() {
        System.out.println("Reading data from Database");
    }
    protected void processData() {
        System.out.println("Processing Database data");
    }
}

// Client
public class TemplateDemo {
    public static void main(String[] args) {
        DataProcessor csv = new CSVDataProcessor();
        csv.process();

        System.out.println("----");

        DataProcessor db = new DatabaseDataProcessor();
        db.process();
    }
}
```

### Output:

```
Reading data from CSV file
Processing CSV data
Saving processed data...
----
Reading data from Database
Processing Database data
Saving processed data...
```

---

## 🔹 Pros

* ✅ Promotes **code reuse** (common logic in base class).
* ✅ Easy to define the overall workflow and vary parts independently.
* ✅ Enforces algorithm consistency while allowing customization.

---

## 🔹 Cons

* ❌ Inflexible if algorithm steps change often → base class needs modifications.
* ❌ Can lead to too much inheritance (harder to maintain if hierarchy grows deep).

---

## 🔹 Design Principle

* ✔️ Follows **Hollywood Principle**: *“Don’t call us, we’ll call you.”* → Base class calls subclass methods at the right time.
* ✔️ Uses **inversion of control** → algorithm structure in base class, implementation in subclasses.

---

## 🔹 Analogy (for fresher)

Think of a **cooking recipe 🍳**:

* Recipe defines the **steps**: prepare ingredients → cook → serve.
* Different chefs may implement steps differently (Italian, Indian, Chinese).
* Recipe skeleton stays the same; only specific steps vary.

---

✅ Real-world examples:

* **Spring Framework**: `JdbcTemplate` → defines algorithm (open connection, execute query, close connection), subclasses provide SQL logic.
* **Servlets (Java EE)**: `doGet()`, `doPost()` → algorithm defined in `HttpServlet`, dev overrides request handling steps.
* **JUnit**: test execution skeleton is fixed, your test logic fills in the blanks.

---
