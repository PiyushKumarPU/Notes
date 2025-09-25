# Bridge Design Pattern

## 🔹 What is it?

The **Bridge Pattern** is a **structural design pattern** that **decouples abstraction from implementation**, so that both can vary independently.

Instead of binding an abstraction tightly to a specific implementation, you “bridge” them via composition.

---

## 🔹 Why use it?

* When you want to **avoid a class explosion** caused by multiple dimensions of variation (e.g., Shape + Color).
* When you want to **switch implementations at runtime**.
* When abstraction and implementation should evolve **independently**.

---

## 🔹 How it works?

1. Define an **Abstraction** (high-level interface).
2. Define an **Implementation interface** (low-level operations).
3. Abstraction keeps a **reference to Implementation** (composition, not inheritance).
4. Client works with Abstraction, which delegates to Implementation.

---

## ✅ Example (Java)

```java
// Implementor (low-level interface)
interface Color {
    void applyColor();
}

// Concrete Implementors
class RedColor implements Color {
    public void applyColor() {
        System.out.println("Applying Red Color");
    }
}

class BlueColor implements Color {
    public void applyColor() {
        System.out.println("Applying Blue Color");
    }
}

// Abstraction
abstract class Shape {
    protected Color color;  // Bridge

    protected Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}

// Refined Abstraction
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Drawing Circle → ");
        color.applyColor();
    }
}

// Client
public class BridgeDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueCircle = new Circle(new BlueColor());

        redCircle.draw();
        blueCircle.draw();
    }
}
```

### Output:

```
Drawing Circle → Applying Red Color
Drawing Circle → Applying Blue Color
```

---

## 🔹 Pros

* ✅ Decouples abstraction from implementation → both can vary independently.
* ✅ Avoids class explosion (ShapeXColorXMaterial combinations).
* ✅ Implementation can be switched at runtime.

---

## 🔹 Cons

* ❌ Adds extra layers (slightly more complexity).
* ❌ Harder to design upfront if variations are unclear.

---

## 🔹 Design Principle

* ✔️ Follows **Open/Closed Principle** → add new shapes or new colors without touching existing code.
* ✔️ Uses **composition over inheritance**.

---

## 🔹 Analogy

Think of a **TV remote control** 📺:

* Remote = **Abstraction** (provides user operations).
* TV = **Implementation** (actual work is done here).
* Same remote can control different TVs (Sony, LG, Samsung).
* TV and Remote evolve independently, but work together via a **bridge**.

---

Great question 🙌 Real-world examples are the best way to “lock in” the **Bridge Pattern** in your head.
Here are some **practical, real-time examples** (from backend, system design, and daily tech):

---

# 🔥 Real-World Examples of Bridge Pattern

### 1. **JDBC (Java Database Connectivity)**

* **Abstraction** → `java.sql.Connection`, `Statement`, `ResultSet` (high-level API).
* **Implementation** → MySQL Driver, PostgreSQL Driver, Oracle Driver.
* The client code works with `Connection` without caring which DB driver is being used.
* You can switch from MySQL to PostgreSQL without changing client code → **bridge between abstraction (API) and implementation (driver)**.

---

### 2. **Messaging Systems (Email, SMS, Push Notifications)**

* **Abstraction** → `Notification` (send message).
* **Implementation** → `EmailSender`, `SMSSender`, `PushSender`.
* You can add new message channels without modifying the notification abstraction.
* Example in backend microservices → unified notification service.

---

### 3. **Payment Gateways**

* **Abstraction** → `Payment` (e.g., `pay(amount)`).
* **Implementation** → `StripeGateway`, `PayPalGateway`, `RazorpayGateway`.
* Your app calls `Payment.pay()`, while the implementation handles actual payment logic.
* You can switch providers at runtime.

---

### 4. **UI Theme + Platform (Desktop/Mobile/Web)**

* **Abstraction** → `UIComponent` (Button, Checkbox).
* **Implementation** → `LightTheme`, `DarkTheme`.
* Same `Button` abstraction can be drawn with different themes.
* Very common in **cross-platform GUI frameworks**.

---

### 5. **Logging Frameworks (SLF4J in Java)**

* **Abstraction** → `Logger` interface (`info()`, `error()`, `debug()`).
* **Implementation** → Log4j, Logback, JUL (Java Util Logging).
* You write `logger.info("msg")`, actual logging implementation can change underneath.

---

### 6. **Device Control (Remote + Device)**

* **Abstraction** → RemoteControl (operations like `powerOn()`, `powerOff()`).
* **Implementation** → TV, Radio, Projector.
* One abstraction (remote) works with many devices → decoupling hardware and control logic.

---