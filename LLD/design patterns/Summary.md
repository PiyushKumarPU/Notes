
---

# 🎯 Design Patterns — Master Cheat Sheet (GoF 23)

---

## 🏗️ Creational Patterns (Object Creation)

### 1. **Singleton**

Ensure only one instance exists, global access.
✅ Used in: Logging, config, DB connection pools.

```java
class Singleton {
  private static Singleton instance;
  private Singleton() {}
  public static Singleton getInstance() {
    if (instance == null) instance = new Singleton();
    return instance;
  }
}
```

---

### 2. **Factory Method**

Subclasses decide which class to instantiate.
✅ Used in: Shape factories, Spring Beans.

```java
Shape s = ShapeFactory.getShape("circle");
```

---

### 3. **Abstract Factory**

Create families of related objects.
✅ Used in: UI themes, cross-platform UIs.

```java
UIFactory factory = new DarkThemeFactory();
Button b = factory.createButton();
```

---

### 4. **Prototype**

Clone existing objects instead of creating from scratch.
✅ Used in: Game engines, Java `Object.clone()`.

```java
Shape c1 = new Circle(10);
Shape c2 = c1.clone();
```

---

### 5. **Builder**

Construct complex objects step by step.
✅ Used in: StringBuilder, Lombok `@Builder`.

```java
User u = new User.UserBuilder().setName("John").setAge(30).build();
```

---

## 🧱 Structural Patterns (Object Composition)

### 6. **Adapter**

Convert one interface into another.
✅ Used in: Legacy API wrappers, Spring MVC Adapters.

```java
MediaPlayer p = new AudioAdapter(new LegacyAudioPlayer());
```

---

### 7. **Bridge**

Decouple abstraction from implementation.
✅ Used in: JDBC (API vs driver), logging.

```java
Shape c = new Circle(new RedColor());
```

---

### 8. **Composite**

Treat individual objects and groups uniformly.
✅ Used in: File system, menus, DOM trees.

```java
Directory root = new Directory("Root");
root.add(new File("resume.pdf"));
```

---

### 9. **Decorator**

Add behavior dynamically without changing class.
✅ Used in: Java I/O streams, Spring Boot filters.

```java
Coffee c = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
```

---

### 10. **Facade**

Simplify a complex subsystem with one interface.
✅ Used in: API gateways, `JdbcTemplate`.

```java
ComputerFacade pc = new ComputerFacade();
pc.startComputer();
```

---

### 11. **Flyweight**

Share objects to save memory.
✅ Used in: String pool, caches, game objects.

```java
Shape red = ShapeFactory.getCircle("Red"); // reused
```

---

### 12. **Proxy**

Provide a placeholder to control access to real object.
✅ Used in: Hibernate lazy loading, Nginx, Spring AOP.

```java
Image img = new ProxyImage("photo.png");
img.display();
```

---

## 🎭 Behavioral Patterns (Object Interaction)

### 13. **Observer**

One-to-many dependency, pub/sub.
✅ Used in: Kafka, GUIs, Spring Events.

```java
agency.addObserver(cnn);
agency.notifyObservers("Breaking News!");
```

---

### 14. **Strategy**

Encapsulate interchangeable algorithms.
✅ Used in: Payments, sorting, authentication.

```java
ctx.setPaymentStrategy(new UpiPayment("id@upi"));
```

---

### 15. **Command**

Encapsulate requests as objects.
✅ Used in: Undo/redo, task queues.

```java
remote.setCommand(new LightOnCommand(light));
```

---

### 16. **State**

Object behavior changes with state.
✅ Used in: Order lifecycle, ATM states.

```java
order.nextStep(); // New → Processing → Shipped → Delivered
```

---

### 17. **Template Method**

Algorithm skeleton in base class, steps overridden.
✅ Used in: `JdbcTemplate`, JUnit, Servlets.

```java
DataProcessor csv = new CSVDataProcessor();
csv.process();
```

---

### 18. **Chain of Responsibility**

Pass request along chain until handled.
✅ Used in: Middleware, logging, approval flows.

```java
info.setNext(debug);
debug.setNext(error);
info.log("ERROR", "Something failed");
```

---

### 19. **Mediator**

Central object controls communication between objects.
✅ Used in: Chat apps, UI dialog management.

---

### 20. **Memento**

Capture & restore object state (undo).
✅ Used in: Text editors, checkpoints, versioning.

---

### 21. **Iterator**

Sequential access without exposing collection details.
✅ Used in: Java `Iterator`, `for-each`.

---

### 22. **Interpreter**

Define grammar & interpret language.
✅ Used in: SQL engines, regex parsers.

---

### 23. **Visitor**

Add new operations without modifying classes.
✅ Used in: Compilers, AST traversals, analytics.

---

# 🔥 Quick Mnemonics

* **Creational** → *How to create objects* (Singleton, Factory, Builder, etc.).
* **Structural** → *How to compose objects* (Adapter, Bridge, Proxy, etc.).
* **Behavioral** → *How objects interact* (Observer, Strategy, State, etc.).

---