# Flyweight Design Pattern

## 🔹 What is it?

The **Flyweight Pattern** is a **structural design pattern** that is used to **minimize memory usage** by sharing as much data as possible between similar objects.

It separates object state into:

* **Intrinsic state** → shared & immutable (common across many objects).
* **Extrinsic state** → unique & passed in by client at runtime.

---

## 🔹 Why use it?

* When you have **a huge number of similar objects** (like game characters, text characters, GUI elements).
* To reduce **memory footprint** by sharing common parts.
* To improve performance when object creation is expensive.

---

## 🔹 How it works?

1. Identify **intrinsic state** (common, sharable).
2. Identify **extrinsic state** (unique per object).
3. Create a **Flyweight Factory** to manage shared objects.
4. Clients request flyweights from the factory instead of creating them directly.

---

## ✅ Example (Java)

```java
import java.util.*;

// Flyweight interface
interface Shape {
    void draw(int x, int y);  // extrinsic state: position
}

// Concrete Flyweight
class Circle implements Shape {
    private String color;  // intrinsic state

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing " + color + " circle at (" + x + ", " + y + ")");
    }
}

// Flyweight Factory
class ShapeFactory {
    private static final Map<String, Circle> circleMap = new HashMap<>();

    public static Circle getCircle(String color) {
        Circle circle = circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating a new " + color + " circle");
        }
        return circle;
    }
}

// Client
public class FlyweightDemo {
    public static void main(String[] args) {
        Shape red1 = ShapeFactory.getCircle("Red");
        red1.draw(10, 20);

        Shape red2 = ShapeFactory.getCircle("Red");
        red2.draw(30, 40);

        Shape blue = ShapeFactory.getCircle("Blue");
        blue.draw(50, 60);
    }
}
```

### Output:

```
Creating a new Red circle
Drawing Red circle at (10, 20)
Drawing Red circle at (30, 40)
Creating a new Blue circle
Drawing Blue circle at (50, 60)
```

Notice: **Red circle object was reused**, only created once.

---

## 🔹 Pros

* ✅ Huge **memory savings** when many similar objects are used.
* ✅ Centralized control over shared objects.
* ✅ Faster object creation (reuse instead of `new`).

---

## 🔹 Cons

* ❌ Complexity in separating intrinsic vs extrinsic state.
* ❌ Code readability suffers (client must supply extrinsic state).
* ❌ Can lead to **thread-safety issues** if flyweights are not immutable.

---

## 🔹 Design Principle

* ✔️ Uses **object sharing** to optimize performance.
* ✔️ Follows **Single Responsibility Principle** (flyweight handles intrinsic state, client supplies extrinsic state).

---

## 🔹 Analogy

Think of **fonts in a text editor** ✍️:

* You don’t create a new “Arial 12px” object for every character.
* Instead, one shared font object is reused across all characters.
* Only character position and content are different (extrinsic state).

---

# 🔥 Real-Time Examples of Flyweight Pattern

### 1. **Java String Pool**

* When you do:

  ```java
  String a = "hello";
  String b = "hello";
  ```
* Both `a` and `b` point to the same `"hello"` object in the **String pool**.
* Java doesn’t create new objects for identical strings → **shared intrinsic state**.

---

### 2. **Game Engines (e.g., characters, trees, bullets)**

* Imagine a video game with **10,000 trees** 🌳.
* Instead of creating 10,000 tree objects with the same color/texture, you store **one tree model (intrinsic)** and just vary position/size (extrinsic).
* This reduces memory drastically.

---

### 3. **Document Editors (Text Characters)**

* In MS Word or Google Docs:

  * Each letter (A, B, C…) doesn’t create a new font object.
  * Fonts, styles, and sizes are **shared objects**.
  * Only character value and position are different.

---

### 4. **Connection Pooling**

* In backend systems, database connections are **expensive** to create.
* Instead of making a new one each time, you reuse connections from a pool.
* The **connection object (intrinsic)** is shared, while **query details (extrinsic)** vary per request.

---

### 5. **Caching Systems**

* Think of **Redis or in-memory cache**.
* If many users request the same configuration data, you don’t load it again.
* The cached object is shared → reduces memory & CPU cost.

---

### 6. **GUI Widgets**

* Buttons, checkboxes, icons in a desktop app often share the same **style/theme object** (colors, borders).
* Only their position/label differ → classic Flyweight.

---

### 7. **Map Rendering (Google Maps)** 🗺️

* A city map may show **thousands of identical houses/buildings**.
* Instead of loading separate images, it reuses the same base icon, only changing coordinates.

---

✅ **Key Idea in all examples:**

* **Shared (intrinsic):** Common, heavy, reused data (fonts, textures, DB connections).
* **Unique (extrinsic):** Varies per use (position, query, text).

---
