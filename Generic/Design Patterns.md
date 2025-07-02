## ✅ Singleton Pattern 

### 📌 Definition

Ensures only **one instance** exists for a class, with a global access point.

---

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

### ✔️ Typical Uses

- Logging frameworks
- Configuration classes
- Thread pools
- Caches
- Database connection managers

---

**



---

## ✅ Factory Pattern 

### 📌 Definition
Defines an interface for creating an object but lets subclasses decide which class to instantiate. It centralizes object creation logic.

---

### 🔑 When to use
- When you need to create many related objects.
- When the creation logic is complex or conditional.

---

### 🧩 Structure Example
```java
interface Shape {
  void draw();
}

class Circle implements Shape {
  public void draw() { System.out.println("Circle"); }
}

class Rectangle implements Shape {
  public void draw() { System.out.println("Rectangle"); }
}

class ShapeFactory {
  public Shape getShape(String type) {
    if (type == null) return null;
    if (type.equalsIgnoreCase("CIRCLE")) return new Circle();
    else if (type.equalsIgnoreCase("RECTANGLE")) return new Rectangle();
    return null;
  }
}
```

---

### ✔️ Advantages
- Encapsulates object creation logic.
- Promotes loose coupling.
- Adheres to Open/Closed Principle.

❌ **Disadvantages**
- Increases number of classes.
- Simple instantiation could be over-engineered.

---

### 📌 Real-World Examples
- `java.util.Calendar.getInstance()`
- `java.sql.DriverManager.getConnection()`
- Spring `BeanFactory` / `ApplicationContext`

---





---

## ✅ Abstract Factory Pattern 

### 📌 Definition

Abstract Factory provides an interface for creating families of related or dependent objects without specifying their concrete classes.

---

### 🔑 When to use

- When your system needs to be independent of how its products are created.
- When you need to enforce families of related objects to be used together.

---

### 🧩 Structure Example

```java
interface GUIFactory {
  Button createButton();
  Checkbox createCheckbox();
}

class WinFactory implements GUIFactory {
  public Button createButton() { return new WinButton(); }
  public Checkbox createCheckbox() { return new WinCheckbox(); }
}

class MacFactory implements GUIFactory {
  public Button createButton() { return new MacButton(); }
  public Checkbox createCheckbox() { return new MacCheckbox(); }
}
```

---

### ✔️ Advantages

- Enforces consistency among products.
- Isolates concrete classes.
- Supports product families easily.

❌ **Disadvantages**

- Harder to add new products.
- Can be more complex than simple factories.

---

### 📌 Real-World Examples

- `javax.xml.parsers.DocumentBuilderFactory`
- `javax.xml.transform.TransformerFactory`
- Spring `ApplicationContext` for bean families

---





---

## ✅ Builder Pattern 

### 📌 Definition
Separates the construction of a complex object from its representation so that the same construction process can create different representations.

---

### 🔑 When to use
- When an object needs many optional attributes.
- When construction is complex or step-wise.
- When immutable objects are required.

---

### 🧩 Structure Example
```java
class User {
  private final String firstName;
  private final String lastName;
  private final int age;

  private User(Builder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.age = builder.age;
  }

  static class Builder {
    private String firstName;
    private String lastName;
    private int age;

    public Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder age(int age) {
      this.age = age;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
```

---

### ✔️ Advantages
- Makes object construction clear & readable.
- Supports immutability.
- Good for objects with many optional fields.

❌ **Disadvantages**
- More verbose than telescoping constructors.
- Needs extra classes (Builder).

---

### 📌 Real-World Examples
- `StringBuilder` and `StringBuffer`
- `java.lang.ProcessBuilder`
- `Lombok @Builder`

---





---

## ✅ Prototype Pattern 

### 📌 Definition
Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.

---

### 🔑 When to use
- When creating an object is costly (e.g., loading, database operations).
- When many similar objects are needed.

---

### 🧩 Structure Example
```java
interface Prototype extends Cloneable {
  Prototype clone() throws CloneNotSupportedException;
}

class Document implements Prototype {
  private String content;
  public Document(String content) { this.content = content; }
  public Prototype clone() throws CloneNotSupportedException {
    return (Prototype) super.clone();
  }
}
```

---

### ✔️ Advantages
- Cloning saves costly creation time.
- Reduces subclassing.

❌ **Disadvantages**
- Cloning can be tricky for complex object graphs.
- Deep vs shallow copy challenges.

---

### 📌 Real-World Examples
- `java.lang.Object#clone()`
- `java.lang.Cloneable`
- Copy constructors

---





---

## ✅ Adapter Pattern 

### 📌 Definition

Adapter Pattern allows incompatible interfaces to work together. It converts the interface of a class into another interface clients expect.

---

### 🔑 When to use

- When you want to use an existing class but its interface does not match your needs.
- To reuse legacy code with a new system.

---

### 🧩 Structure Example

```java
interface Target {
  void request();
}

class Adaptee {
  void specificRequest() { System.out.println("Adaptee specific request"); }
}

class Adapter implements Target {
  private Adaptee adaptee;

  public Adapter(Adaptee adaptee) {
    this.adaptee = adaptee;
  }

  public void request() {
    adaptee.specificRequest();
  }
}
```

---

### ✔️ Advantages

- Reuses existing functionality.
- Improves code compatibility without modifying source code.

❌ **Disadvantages**

- Can increase complexity.
- Too many adapters can make code harder to maintain.

---

### 📌 Real-World Examples

- `java.io.InputStreamReader`
- `java.io.OutputStreamWriter`
- Spring `Adapter` interfaces (HandlerAdapter)

---





---

## ✅ Decorator Pattern 

### 📌 Definition
Decorator Pattern allows behavior to be added to an individual object dynamically, without affecting the behavior of other objects from the same class.

---

### 🔑 When to use
- To add responsibilities to objects without subclassing.
- When extension by inheritance is impractical.

---

### 🧩 Structure Example
```java
interface Notifier {
  void send();
}

class EmailNotifier implements Notifier {
  public void send() { System.out.println("Sending Email"); }
}

class SMSDecorator implements Notifier {
  private Notifier notifier;

  public SMSDecorator(Notifier notifier) {
    this.notifier = notifier;
  }

  public void send() {
    notifier.send();
    System.out.println("Sending SMS");
  }
}
```

---

### ✔️ Advantages
- Flexible alternative to subclassing.
- Combines behaviors at runtime.

❌ **Disadvantages**
- Many small classes can increase complexity.
- Debugging can become complicated.

---

### 📌 Real-World Examples
- `java.io.BufferedReader`
- `java.io.DataInputStream`
- Spring Security filter chains

---





---

## ✅ Proxy Pattern 

### 📌 Definition
Proxy Pattern provides a surrogate or placeholder for another object to control access to it.

### 🧩 Example
```java
interface Service {
  void execute();
}

class RealService implements Service {
  public void execute() { System.out.println("Executing Service"); }
}

class SecurityProxy implements Service {
  private RealService realService;

  public void execute() {
    if (checkAccess()) {
      if (realService == null) realService = new RealService();
      realService.execute();
    }
  }

  private boolean checkAccess() {
    System.out.println("Checking access");
    return true;
  }
}
```

✔️ **Advantages:** Controls access, adds security/logging.
❌ **Disadvantages:** Adds complexity, may slow down real object.

---

## ✅ Composite Pattern 

### 📌 Definition
Composite Pattern lets clients treat individual objects and compositions of objects uniformly.

### 🧩 Example
```java
interface FileSystem {
  void ls();
}

class File implements FileSystem {
  private String name;
  public File(String name) { this.name = name; }
  public void ls() { System.out.println(name); }
}

class Directory implements FileSystem {
  private String name;
  private List<FileSystem> children = new ArrayList<>();

  public Directory(String name) { this.name = name; }
  public void add(FileSystem fs) { children.add(fs); }

  public void ls() {
    System.out.println(name);
    for (FileSystem fs : children) fs.ls();
  }
}
```
✔️ **Advantages:** Treats leaf and composite uniformly.
❌ **Disadvantages:** Can make design overly general.

---

## ✅ Facade Pattern 

### 📌 Definition
Facade Pattern provides a simplified interface to a complex subsystem.

### 🧩 Example
```java
class VideoFile {}
class Codec {}
class BitrateReader {}
class AudioMixer {}

class VideoConversionFacade {
  public void convertVideo(String filename, String format) {
    System.out.println("Video converted to " + format);
  }
}
```
✔️ **Advantages:** Easy to use, hides complexity.
❌ **Disadvantages:** May become a god object.

---

## ✅ Strategy Pattern 

### 📌 Definition
Strategy Pattern defines a family of algorithms, encapsulates each, and makes them interchangeable.

### 🧩 Example
```java
interface PaymentStrategy {
  void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
  public void pay(int amount) { System.out.println("Paid " + amount + " by credit card"); }
}

class PaypalPayment implements PaymentStrategy {
  public void pay(int amount) { System.out.println("Paid " + amount + " by PayPal"); }
}
```
✔️ **Advantages:** Easily swap algorithms.
❌ **Disadvantages:** Clients must know strategy differences.

---

## ✅ Observer Pattern 

### 📌 Definition
Observer Pattern defines a one-to-many dependency between objects.

### 🧩 Example
```java
interface Observer {
  void update(String message);
}

class ConcreteObserver implements Observer {
  public void update(String message) {
    System.out.println("Received: " + message);
  }
}

class Subject {
  private List<Observer> observers = new ArrayList<>();
  public void add(Observer o) { observers.add(o); }
  public void notifyAllObservers(String msg) {
    for (Observer o : observers) o.update(msg);
  }
}
```
✔️ **Advantages:** Loose coupling.
❌ **Disadvantages:** May cause cascading updates.

---

## ✅ Command Pattern 

### 📌 Definition
Command Pattern encapsulates a request as an object, letting you parametrize clients with requests.

### 🧩 Example
```java
interface Command {
  void execute();
}

class Light {
  void on() { System.out.println("Light ON"); }
}

class LightOnCommand implements Command {
  private Light light;
  public LightOnCommand(Light light) { this.light = light; }
  public void execute() { light.on(); }
}
```
✔️ **Advantages:** Decouples invoker & receiver.
❌ **Disadvantages:** Lots of small command classes.

---

## ✅ Chain of Responsibility 

### 📌 Definition
Chain of Responsibility lets you pass a request along a chain of handlers.

### 🧩 Example
```java
abstract class Handler {
  protected Handler next;
  public void setNext(Handler next) { this.next = next; }
  public abstract void handle(String request);
}

class AuthHandler extends Handler {
  public void handle(String request) {
    if (request.equals("AUTH")) System.out.println("Handled AUTH");
    else if (next != null) next.handle(request);
  }
}
```
✔️ **Advantages:** Flexible chain flow.
❌ **Disadvantages:** Debugging can be hard.

---

## ✅ Template Method 

### 📌 Definition
Template Method defines the program skeleton and lets subclasses redefine certain steps.

### 🧩 Example
```java
abstract class DataProcessor {
  public final void process() {
    readData();
    processData();
    writeData();
  }

  abstract void readData();
  abstract void processData();
  abstract void writeData();
}
```
✔️ **Advantages:** Code reuse.
❌ **Disadvantages:** Inflexible base class.

---

## ✅ State Pattern 

### 📌 Definition
State Pattern lets an object alter its behavior when its internal state changes.

### 🧩 Example
```java
interface State {
  void handle();
}

class OnState implements State {
  public void handle() { System.out.println("Device is ON"); }
}

class OffState implements State {
  public void handle() { System.out.println("Device is OFF"); }
}

class Context {
  private State state;
  public void setState(State state) { this.state = state; }
  public void request() { state.handle(); }
}
```
✔️ **Advantages:** Behavior changes with state.
❌ **Disadvantages:** Many state classes can clutter design.

---



---

