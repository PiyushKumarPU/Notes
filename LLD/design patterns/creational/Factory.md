# Factory Design Pattern
## 🔹 What is Factory Method?
The **Factory Method** pattern defines an interface for creating an object, but lets **subclasses decide which class to instantiate**.  
Instead of using `new` everywhere, you delegate object creation to a factory method.  

**Key Idea:**  
Encapsulate object creation logic → clients only depend on abstractions, not concrete classes.

---

## 🔹 Why do we use Factory Method?
- To avoid **tight coupling** with specific implementations.  
- To follow the **Open/Closed Principle (OCP)** → we can introduce new product types without modifying client code.  
- To centralize and **standardize object creation logic**.  

**Example in backend:**  
Payment processing system.  
Instead of doing:
```java
PaymentGateway gateway = new RazorpayGateway();
```

## ✅ Pros (Benefits)

- **Loose coupling**  
  Client code depends on interface/abstract class, not concrete classes.

- **Single Responsibility Principle (SRP)**  
  Object creation logic is isolated in factories.

- **Open/Closed Principle (OCP)**  
  Easily add new product types without changing core logic.

- **Consistency**  
  Centralized way to build objects ensures uniform setup/configuration.

---

## ❌ Cons (Drawbacks)

- **Class explosion**  
  You may end up with too many factory subclasses if each variation has its own factory.

- **Complexity overhead**  
  For simple cases, factories can feel like over-engineering.

- **Indirection**  
  Debugging can be harder since creation logic is abstracted away.

---

# 🔹 What Design Principles are Involved?

- ✅ **Single Responsibility Principle (SRP)**  
  Creation logic is separated from usage logic.

- ✅ **Open/Closed Principle (OCP)**  
  System is open for extension (new products) but closed for modification.

- ✅ **Dependency Inversion Principle (DIP)**  
  Client code depends on abstractions (interfaces) not concretions.

---

# 🔹 When to Use Factory Method (Pragmatic Advice)

- ✅ When object creation logic is **complex** or varies depending on conditions.  
- ✅ When you want to **decouple client code** from concrete implementations.  
- ✅ When **new product types** will be added frequently.  

- ❌ Avoid if your system has only **one or two simple object types**  
  (extra factory adds no value).

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