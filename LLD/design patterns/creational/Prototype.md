# Prototype Design Pattern

## 🔹 What is it?

The **Prototype Pattern** is a **creational design pattern** that allows you to **create new objects by cloning existing ones** instead of instantiating them with `new`.

This is useful when object creation is **expensive** (e.g., database fetch, network call, complex computation) or when you want to **decouple the code** from concrete class constructors.

---

## 🔹 Why use it?

* When creating an object is **costly** or **time-consuming**.
* When you need **many copies** of an object with only small variations.
* When you want to avoid **dependency on concrete class constructors**.

---

## 🔹 How it works?

1. Define a **Prototype interface** with a `clone()` method.
2. Each concrete class implements `clone()` and returns a copy of itself.
3. Instead of `new`, the client calls `.clone()` on an existing object.

---

## ✅ Example (Java)

```java
// Prototype interface
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

// Concrete Prototype
class Circle implements Shape {
    int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this.radius);  // shallow copy
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle with radius " + radius);
    }
}

// Client
public class PrototypeDemo {
    public static void main(String[] args) {
        Circle circle1 = new Circle(10);       // expensive object creation
        Circle circle2 = (Circle) circle1.clone();  // clone instead of new

        circle1.draw();
        circle2.draw();
    }
}
```

### Output:

```
Drawing Circle with radius 10
Drawing Circle with radius 10
```

---

## 🔹 Pros

* ✅ **Performance boost** → avoids expensive re-creation.
* ✅ **Simplifies object creation** → no need to know constructor details.
* ✅ **Decoupled client code** → just calls `clone()`.

---

## 🔹 Cons

* ❌ **Deep vs Shallow copy issues** → references may get shared unintentionally.
* ❌ Cloning objects with **complex state (files, DB, sockets)** is tricky.
* ❌ Not as intuitive as just using `new`.

---

## 🔹 Design Principle

* ❌ Breaks **Single Responsibility Principle (SRP)** → class is responsible for both its business logic and how it clones itself.
* ❌ Can violate **Liskov Substitution** if cloned object doesn’t behave exactly like the original.

---

## 🔹 Analogy (for fresher)

Think of **photocopying a document** 📄:

* `new` = writing the whole document from scratch.
* `clone()` = making a photocopy → much faster, same content.

---
