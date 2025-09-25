
# 🏗️ Creational Design Patterns — Summary

---

## 1. **Singleton**

**Goal:** Ensure only one instance of a class exists & provide global access.
**Use case:** Logging, configuration, DB connection pool.

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

✅ Real-world: `Runtime.getRuntime()` in Java, Spring beans (by default).

---

## 2. **Factory Method**

**Goal:** Define an interface for creating objects, but let subclasses decide which class to instantiate.
**Use case:** When the client shouldn’t depend on concrete classes.

```java
interface Shape { void draw(); }
class Circle implements Shape { public void draw() {} }
class ShapeFactory {
  Shape getShape(String type) {
    return type.equals("circle") ? new Circle() : null;
  }
}
```

✅ Real-world: Java `Calendar.getInstance()`, Spring FactoryBeans.

---

## 3. **Abstract Factory**

**Goal:** Create **families of related objects** without specifying concrete classes.
**Use case:** Cross-platform UI (Dark vs Light themes).

```java
interface UIFactory { Button createBtn(); Checkbox createChk(); }
class DarkFactory implements UIFactory { ... }
class LightFactory implements UIFactory { ... }
```

✅ Real-world: GUI frameworks (Swing, AWT), dependency injection.

---

## 4. **Prototype**

**Goal:** Create new objects by **cloning existing ones**.
**Use case:** When creating an object is costly, or you need many copies.

```java
interface Shape extends Cloneable { Shape clone(); }
class Circle implements Shape {
  int r; Circle(int r){this.r=r;}
  public Shape clone(){ return new Circle(r); }
}
```

✅ Real-world: Java `Object.clone()`, game engines (cloning entities).

---

## 5. **Builder**

**Goal:** Construct complex objects **step by step**.
**Use case:** Objects with many optional parameters (avoid telescoping constructors).

```java
User user = new User.UserBuilder()
                 .setName("John")
                 .setAge(30)
                 .build();
```

✅ Real-world: `StringBuilder`, Lombok’s `@Builder`, SQL query builders.

---

# 🔥 Quick Way to Remember

* **Singleton** → Only **one instance** (global).
* **Factory Method** → Creates **one object**, subclasses decide which.
* **Abstract Factory** → Creates a **family of related objects**.
* **Prototype** → **Copy** existing object instead of `new`.
* **Builder** → Build **complex object step by step**.

---