# State Design Pattern

## 🔹 What is it?

The **State Pattern** is a **behavioral design pattern** that lets an object **change its behavior when its internal state changes**, as if the object’s class changed at runtime.

It’s like implementing a **finite state machine (FSM)** where each state has its own behavior.

---

## 🔹 Why use it?

* When an object’s behavior depends on its current **state**, and it must change at runtime.
* To avoid huge `if-else` or `switch` statements for state handling.
* To encapsulate **state-specific logic** into separate classes.

---

## 🔹 How it works?

1. Define a **State interface** (declares behavior methods).
2. Implement **Concrete States** (each represents a state’s behavior).
3. Context holds a reference to the **current state** and delegates behavior to it.
4. State transitions happen by swapping the current state object.

---

## ✅ Example (Java)

```java
// State
interface OrderState {
    void handle(OrderContext context);
}

// Concrete States
class NewOrderState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Order placed. Moving to Processing state.");
        context.setState(new ProcessingState());
    }
}

class ProcessingState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Order is being processed. Moving to Shipped state.");
        context.setState(new ShippedState());
    }
}

class ShippedState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Order shipped. Moving to Delivered state.");
        context.setState(new DeliveredState());
    }
}

class DeliveredState implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Order already delivered. No further actions.");
    }
}

// Context
class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new NewOrderState(); // default
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextStep() {
        state.handle(this);
    }
}

// Client
public class StateDemo {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        order.nextStep(); // New -> Processing
        order.nextStep(); // Processing -> Shipped
        order.nextStep(); // Shipped -> Delivered
        order.nextStep(); // Delivered (end state)
    }
}
```

### Output:

```
Order placed. Moving to Processing state.
Order is being processed. Moving to Shipped state.
Order shipped. Moving to Delivered state.
Order already delivered. No further actions.
```

---

## 🔹 Pros

* ✅ Avoids messy `if-else` chains for state handling.
* ✅ Encapsulates state-specific behavior → cleaner code.
* ✅ Easy to add new states without touching existing logic.

---

## 🔹 Cons

* ❌ More classes (one per state).
* ❌ May feel like overkill for simple state machines.

---

## 🔹 Design Principle

* ✔️ Follows **Open/Closed Principle** → add new states easily.
* ✔️ Uses **composition over inheritance** → context delegates to state objects.

---

## 🔹 Analogy

Think of a **Traffic Light 🚦**:

* Red, Yellow, Green are **states**.
* Each state defines its own behavior (“stop”, “wait”, “go”).
* The traffic light (context) switches behavior by changing state.

---

✅ Real-world examples:

* Order lifecycle in e-commerce (New → Processing → Shipped → Delivered).
* Workflow engines (Draft → Review → Approved → Published).
* Game development (Character states: Idle, Running, Jumping, Attacking).
* ATM machine (Insert card, Enter PIN, Withdraw, Eject card).

---