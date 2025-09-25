# Chain of Responsibility Design Pattern

## 🔹 What is it?

The **Chain of Responsibility (CoR) Pattern** is a **behavioral design pattern** where a request is **passed along a chain of handlers**, and each handler decides:

* to process the request, OR
* to pass it along to the next handler in the chain.

---

## 🔹 Why use it?

* To avoid coupling a sender with a specific receiver.
* To build flexible **pipelines of processing steps**.
* To support **filtering, logging, middleware, approval flows**.

---

## 🔹 How it works?

1. Define a **Handler interface** with `setNext()` and `handle()` methods.
2. Concrete handlers either process the request or pass it to the next handler.
3. The client sends the request to the first handler in the chain.

---

## ✅ Example (Java)

```java
// Handler
abstract class Logger {
    protected Logger nextLogger;

    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(String level, String message) {
        if (canHandle(level)) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.log(level, message);
        }
    }

    protected abstract boolean canHandle(String level);
    protected abstract void write(String message);
}

// Concrete Handlers
class InfoLogger extends Logger {
    protected boolean canHandle(String level) { return level.equals("INFO"); }
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}

class ErrorLogger extends Logger {
    protected boolean canHandle(String level) { return level.equals("ERROR"); }
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}

class DebugLogger extends Logger {
    protected boolean canHandle(String level) { return level.equals("DEBUG"); }
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}

// Client
public class CoRDemo {
    public static void main(String[] args) {
        Logger info = new InfoLogger();
        Logger debug = new DebugLogger();
        Logger error = new ErrorLogger();

        info.setNext(debug);
        debug.setNext(error);

        info.log("INFO", "This is an info message");
        info.log("DEBUG", "Debugging application");
        info.log("ERROR", "An error occurred!");
    }
}
```

### Output:

```
INFO: This is an info message
DEBUG: Debugging application
ERROR: An error occurred!
```

---

## 🔹 Pros

* ✅ Decouples sender from receiver (flexible chains).
* ✅ Easy to add/remove/modify handlers without touching others.
* ✅ Supports dynamic chains of responsibility.

---

## 🔹 Cons

* ❌ Hard to debug (request might pass through many handlers).
* ❌ Request may go unhandled if no handler accepts it.

---

## 🔹 Design Principle

* ✔️ Follows **Single Responsibility Principle** (each handler has one job).
* ✔️ Follows **Open/Closed Principle** (add new handlers without modifying chain).

---

## 🔹 Analogy (for fresher)

Think of a **customer service call center ☎️**:

* Call starts at Level 1 agent.
* If not resolved → escalates to Level 2.
* If still unresolved → escalates to Manager.
* Each level decides whether to handle or pass along.

---

✅ Real-world examples:

* Logging frameworks (INFO → DEBUG → ERROR).
* Servlet Filters in Java EE / Spring Boot middleware.
* Approval workflows (Team Lead → Manager → Director).
* Event handling pipelines (like Express.js middleware).

---
