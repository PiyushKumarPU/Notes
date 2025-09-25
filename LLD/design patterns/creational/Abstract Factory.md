# Abstract Factory Design Pattern

## 🔹 What is Abstract Factory?
The **Abstract Factory** pattern provides an interface to create **families of related objects** without specifying their concrete classes.  

Think of it as a **factory of factories** – a higher-level abstraction compared to Factory Method.  

**Key Idea:**  
Ensure that related products (objects) are created together, keeping consistency across the system.

---
## Abstract Factory Design Pattern Explanation

## 🔹 Easy Real-Life Analogy
**Imagine you’re ordering food at a restaurant.**

The restaurant offers **two cuisines**:  
- **Indian Cuisine** → Curry + Naan  
- **Italian Cuisine** → Pasta + Garlic Bread  

When you pick a cuisine, you get a **matching combo** of dishes.  
👉 You’ll never accidentally mix *Indian Curry* with *Italian Garlic Bread* in a combo.  

That’s exactly what **Abstract Factory** does in programming:  
- It gives you a **family of related objects** together.  
- Ensures consistency, so you don’t mix products from different families.  

---

## 🔹 Code Example: Restaurant Combo

```java
// Product interfaces
interface MainCourse { void serve(); }
interface Bread { void serve(); }

// Indian family
class Curry implements MainCourse {
    public void serve() { System.out.println("Serving Curry"); }
}
class Naan implements Bread {
    public void serve() { System.out.println("Serving Naan"); }
}

// Italian family
class Pasta implements MainCourse {
    public void serve() { System.out.println("Serving Pasta"); }
}
class GarlicBread implements Bread {
    public void serve() { System.out.println("Serving Garlic Bread"); }
}

// Abstract Factory
interface CuisineFactory {
    MainCourse createMainCourse();
    Bread createBread();
}

// Concrete Factories
class IndianCuisineFactory implements CuisineFactory {
    public MainCourse createMainCourse() { return new Curry(); }
    public Bread createBread() { return new Naan(); }
}

class ItalianCuisineFactory implements CuisineFactory {
    public MainCourse createMainCourse() { return new Pasta(); }
    public Bread createBread() { return new GarlicBread(); }
}

// Client
public class Restaurant {
    public static void main(String[] args) {
        // Choose one cuisine family
        CuisineFactory factory = new IndianCuisineFactory();

        MainCourse main = factory.createMainCourse();
        Bread bread = factory.createBread();

        main.serve();   // Serving Curry
        bread.serve();  // Serving Naan
    }
}

---

## 🔹 Why do we use Abstract Factory?
- When a system needs to work with **multiple families of products**, and you must ensure **compatibility** within each family.  
- To avoid **mixing objects from different product families** accidentally.  
- To make the system easily extensible to new families without breaking existing code.  

**Example in backend:**  
Cloud service integrations:  
- AWS → S3 (Storage), EC2 (Compute), SQS (Queue)  
- GCP → GCS, GCE, Pub/Sub  
- Azure → Blob Storage, VM, Service Bus  

Instead of creating them directly, you use:  
```java
CloudFactory factory = new AwsCloudFactory();
Storage storage = factory.createStorage();
Compute compute = factory.createCompute();
Queue queue = factory.createQueue();
```

---

## ✅ Pros (Benefits)

- **Consistency across products**  
  All related objects are created together and match each other.

- **Decoupling**  
  Client code works only with abstract factories and product interfaces.

- **Open/Closed Principle (OCP)**  
  Add new product families (e.g., Alibaba Cloud) without modifying existing code.

- **Encapsulation of creation logic**  
  Complex creation logic stays hidden inside factories.

---

## ❌ Cons (Drawbacks)

- **Complexity**  
  Adds another layer of abstraction (factories of factories).

- **Class explosion**  
  Many interfaces and factory classes to maintain.

- **Difficult to support new product variations**  
  If you add a new type of product (e.g., Monitoring service), all factories must implement it.

---

# 🔹 What Design Principles are Involved?

- ✅ **Single Responsibility Principle (SRP)**  
  Each factory handles creation of related products.

- ✅ **Open/Closed Principle (OCP)**  
  New families can be added without changing client code.

- ✅ **Dependency Inversion Principle (DIP)**  
  Client depends on abstractions (interfaces) instead of concrete classes.

---

# 🔹 When to Use Abstract Factory (Pragmatic Advice)

- ✅ When your system must work with **families of related products**  
  (e.g., UI themes, cloud providers, DB drivers).

- ✅ When you want to **ensure compatibility** between products in the same family.

- ✅ When **product families are expected to grow** in the future.

- ❌ Avoid for **small/simple systems** → may introduce unnecessary boilerplate.

### Step 1: Define product interfaces
```java
// Products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}
```

### Step 2: Create concrete products
```java
// Dark theme products
class DarkButton implements Button {
    public void render() { System.out.println("Dark Button"); }
}
class DarkCheckbox implements Checkbox {
    public void render() { System.out.println("Dark Checkbox"); }
}

// Light theme products
class LightButton implements Button {
    public void render() { System.out.println("Light Button"); }
}
class LightCheckbox implements Checkbox {
    public void render() { System.out.println("Light Checkbox"); }
}

```

### Step 3: Abstract Factory
```java
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

```

### Step 4: Concrete Factories
```java
class DarkThemeFactory implements UIFactory {
    public Button createButton() { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}

class LightThemeFactory implements UIFactory {
    public Button createButton() { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
}

```

### Step 5: Client code
```java
public class Demo {
    public static void main(String[] args) {
        // Suppose user selects Dark theme
        UIFactory factory = new DarkThemeFactory();

        Button btn = factory.createButton();
        Checkbox chk = factory.createCheckbox();

        btn.render();   // Dark Button
        chk.render();   // Dark Checkbox
    }
}

```

# ✅ Why this is Abstract Factory?

- **UIFactory** = Abstract Factory  
- **DarkThemeFactory & LightThemeFactory** = Concrete Factories  
- **Button + Checkbox** = Products  

👉 The client code (`Demo`) doesn’t know about `new DarkButton()` or `new LightCheckbox()`.  
It just asks the **factory** for a **family of products**.

---

## 💡 Quick way to remember:

- **Factory Method** → creates **one object**.  
- **Abstract Factory** → creates a **family of related objects**.  
