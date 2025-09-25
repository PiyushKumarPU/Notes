# Command Design Pattern

## 🔹 What is it?

The **Command Pattern** is a **behavioral design pattern** that **encapsulates a request as an object**, allowing you to:

* Parameterize methods with different requests.
* Queue requests, log them, or support undo/redo operations.

It separates the **object that invokes an operation** from the **one that knows how to perform it**.

---

## 🔹 Why use it?

* To decouple the **invoker** (who triggers the action) from the **receiver** (who performs the action).
* To support **undo/redo** functionality.
* To implement **task scheduling, job queues, transaction logs**.

---

## 🔹 How it works?

1. Define a **Command interface** with an `execute()` method.
2. Implement **Concrete Commands** (wrap requests to receivers).
3. Create an **Invoker** that stores/executes commands.
4. Client creates commands and passes them to the Invoker.

---

## ✅ Example (Java)

```java
// Command
interface Command {
    void execute();
}

// Receiver
class Light {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}

// Concrete Commands
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
}

class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
}

// Invoker
class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton() { command.execute(); }
}

// Client
public class CommandDemo {
    public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
```

### Output:

```
Light is ON
Light is OFF
```

---

## 🔹 Pros

* ✅ Decouples sender (Invoker) from receiver.
* ✅ Supports undo/redo (store command history).
* ✅ Easy to implement task queues, logging, transactions.
* ✅ Open/Closed → add new commands without changing existing code.

---

## 🔹 Cons

* ❌ More classes (each action = one Command class).
* ❌ Can add complexity for simple operations.

---

## 🔹 Design Principle

* ✔️ Follows **Single Responsibility Principle** → Command handles request, Receiver executes it.
* ✔️ Supports **Open/Closed Principle** → add new commands without modifying Invoker.

---

## 🔹 Analogy

Think of a **restaurant order system** 🍽️:

* **Customer (Client)** places order → creates Command.
* **Waiter (Invoker)** takes the order → stores and forwards.
* **Chef (Receiver)** executes the order.
* Order (Command) can be queued, canceled, or redone.

---

✅ Real-world examples:

* GUI buttons (each button click = command).
* Task/job queues (like Quartz, Celery).
* Logging & audit trails (replay commands).
* Database transactions (undo/redo).

---