# Creational Design Patterns

## Singleton
# 🔹 What is Singleton?

A **Singleton** ensures that:

- A class has only **one instance** throughout the application lifecycle.  
- It provides a **global access point** to that instance.  

---

## Classic Backend Examples

- **Database Connection Pool Manager**  
- **Central Configuration Manager**  
- **Logging Service**

# 🔹 Why do we use Singleton?

- Some resources are **expensive to create**  
  (e.g., Database connections, Thread pools, Caching layers).  

- We need **shared state** across the application.  

- We want a **centralized access point**  
  (e.g., Logger).  

---

✅ Instead of every module doing:  
```java
new DBConnectionPool();
```

# 🔹 Pros and Cons of Singleton

---

## ✅ Pros (Benefits)

- **Controlled access to one instance**  
  Prevents multiple inconsistent objects.  
  (e.g., avoids multiple configs loaded differently).

- **Performance efficiency**  
  Reduces costly initialization by reusing the single instance.

- **Global access**  
  Any part of the system can get the instance easily.

- **Encapsulation of shared resource**  
  The Singleton “owns” its resource (like connections, logging) in one place.

---

## ❌ Cons (Drawbacks)

- **Global state = Hidden dependency**  
  Code using `MySingleton.getInstance()` looks clean, but it actually depends on a global resource.  
  → Makes dependencies implicit and hard to trace.

- **Breaks testability**  
  Unit testing is painful because you can’t easily mock or replace the singleton (unless you add extra hooks).

- **Concurrency issues**  
  If not implemented carefully (thread-safe initialization), it can cause race conditions.

- **Tight coupling**  
  Every class that calls `Singleton.getInstance()` is tightly bound to that implementation.  
  → Replacing it later is hard.

- **Harder to scale in distributed systems**  
  A true singleton across multiple JVMs (e.g., in microservices) doesn’t exist unless you use external coordination (Redis, ZooKeeper, etc.).

---

# 🔹 What Design Principles does Singleton break?

- ⚠️ **Single Responsibility Principle (SRP)**  
  A class should have only one reason to change.  
  Singleton has two responsibilities:  
  1. Business logic (its actual job).  
  2. Lifecycle management (ensuring only one instance).

- ⚠️ **Open/Closed Principle (OCP)**  
  Singletons are usually not easily extendable.  
  Subclassing or replacing them in production is tricky.

- ⚠️ **Dependency Inversion Principle (DIP)**  
  High-level modules should depend on abstractions, not concretions.  
  Singleton introduces concrete global access (`getInstance()`), bypassing DI containers or interfaces.

- ⚠️ **Testability Principle (not part of SOLID but crucial)**  
  Hard to substitute in unit tests → leads to brittle test design.

---

# 🔹 When to Use Singleton (Pragmatic Advice)

- ✅ **Good fit**: For truly shared resources that make sense only as one instance  
  (connection pool, cache manager, config loader).

- ❌ **Bad fit**: For business logic or services where DI (Dependency Injection) frameworks (like **Spring**, **NestJS**)  
  already manage lifecycles better.  

👉 In modern backend frameworks, we often let the **IoC container enforce singleton scope** instead of writing the pattern manually.

## Way of Creating Singleton
### 🔥 Variants with Code, Advantages & Disadvantages

---

### 1️⃣ Lazy Initialization Singleton (Thread-Unsafe)

```java
public class LazySingleton {
  private static LazySingleton instance;

  private LazySingleton() {}

  public static LazySingleton getInstance() {
    if (instance == null) {
      instance = new LazySingleton();
    }
    return instance;
  }
}
```

✔️ **Advantages:**

- Simple, easy to implement
- Lazy initialization — instance created only when needed

❌ **Disadvantages:**

- Not thread-safe
- Multiple threads may create multiple instances

---

### 2️⃣ Thread-Safe Singleton (Synchronized Method)

```java
public class ThreadSafeSingleton {
  private static ThreadSafeSingleton instance;

  private ThreadSafeSingleton() {}

  public static synchronized ThreadSafeSingleton getInstance() {
    if (instance == null) {
      instance = new ThreadSafeSingleton();
    }
    return instance;
  }
}
```

✔️ **Advantages:**

- Thread-safe
- Lazy initialization

❌ **Disadvantages:**

- Synchronized method reduces performance
- All calls block on lock, even when already initialized

---

### 3️⃣ Double-Checked Locking Singleton

```java
public class DoubleCheckedSingleton {
  private static volatile DoubleCheckedSingleton instance;

  private DoubleCheckedSingleton() {}

  public static DoubleCheckedSingleton getInstance() {
    if (instance == null) {
      synchronized (DoubleCheckedSingleton.class) {
        if (instance == null) {
          instance = new DoubleCheckedSingleton();
        }
      }
    }
    return instance;
  }
}
```

✔️ **Advantages:**

- Thread-safe and efficient
- Synchronization used only once

❌ **Disadvantages:**

- Slightly more complex
- `volatile` required to prevent instruction reordering

---

### 4️⃣ Bill Pugh Singleton

```java
public class BillPughSingleton {
  private BillPughSingleton() {}

  private static class Helper {
    private static final BillPughSingleton INSTANCE = new BillPughSingleton();
  }

  public static BillPughSingleton getInstance() {
    return Helper.INSTANCE;
  }
}
```

✔️ **Advantages:**

- Thread-safe without synchronization overhead
- Lazy initialization
- Uses JVM class loading mechanism

❌ **Disadvantages:**

- Static holder idiom may be less familiar to beginners

---

### 5️⃣ Enum Singleton

```java
public enum EnumSingleton {
  INSTANCE;

  public void doSomething() {
    System.out.println("Enum Singleton in action");
  }
}
```

✔️ **Advantages:**

- Thread-safe by default
- Easiest to implement
- Serialization & reflection safe

❌ **Disadvantages:**

- Eager initialization
- Cannot accept lazy parameters
- Not suitable for complex inheritance

---

### 📌 Summary Table

| Variant                | Thread-Safe | Lazy | Advantages                | Disadvantages                      |
| ---------------------- | ----------- | ---- | ------------------------- | ---------------------------------- |
| Lazy Initialization    | ❌           | ✅    | Simple                    | Not thread-safe                    |
| Synchronized Method    | ✅           | ✅    | Thread-safe, simple       | Slower under heavy load            |
| Double-Checked Locking | ✅           | ✅    | Thread-safe, efficient    | Slightly complex, needs `volatile` |
| Bill Pugh Singleton    | ✅           | ✅    | Best combo of lazy & safe | Static holder idiom lesser known   |
| Enum Singleton         | ✅           | ❌    | Simple, robust, safe      | Eager, not parameterized           |

---