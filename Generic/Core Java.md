
## ✅ OOPS Principle: Encapsulation

### 🔹 What is Encapsulation?

Encapsulation is one of the core principles of Object-Oriented Programming (OOP).  
It means **bundling data (fields) and behavior (methods)** together inside a single unit (usually a **class**) and **restricting direct access** to some of the object’s components.

Instead of exposing the fields directly, you expose **controlled access** through methods — typically getters, setters, or other business logic.

---

### 🔹 Why is Encapsulation Important?

- ✅ **Protects internal state:** Prevents external code from putting the object in an invalid state.
- ✅ **Enforces constraints:** Validation logic inside setters.
- ✅ **Hides implementation details:** Clients interact only with methods, not with internal fields.
- ✅ **Improves maintainability:** You can change internals without breaking external code.
- ✅ **Supports modular design:** The class works like a self-contained black box.

---

### 🔹 How is it Implemented?

- Make fields **`private`**.
- Provide **public methods** to read or update them (`getters` and `setters`).
- Skip setters for immutable fields when necessary.

---

### 🔹 Example: BankAccount

```java
public class BankAccount {
    private double balance; // private field

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException();
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException();
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) throw new IllegalArgumentException();
        balance -= amount;
    }
}
```

✅ Here:
- `balance` is **hidden** — no direct access.
- Access and modification are done only through **controlled methods** that enforce business rules.

---

### 🔹 Follow-up: Encapsulation vs Abstraction

- **Encapsulation** is about *how* you hide data and implementation inside a unit and control access.
- **Abstraction** is about *what* you expose — focusing on relevant behavior while hiding unnecessary details.

---


# ✅ OOPS Principle: Inheritance & Composition

---

## 🔹 What is Inheritance?

**Inheritance** lets a class (**child/subclass**) acquire properties and behavior of another class (**parent/superclass**) — reusing and customizing logic.

**Java keyword:** `extends`.

---

## 🔹 Why is Inheritance Important?

| Benefit              | Explanation                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| ✅ Code Reuse         | Write common logic once in parent, reuse in child.                          |
| ✅ Method Overriding  | Child can change/extend parent behavior.                                    |
| ✅ Polymorphism       | Parent reference can point to child instance, enabling flexible design.     |
| ✅ Hierarchy Modeling | Represents **is-a** relationships — `Dog` **is an** `Animal`.              |

---

## 🔹 Example: Inheritance

```java
class Vehicle {
    void start() {
        System.out.println("Vehicle starts");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car starts");
    }

    void openSunroof() {
        System.out.println("Sunroof opened");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start(); // Car starts

        // v.openSunroof(); // Compile-time error
    }
}
```

✅ **Key point:**  
- `Car` **is a** `Vehicle`.
- Parent reference can’t access child-only methods without downcasting.
- This demonstrates **runtime polymorphism**.

---

## 🔹 Limitations of Inheritance

| Limitation             | Why it’s an issue                                                          |
|------------------------|-----------------------------------------------------------------------------|
| ❌ Tight Coupling       | Subclass depends heavily on parent.                                         |
| ❌ Fragile Base Class   | Parent changes may break subclasses.                                        |
| ❌ Single Inheritance   | Java does not allow multiple inheritance with classes (avoids diamond problem). |

---

## 🔹 Best Practice

✅ Use **composition** for **has-a** relationships or when multiple inheritance is needed.

✅ Use **interfaces** for multiple inheritance of behavior.

✅ Use **abstract classes** for shared behavior that should not be instantiated directly.

---

## 🔹 Bonus: `super` keyword

- Access parent methods: `super.start()`.
- Call parent constructor: `super()`.

---

# 🔹 What is Composition?

**Composition** means one class **has a reference** to another class — instead of inheriting, it **uses** it.

This models **has-a** relationships.

✅ **Flexible**, **loose coupling**, easier to change.

---

## 🔹 Example: Composition

```java
class Engine {
    void run() {
        System.out.println("Engine running");
    }
}

class Car {
    private Engine engine; // Car has-a Engine

    public Car() {
        this.engine = new Engine();
    }

    void start() {
        engine.run();
        System.out.println("Car is moving");
    }
}

public class CompositionDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start(); // Engine running \n Car is moving
    }
}
```

✅ `Car` **has a** `Engine`.  
✅ More flexible than inheritance — you can swap `Engine` easily.

---

# ✅ Is-A vs Has-A Summary

| Aspect              | Inheritance (**Is-A**)                     | Composition (**Has-A**)                |
|---------------------|---------------------------------------------|----------------------------------------|
| Definition          | Child **is a** type of Parent.              | One class **has a** reference to another. |
| Example             | `Car` **is a** `Vehicle`.                  | `Car` **has a** `Engine`.              |
| Flexibility         | Less flexible, tight coupling.              | More flexible, loose coupling.         |
| Multiple Behavior   | Limited (no multiple inheritance).          | Supports multiple behaviors.           |
| Best for            | Strict hierarchies, common functionality.   | Reusable, replaceable parts.           |

✅ **Key Principle:**  
**Favor composition over inheritance** when the relationship is **not truly “is-a.”**


## ✅ OOPS Principle: Polymorphism

### 🔹 What is Polymorphism?

**Polymorphism** means **“many forms.”**  
It’s an OOP concept where the **same interface, method, or operation behaves differently based on the object that implements it**.

In Java, there are **two types**:\
1️⃣ **Compile-Time Polymorphism (Static):** Method Overloading  
2️⃣ **Runtime Polymorphism (Dynamic):** Method Overriding

---

### 🔹 Why is Polymorphism Important?

✅ **Flexibility:** One interface, multiple implementations.  
✅ **Extensibility:** Add new behavior without changing existing code.  
✅ **Code Reuse:** Common interface, specific implementations.  
✅ **Loose Coupling:** Enables programming to interfaces, not concrete classes.

---

### 🔹 1️⃣ Compile-Time Polymorphism — Method Overloading

**Same method name** but **different parameter list** in the same class.  
Which method is called is determined by the compiler at **compile time**.

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

✅ **Rules:**
- Must differ in **number/type/order** of parameters.
- Return type alone **does not distinguish** overloads.

---

### 🔹 2️⃣ Runtime Polymorphism — Method Overriding

**Same method signature** in parent and child class, with different implementations.  
Which version runs is decided **at runtime**, based on the object’s actual type.

```java
class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}

public class PolyDemo {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.makeSound(); // Woof!
    }
}
```

✅ **Key point:**  
- Parent reference holds child object.
- JVM resolves which method to run at runtime.

---

### 🔹 Pitfalls

❌ Overload resolution happens at compile-time → can be misleading if mixed with type casting.  
❌ Overriding must match signature exactly — use `@Override`.  
❌ `private`, `static`, and `final` methods **cannot be overridden**, but they can be **hidden** (method hiding).

---

### 🔹 Best Practices

✅ Use `@Override` to catch errors.  
✅ Prefer interfaces for flexibility.  
✅ Favor composition if overriding behavior isn’t clean.

---

### 🔹 Related: Polymorphism with Interfaces

```java
interface Shape {
    double area();
}

class Circle implements Shape {
    public double area() { return Math.PI * 2 * 2; }
}

class Square implements Shape {
    public double area() { return 4 * 4; }
}

public class PolyInterfaceDemo {
    public static void main(String[] args) {
        Shape s = new Circle();
        System.out.println(s.area()); // Circle area

        s = new Square();
        System.out.println(s.area()); // Square area
    }
}
```

✅ **One interface, multiple implementations** — classic polymorphism.

---

## ✅ Quick Recap

| Type                | Example               | When Resolved |
|---------------------|-----------------------|----------------|
| Method Overloading  | Same class, diff args | Compile time   |
| Method Overriding   | Subclass, same method | Runtime        |

---

## ✅ OOPS Principle: Abstraction

### 🔹 What is Abstraction?

**Abstraction** means **hiding complex implementation details and exposing only the essential features** relevant for the user.

In Java, abstraction is mainly achieved using **abstract classes** and **interfaces**.

Abstraction lets you focus on **what an object does**, not **how it does it**.

---

### 🔹 Why is Abstraction Important?

✅ **Simplifies complex systems:** Users interact with a simple interface, not the inner complexity.

✅ **Improves maintainability:** Implementation can change without affecting users.

✅ **Supports loose coupling:** Clients depend on abstractions, not concrete implementations.

✅ **Enables polymorphism:** Same interface, different implementations.

---

### 🔹 How is Abstraction Achieved?

1️⃣ **Abstract Class:**

- Can have **abstract methods** (no body) and **concrete methods** (with body).
- Cannot be instantiated directly.
- Used when there’s **shared code** plus **abstract contracts**.

```java
abstract class Shape {
    abstract double area(); // abstract method

    void display() {
        System.out.println("Calculating area...");
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}
```

---

2️⃣ **Interface:**

- Pure abstraction — defines only **method signatures** (Java 8+ allows default and static methods).
- A class can implement multiple interfaces → enables multiple inheritance of type.

```java
interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }
}
```

---

### 🔹 Real-World Example

- **Driving a car:** You use the steering wheel and pedals — you don’t need to know the engine’s internal combustion process.
- **Database drivers:** You call `Connection` and `Statement` APIs — JDBC hides the vendor-specific implementations.

---

### 🔹 Best Practices

✅ Prefer interfaces for type contracts — flexible and testable.

✅ Use abstract classes when you have **common behavior** to share.

✅ Program to **interfaces**, not implementations.

---

### 🔹 Abstraction vs Encapsulation

| Aspect | Abstraction | Encapsulation |
|--------|--------------|----------------|
| What | Hides complexity by exposing only relevant behavior | Binds data & methods, restricts direct access |
| How | Achieved via abstract classes/interfaces | Achieved via private fields + public methods |
| Focus | Focus on **what** an object does | Focus on **how** to protect internal data |

---


# ✅ OOPS Principles & SOLID Design

---

## 1️⃣ S — Single Responsibility Principle (SRP)

**SRP:** A class should have only **one reason to change**.

### 🔹 OOPS Support

✅ **Encapsulation** groups related data + behavior, hides details, keeps logic cohesive.

### ✅ Example

```java
class ReportGenerator {
    String generateReport() {
        // Generate report
    }
}

class ReportPrinter {
    void printReport(String report) {
        // Print report
    }
}
```

Separate responsibilities → clear SRP.

---

## 2️⃣ O — Open/Closed Principle (OCP)

**OCP:** Classes should be **open for extension, closed for modification**.

### 🔹 OOPS Support

✅ **Inheritance, Polymorphism:** Extend behavior via child classes.

✅ **Abstraction:** Use interfaces/abstract classes.

### ✅ Example

```java
interface Shape {
    double area();
}

class Rectangle implements Shape {
    public double area() { return width * height; }
}

class Circle implements Shape {
    public double area() { return Math.PI * radius * radius; }
}

class AreaCalculator {
    double calculateArea(Shape shape) { return shape.area(); }
}
```

Add new `Shape` without changing `AreaCalculator`.

---

## 3️⃣ L — Liskov Substitution Principle (LSP)

**LSP:** Subclasses must be substitutable for their base classes.

### 🔹 OOPS Support

✅ **Inheritance, Polymorphism:** Use proper **is-a** relationships.

✅ Keep subclass behavior compatible.

### ✅ Example

```java
interface Bird {}

interface Flyable {
    void fly();
}

class Sparrow implements Bird, Flyable {
    public void fly() { /* fly */ }
}

class Penguin implements Bird {
    // Does not fly — no LSP violation
}
```

---

## 4️⃣ I — Interface Segregation Principle (ISP)

**ISP:** No client should depend on methods they don’t use.

### 🔹 OOPS Support

✅ Use **abstraction & multiple small interfaces**.

### ✅ Example

```java
interface Printable {
    void print();
}

interface Scannable {
    void scan();
}

class SimplePrinter implements Printable {
    public void print() { /* ... */ }
}
```

---

## 5️⃣ D — Dependency Inversion Principle (DIP)

**DIP:** High-level modules & low-level modules depend on **abstractions**.

### 🔹 OOPS Support

✅ Use **abstraction + composition (dependency injection)**.

### ✅ Example

```java
interface Keyboard {}
interface Monitor {}

class WiredKeyboard implements Keyboard {}
class LEDMonitor implements Monitor {}

class Computer {
    private final Keyboard keyboard;
    private final Monitor monitor;

    public Computer(Keyboard keyboard, Monitor monitor) {
        this.keyboard = keyboard;
        this.monitor = monitor;
    }
}
```

High-level `Computer` depends on abstractions, not concrete classes.

---

## ✅ Summary: OOPS & SOLID

| SOLID Principle | Supported By                               |
|-----------------|--------------------------------------------|
| SRP             | Encapsulation                              |
| OCP             | Inheritance, Polymorphism, Abstraction     |
| LSP             | Inheritance, Polymorphism                  |
| ISP             | Abstraction, Small Interfaces              |
| DIP             | Abstraction, Composition                   |

✅ **Key takeaway:** SOLID principles use OOPS building blocks to make code **maintainable**, **extensible**, **testable**, and **robust**.



## ✅ Core Java: `final`, `static`, `this`, `super`

---

### 🔹 `final` Keyword

**Meaning:**  
Used to mark **constants**, prevent **method overriding**, and prevent **inheritance**.

**Use Cases:**
1️⃣ **Final Variable:** Value cannot change.  
2️⃣ **Final Method:** Cannot be overridden.  
3️⃣ **Final Class:** Cannot be subclassed.

**Examples:**

```java
final int MAX_USERS = 100; // constant

final class Utility {
    // cannot be extended
}

class Parent {
    final void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    // void show() {} // ERROR: Cannot override final method
}
```

✅ **Best Practice:** Use `final` to design immutable classes, e.g., `String` is final.

---

### 🔹 `static` Keyword

**Meaning:**  
Belongs to the **class**, not to instances. Shared across all instances.

**Use Cases:**
- **Static Variables:** Common for all objects.
- **Static Methods:** Called without object.
- **Static Blocks:** Run once when class loads.
- **Static Nested Classes:** Does not need outer class instance.

**Example:**

```java
class Counter {
    static int count = 0; // shared

    Counter() {
        count++;
    }

    static void showCount() {
        System.out.println(count);
    }
}

public class StaticDemo {
    public static void main(String[] args) {
        new Counter();
        new Counter();
        Counter.showCount(); // 2
    }
}
```

✅ **Best Practice:** Use for **utility methods** (`Math.abs()`), constants (`static final`).  
❌ **Pitfall:** Cannot access instance variables directly from static context.

---

### 🔹 `this` Keyword

**Meaning:**  
Refers to **current object**.

**Use Cases:**
- Distinguish fields from parameters.
- Call other constructors (`this()`).
- Pass current object (`return this`).

**Example:**

```java
class Person {
    String name;

    Person(String name) {
        this.name = name; // distinguish field vs param
    }

    Person() {
        this("Default"); // call another constructor
    }

    Person getSelf() {
        return this;
    }
}
```

✅ **Best Practice:** Good for fluent APIs (`return this`).

---

### 🔹 `super` Keyword

**Meaning:**  
Refers to **parent class**.

**Use Cases:**
- Call parent’s method.
- Access parent’s fields.
- Call parent constructor (`super()`).

**Example:**

```java
class Parent {
    void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    void show() {
        super.show(); // call parent version
        System.out.println("Child show");
    }

    Child() {
        super(); // call parent constructor (implicit if no-arg)
    }
}
```

✅ **Best Practice:** Use `super` to clarify which method you want to call when overridden.

---

## ✅ Quick Recap

| Keyword | Meaning | Common Use |
|---------|---------|-------------|
| `final` | Prevent change | Constant var, sealed method/class |
| `static` | Belongs to class | Shared data, utility methods |
| `this` | Current object | Distinguish fields, call other ctor |
| `super` | Parent class | Call parent’s method/ctor |

---

## ✅ Core Java: Exception Handling

---

### 🔹 What is Exception Handling?

**Exception Handling** is Java’s robust mechanism to handle **runtime errors** gracefully and maintain normal program flow.

It helps **detect**, **handle**, and **recover** from abnormal conditions without crashing the application.

---

### 🔹 Types of Exceptions

1️⃣ **Checked Exceptions**
- Errors that are **checked at compile-time**.
- The compiler forces you to handle or declare them.
- Examples: `IOException`, `SQLException`.

2️⃣ **Unchecked Exceptions**
- **Runtime exceptions**, not checked at compile time.
- Typically due to programming errors (bad logic).
- Examples: `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`.

3️⃣ **Errors**
- Serious issues the application usually can’t handle.
- Example: `OutOfMemoryError`, `StackOverflowError`.

---

### 🔹 Basic Syntax

```java
try {
    // risky code
} catch (ExceptionType e) {
    // handle exception
} finally {
    // always executed
}
```

---

### 🔹 Example

```java
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        } finally {
            System.out.println("Cleanup if needed");
        }
    }
}
```

✅ `finally` block **always executes**, even if exception is not thrown.

---

### 🔹 Try-With-Resources (Java 7+)

For automatic resource management (like closing files, DB connections).

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
// Auto closes br
```

---

### 🔹 Custom Exceptions

You can create your own exceptions for business-specific validation.

```java
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}

class Test {
    void validate(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Not eligible");
    }
}
```

---

### 🔹 Best Practices

✅ **Catch specific exceptions**, not generic `Exception`.  
✅ **Never swallow exceptions** — log or rethrow.  
✅ Use **`finally`** for resource cleanup.  
✅ Prefer **try-with-resources** for I/O and JDBC.  
✅ Wrap **checked exceptions** if propagating (e.g., `RuntimeException`).  
✅ Add context info to error messages.

---

### 🔹 Pitfalls

❌ Empty `catch` blocks — hides bugs.  
❌ Catching `Throwable` or `Error` — don’t catch system errors like `OutOfMemoryError`.  
❌ Bad practice to use exceptions for normal flow — use them for exceptional scenarios only.

---

### 🔹 Common Interview Q

✅ **Can you catch multiple exceptions in one catch?**  
👉 Yes — Java 7+ supports multi-catch:

```java
try {
    // risky
} catch (IOException | SQLException e) {
    e.printStackTrace();
}
```

✅ **Difference between `throw` and `throws`?**  
- `throw` — actually throws an exception.
- `throws` — declares that a method might throw an exception.

---

## ✅ Quick Recap

| Concept | Meaning |
|---------|---------|
| Checked | Must handle or declare |
| Unchecked | Runtime, usually logic bugs |
| Error | Serious, not handled by app |
| `try-catch-finally` | Standard block |
| `try-with-resources` | Auto-close resources |


# ✅ Java Memory Model (JMM) — Detailed JVM Memory Areas

---

## 🔹 What is the Java Memory Model?

The **Java Memory Model (JMM)** defines **how threads interact with memory** — it describes how variables are stored, read, written, and how changes become visible across threads.

The JVM implements this with **well-defined runtime memory areas**.

---

## ✅ JVM Runtime Memory Areas

| Memory Area        | Purpose                                   | Scope                |
|--------------------|-------------------------------------------|----------------------|
| **Method Area**    | Class metadata, static fields, bytecode   | Shared across threads |
| **Heap**           | Objects, instance data                    | Shared across threads |
| **Stack**          | Local variables, method calls             | One per thread       |
| **PC Register**    | Address of current executing instruction  | One per thread       |
| **Native Stack**   | Native method calls via JNI               | One per thread       |

---

## 🔹 1️⃣ Method Area (Metaspace)

- Stores **class metadata**, method bytecode, static variables, runtime constant pool.
- **HotSpot JVM (Java 8+)** uses **Metaspace** instead of PermGen — dynamically grows with native memory.
- Example: `class MyClass {}` → bytecode & structure stored here.
- Shared by all threads.

---

## 🔹 2️⃣ Heap

- Runtime area for **all objects** and **instance variables**.
- Managed by the **Garbage Collector (GC)**.
- Divided into:
  - **Young Generation**: Eden + Survivor spaces for short-lived objects.
  - **Old Generation (Tenured)**: Long-lived objects.
  - **Humongous Objects**: Large buffers/arrays (G1 GC).
- Shared by all threads.

Example:
```java
String s = new String("abc"); // Heap allocated
```

---

## 🔹 3️⃣ Java Stack

- Each thread has its **own JVM stack**.
- Stores **stack frames**:
  - Local variables
  - Operand stack
  - Return addresses
- **Primitive variables & references** live here. (Objects themselves → Heap)

Thread-confined → naturally thread-safe.

---

## 🔹 4️⃣ PC Register

- Each thread has its own **Program Counter (PC)**.
- Holds the address of the **current JVM instruction** being executed.
- Crucial for managing method calls and branch execution.

---

## 🔹 5️⃣ Native Method Stack

- Supports **native method execution** (`JNI`).
- Stores local variables, return addresses for native code.
- One per thread.

---

## 🔹 📌 How JMM and JVM Memory Areas Connect

- **Heap + Method Area** → Shared → multiple threads can access same data.
- **Stack + PC Register + Native Stack** → Thread-local → no shared data → no race conditions.

**JMM** ensures:
- **Visibility:** One thread’s write visible to others (`volatile`/locks).
- **Atomicity:** Operations done fully or not at all.
- **Ordering:** Prevents unsafe reorderings.

---

## 🔹 ⚙️ Best Practices & Tuning

✅ **GC tuning:**  
- `-Xms` / `-Xmx` → Initial / max heap.
- `-XX:MaxMetaspaceSize` → Cap Metaspace.

✅ **Memory leaks:**  
- Release object references you no longer need.

✅ **Use local variables:**  
- Faster, GC-free → stored on stack.

✅ **Tools:**  
- `jmap`, `jconsole`, `VisualVM`, `Flight Recorder` → Monitor memory usage.

---

## ✨ Key Takeaway

The **Java Memory Model** and JVM memory areas together ensure:
- Automatic memory management.
- Thread safety.
- High performance for modern multi-threaded apps.

Mastering them means writing **efficient, safe, maintainable Java code**.

---


### 🔹 Garbage Collection (GC)

✅ **Automatic** — frees unreachable objects.

Key Points:
- **Minor GC:** Cleans young generation → cheap, frequent.
- **Major/Full GC:** Cleans old generation → more costly, can pause app.
- `finalize()` → rarely used now; `AutoCloseable`/`try-with-resources` is better.

---

### 🔹 Pitfalls

❌ Holding unnecessary references → memory leaks.  
❌ Not closing resources → leads to leaks outside JVM heap too (file descriptors, DB connections).  
❌ Excessive object creation → GC pressure → performance drop.

---

### 🔹 Best Practices

✅ Use **`null`** references when objects are no longer needed if manual release helps GC.

✅ Prefer **local variables** — they die when method exits.

✅ Monitor with tools: `jvisualvm`, `jconsole`, profilers.

✅ Understand GC tuning: heap sizes, GC types (G1, CMS, ZGC).

---

### 🔹 Related Concepts

- **Escape Analysis:** JVM decides if object can be allocated on stack → reduces heap allocation.
- **Memory Leaks in Java:** Mostly happen due to long-living references, static collections, listeners not removed.

---

## ✅ Quick Recap

| Concept | Key Points |
|---------|-------------|
| **Stack** | Thread-local, holds frames & local vars |
| **Heap** | Shared, holds objects |
| **Method Area (Metaspace)** | Class-level info |
| **GC** | Reclaims unreachable objects |
| **Pitfall** | Leaks, excessive allocations |

---

## ✅ Core Java: Serialization

---

### 🔹 What is Serialization?

**Serialization** is the process of converting an object’s state into a **byte stream** so it can be:
- Saved to a file or database.
- Sent over a network.
- Stored for later reconstruction.

**Deserialization** is the reverse — rebuilding the object from the byte stream.

---

### 🔹 How is Serialization done in Java?

Java provides built-in support via the `java.io.Serializable` **marker interface**.

**Marker interface:**  
It has no methods — just tells the JVM the object is eligible for serialization.

---

### 🔹 Basic Example

```java
import java.io.*;

// Must implement Serializable
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int id;
    transient String password; // won't be serialized

    public Employee(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}

public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("Alice", 101, "secret");

        // Serialize
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.ser"));
        out.writeObject(emp);
        out.close();

        // Deserialize
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.ser"));
        Employee deserializedEmp = (Employee) in.readObject();
        in.close();

        System.out.println(deserializedEmp.name); // Alice
        System.out.println(deserializedEmp.password); // null (transient)
    }
}
```

✅ **Key points:**
- `serialVersionUID` helps JVM check class compatibility during deserialization.
- `transient` fields are skipped — useful for sensitive or derived data.

---

### 🔹 Common Pitfalls

❌ Not defining `serialVersionUID` → JVM generates one by default; version mismatch during deserialization can cause `InvalidClassException`.

❌ Sensitive data leakage → don’t serialize passwords or secrets directly.

❌ Breaking backward compatibility → changing class structure breaks old serialized data unless you handle versioning.

❌ Serialization only saves **object state**, not static fields or methods.

---

### 🔹 Best Practices

✅ Always define `serialVersionUID` explicitly.

✅ Use `transient` for fields you don’t want in the serialized form.

✅ If custom logic is needed, implement:
```java
private void writeObject(ObjectOutputStream oos) throws IOException
private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
```

✅ For advanced scenarios, use `Externalizable` — gives full control over what and how to serialize (must implement `writeExternal` and `readExternal`).

✅ Use modern alternatives if possible — JSON, Protobuf, Avro are better for many real-world scenarios.

---

### 🔹 Real-World Uses

- **Session persistence:** Saving user sessions to disk.
- **Caching:** Storing Java objects in distributed caches (e.g., Ehcache, Hazelcast).
- **RMI:** Java Remote Method Invocation uses serialization under the hood.

---

## ✅ Quick Recap

| Concept | Meaning |
|---------|---------|
| **Serializable** | Marker interface |
| **serialVersionUID** | Version control for classes |
| **transient** | Skip field in serialization |
| **Deserialization** | Rebuild object from bytes |
| **Pitfalls** | Compatibility, sensitive data, versioning |

---

## ✅ Core Java: Java 8+ Key Features

---

### 🔹 Why Java 8 Matters

Java 8 was a **huge leap** for Java — introducing **functional programming**, **Stream API**, and modern idioms that make Java more concise and expressive.

---

### 🔹 1️⃣ Lambda Expressions

**What:**  
Anonymous functions — let you pass behavior as data.

**Syntax:**  
`(parameters) -> expression` or `(parameters) -> { statements }`

**Example:**

```java
List<String> names = Arrays.asList("A", "B", "C");
names.forEach(n -> System.out.println(n));
```

✅ Shorter than anonymous inner classes.

---

### 🔹 2️⃣ Functional Interfaces

**What:**  
An interface with exactly **one abstract method**.

**Example:**  
`Runnable`, `Callable`, `Comparator`, `Function`, `Predicate`.

You can define your own:

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

Calculator calc = (a, b) -> a + b;
```

✅ `@FunctionalInterface` helps catch mistakes.

---

### 🔹 3️⃣ Method References

**What:**  
Shortcut for calling existing methods with lambdas.

**Syntax:**  
`ClassName::methodName` or `object::methodName`

**Example:**

```java
names.forEach(System.out::println);
```

---

### 🔹 4️⃣ Streams API

**What:**  
A modern, functional way to process collections with **filter/map/reduce**.

**Key ops:**  
- `filter`: Keep elements that match.  
- `map`: Transform elements.  
- `reduce`: Combine elements to produce a single result.  
- `collect`: Gather results into a list, set, map.

**Example:**

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

int sum = nums.stream()
              .filter(n -> n % 2 == 0)
              .mapToInt(n -> n)
              .sum();

System.out.println(sum); // 6 (2+4)
```

✅ Streams are **lazy** and **pipeline-friendly**.

---

### 🔹 5️⃣ Optional

**What:**  
A container object to represent a value that might be **present or absent** → helps reduce `NullPointerException`.

**Example:**

```java
Optional<String> name = Optional.of("Alice");
Optional<String> empty = Optional.empty();

name.ifPresent(System.out::println); // Alice

String fallback = empty.orElse("Default");
System.out.println(fallback); // Default
```

✅ Use `Optional` for return types, not for fields or parameters.

---

### 🔹 6️⃣ Default & Static Methods in Interfaces

**What:**  
Interfaces can have **default** and **static** methods.

**Why:**  
Supports backward compatibility and interface evolution.

**Example:**

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle starting");
    }

    static void info() {
        System.out.println("Vehicles have engines");
    }
}
```

---

### 🔹 Other Java 8 Goodies

✅ **Date/Time API (`java.time`)** → Replaces legacy `Date`.

✅ **Nashorn JS Engine** → Execute JS (removed later).

✅ **New Collectors & Parallel Streams** → For performance.

---

### 🔹 Pitfalls

❌ Overusing parallel streams — can hurt performance for small tasks.

❌ Lambdas can make debugging stack traces harder.

❌ Forgetting that streams can be **consumed only once**.

---

## ✅ Quick Recap

| Feature | Use |
|---------|-----|
| **Lambda** | Anonymous functions |
| **Streams API** | Functional collection processing |
| **Functional Interface** | Interface with one abstract method |
| **Method Reference** | Shorthand for lambdas |
| **Optional** | Null-safe container |
| **Default Methods** | Interface evolution |

---

## ✅ Core Java: I/O, NIO2 & JVM Internals

---

### 🔹 1️⃣ Java I/O (Old I/O)

**What:**  
Java’s **classic I/O** (`java.io` package) provides **blocking**, **stream-based** APIs for reading/writing data.

**Streams:**  
- **Byte streams:** `InputStream`, `OutputStream` → binary data (files, sockets).
- **Character streams:** `Reader`, `Writer` → text data.

---

**Example: Read a text file (classic I/O)**

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

✅ Use **Buffered** streams for efficiency.

---

### 🔹 2️⃣ NIO & NIO2 (New I/O)

**What:**  
Java **NIO** (Non-blocking I/O) → `java.nio` — introduced for **high-performance**, **buffer-based**, **non-blocking I/O**.

**Key concepts:**  
- **Buffers:** Container for data (e.g., `ByteBuffer`).  
- **Channels:** Like streams, but bi-directional and non-blocking.  
- **Selectors:** Monitor multiple channels (e.g., multiple sockets) with a single thread.

**NIO2 (Java 7):**  
- Improved file operations → `java.nio.file` → `Path`, `Files` API.  
- Better directory walking (`Files.walk()`).

---

**Example: Read a file with NIO2**

```java
Path path = Paths.get("file.txt");
List<String> lines = Files.readAllLines(path);
lines.forEach(System.out::println);
```

---

**NIO vs I/O:**

| Aspect | Classic I/O | NIO/NIO2 |
|--------|--------------|----------|
| Mode | Blocking | Non-blocking |
| Data | Stream | Buffer |
| Scalability | One thread per stream | One thread for many channels |

✅ NIO/NIO2 is great for scalable servers (e.g., Netty).

---

### 🔹 Best Practices

✅ Always close streams → use `try-with-resources`.  
✅ Prefer NIO2 (`Files.copy`, `Files.walk`) for modern file ops.  
✅ For heavy network I/O, prefer async NIO + frameworks like Netty.

---

## ✅ 3️⃣ JVM Internals (Quick Deep Dive)

---

### 🔹 What is the JVM?

The **Java Virtual Machine (JVM)** is the runtime that:
- Loads `.class` files (bytecode).
- Verifies and executes bytecode.
- Manages memory (heap, stack, GC).
- Provides JIT compilation.

---

### 🔹 Key Components

| Part | What it does |
|------|---------------|
| **ClassLoader Subsystem** | Loads classes dynamically |
| **Runtime Data Areas** | Stack, Heap, Method Area, PC Register |
| **Execution Engine** | Interprets bytecode or compiles with JIT |
| **JIT Compiler** | Just-In-Time compiler → speeds up repeated code |
| **Garbage Collector** | Frees memory by cleaning unreachable objects |

---

### 🔹 ClassLoader Details

**ClassLoader types:**  
- **Bootstrap ClassLoader:** Loads core Java classes (`rt.jar`).  
- **Extension ClassLoader:** Loads `ext` library classes.  
- **Application ClassLoader:** Loads app’s `.class` files.

Custom classloaders → used for advanced frameworks, hot reloading, plugins.

---

### 🔹 JIT Compiler

✅ HotSpot JVM → combines interpretation + JIT compilation.  
✅ JIT compiles **hot methods** (frequently used) to native code → speeds up performance.

---

### 🔹 Garbage Collector (Recap)

✅ **Generational GC:** Eden → Survivor → Tenured.  
✅ Common algorithms: G1, CMS, ZGC, Shenandoah.  
✅ GC tuning: `-Xmx`, `-Xms`, `-XX:+UseG1GC`.

---

### 🔹 Tools for JVM Monitoring

✅ `jconsole`, `jvisualvm`, `jstat`  
✅ `jstack` → thread dump for deadlock detection.  
✅ `jmap` → memory maps & heap dumps.

---

## ✅ Quick Recap

| Concept | Key Points |
|---------|-------------|
| **I/O** | Blocking, stream-based |
| **NIO/NIO2** | Non-blocking, buffer-based, scalable |
| **JVM** | Loads, executes, optimizes |
| **ClassLoader** | Dynamic class loading |
| **JIT** | Faster native execution |
| **GC** | Automatic memory management |

---
