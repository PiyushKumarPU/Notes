# 🧱 Structural Design Patterns - Summary

---

## 1. **Adapter**

**Goal:** Convert one interface into another (bridge between incompatible systems).
**Use case:** Integrating legacy/third-party APIs.

```java
MediaPlayer player = new AudioAdapter(new LegacyAudioPlayer());
player.play("song.mp3");
```

✅ Real-world: Travel plug adapter, Spring’s `AdapterPattern` in MVC.

---

## 2. **Bridge**

**Goal:** Decouple abstraction from implementation so both can vary independently.
**Use case:** Avoid class explosion when two dimensions vary (e.g., Shape × Color).

```java
Shape circle = new Circle(new RedColor());
circle.draw();
```

✅ Real-world: JDBC (API vs DB driver), logging frameworks.

---

## 3. **Composite**

**Goal:** Treat individual objects and groups uniformly (tree structures).
**Use case:** File system, organization hierarchies, menus.

```java
Directory root = new Directory("Root");
root.add(new File("resume.pdf"));
root.showDetails();
```

✅ Real-world: DOM trees, company org charts.

---

## 4. **Decorator**

**Goal:** Dynamically add responsibilities to objects without modifying class.
**Use case:** Flexible add-ons (coffee shop, UI elements).

```java
Coffee coffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
```

✅ Real-world: Java I/O streams, Spring Boot filters.

---

## 5. **Facade**

**Goal:** Provide a simplified interface to a complex subsystem.
**Use case:** API gateways, SDK wrappers, computer startup.

```java
ComputerFacade pc = new ComputerFacade();
pc.startComputer();
```

✅ Real-world: Spring’s `JdbcTemplate`, Hibernate, hotel reception.

---

## 6. **Flyweight**

**Goal:** Share common state to reduce memory usage.
**Use case:** Large number of similar objects.

```java
Shape red1 = ShapeFactory.getCircle("Red");  // reused
```

✅ Real-world: Java String pool, game engines (trees, bullets), caches.

---

## 7. **Proxy**

**Goal:** Provide a placeholder/surrogate to control access to real object.
**Use case:** Lazy loading, access control, caching, remote calls.

```java
Image img = new ProxyImage("photo.png");
img.display(); // loads only once
```

✅ Real-world: Spring AOP proxies, Hibernate lazy loading, Nginx reverse proxy.

---

# 🔥 Quick Way to Remember

* **Adapter** → “I don’t match, convert me.”
* **Bridge** → “Two dimensions, let’s decouple.”
* **Composite** → “Part-whole tree structure.”
* **Decorator** → “Wrap me with extra stuff.”
* **Facade** → “Here’s one simple entry point.”
* **Flyweight** → “Share heavy objects.”
* **Proxy** → “I’ll stand in between you and the real thing.”

---
