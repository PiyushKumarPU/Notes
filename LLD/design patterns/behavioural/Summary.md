# 🎭 Behavioral Design Patterns — Summary
---

## 1. **Observer**

**Goal:** One-to-many dependency → subject notifies observers when state changes.
**Use case:** Event-driven systems, pub/sub, GUIs.

```java
agency.addObserver(cnn);
agency.notifyObservers("Breaking News!");
```

✅ Real-world: Kafka, RabbitMQ, Spring Events, YouTube subscriptions.

---

## 2. **Strategy**

**Goal:** Define a family of algorithms, make them interchangeable at runtime.
**Use case:** Payments, sorting, authentication strategies.

```java
context.setPaymentStrategy(new UpiPayment("id@upi"));
context.checkout(200);
```

✅ Real-world: Payment gateways, Spring Security auth providers, sorting libraries.

---

## 3. **Command**

**Goal:** Encapsulate a request as an object → supports undo/redo, queues.
**Use case:** GUI actions, task schedulers, job queues.

```java
remote.setCommand(new LightOnCommand(light));
remote.pressButton();
```

✅ Real-world: Task queues, DB transactions, logging/audit trails.

---

## 4. **State**

**Goal:** Allow object to change behavior when internal state changes.
**Use case:** Order lifecycle, workflows, finite state machines.

```java
order.nextStep(); // New → Processing → Shipped → Delivered
```

✅ Real-world: Order state in e-commerce, ATM states, workflow engines.

---

## 5. **Template Method**

**Goal:** Define algorithm skeleton in base class, subclasses fill in steps.
**Use case:** Framework hooks, data processing pipelines.

```java
DataProcessor csv = new CSVDataProcessor();
csv.process();
```

✅ Real-world: Spring `JdbcTemplate`, Servlets (`doGet`, `doPost`), JUnit test runners.

---

## 6. **Chain of Responsibility**

**Goal:** Pass request along a chain of handlers until one handles it.
**Use case:** Logging, middleware, approval flows.

```java
info.setNext(debug);
debug.setNext(error);
info.log("ERROR", "Something failed");
```

✅ Real-world: Logging frameworks, servlet filters, Express.js middleware, approval pipelines.

---

# 🔥 Quick Way to Remember

* **Observer** → “I’ll notify all my subscribers.”
* **Strategy** → “Pick an algorithm at runtime.”
* **Command** → “Wrap a request as an object.”
* **State** → “Behavior changes with state.”
* **Template Method** → “Skeleton fixed, steps customizable.”
* **Chain of Responsibility** → “Pass it along the chain until handled.”

---
