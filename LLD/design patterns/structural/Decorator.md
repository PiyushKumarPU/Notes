# Decorator Design Pattern

## 🔹 What is it?

The **Decorator Pattern** is a **structural design pattern** that allows you to **dynamically add new behavior or responsibilities to objects at runtime** without modifying their class.

It wraps an object inside another object (the decorator), which **adds extra functionality**.

---

## 🔹 Why use it?

* To add features to objects **without modifying existing code** (avoids subclass explosion).
* When you want to follow **Open/Closed Principle** → extend behavior without changing the base class.
* To apply **different combinations of responsibilities dynamically**.

---

## 🔹 How it works?

1. Define a **Component interface** (base contract).
2. Concrete classes implement the base functionality.
3. Create a **Decorator class** that implements Component and wraps another Component.
4. Add extra behavior in the decorator before/after delegating to the wrapped object.

---

## ✅ Example (Java)

```java
// Component
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
    public double getCost() {
        return 5.0;
    }
}

// Decorator
class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
    public double getCost() {
        return super.getCost() + 2.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
    public double getCost() {
        return super.getCost() + 1.0;
    }
}

// Client
public class DecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " → $" + coffee.getCost());

        // Add milk
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " → $" + coffee.getCost());

        // Add sugar
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " → $" + coffee.getCost());
    }
}
```

### Output:

```
Simple Coffee → $5.0
Simple Coffee, Milk → $7.0
Simple Coffee, Milk, Sugar → $8.0
```

---

## 🔹 Pros

* ✅ Add responsibilities **at runtime**.
* ✅ Avoids class explosion (no need to create `MilkCoffee`, `SugarCoffee`, `MilkSugarCoffee`).
* ✅ Follows **Open/Closed Principle**.

---

## 🔹 Cons

* ❌ Too many small classes (each decorator is a new class).
* ❌ Can make debugging harder (lots of wrapping).

---

## 🔹 Design Principle

* ✔️ Uses **composition over inheritance**.
* ✔️ Follows **Open/Closed Principle** → behavior is extended without modifying classes.

---

## 🔹 Analogy

Think of **Toppings on a Pizza 🍕 or Add-ons in Coffee ☕**:

* Start with a base (plain pizza/coffee).
* Add cheese, olives, or milk, sugar → each add-on is a **decorator**.
* You don’t change the pizza recipe itself; you **wrap it with extra features**.

---

