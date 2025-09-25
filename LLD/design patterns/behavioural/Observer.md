# Observer Design Pattern

## 🔹 What is it?

The **Observer Pattern** is a **behavioral design pattern** that defines a **one-to-many dependency** between objects:

* When one object (**Subject**) changes its state,
* All dependent objects (**Observers**) are automatically notified.

This is also known as **Publish-Subscribe** (Pub/Sub).

---

## 🔹 Why use it?

* To build **event-driven systems**.
* When multiple objects need to react to state changes of another object.
* To decouple **event producers (subjects)** from **event consumers (observers)**.

---

## 🔹 How it works?

1. Define a **Subject** that maintains a list of observers and notifies them of changes.
2. Define an **Observer interface** with an `update()` method.
3. Observers register/unregister themselves with the Subject.
4. When Subject state changes, it calls `update()` on all observers.

---

## ✅ Example (Java)

```java
import java.util.*;

// Observer
interface Observer {
    void update(String message);
}

// Subject
class NewsAgency {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(String news) {
        for (Observer o : observers) {
            o.update(news);
        }
    }
}

// Concrete Observers
class NewsChannel implements Observer {
    private String name;
    public NewsChannel(String name) { this.name = name; }

    @Override
    public void update(String message) {
        System.out.println(name + " received news: " + message);
    }
}

// Client
public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        Observer cnn = new NewsChannel("CNN");
        Observer bbc = new NewsChannel("BBC");

        agency.addObserver(cnn);
        agency.addObserver(bbc);

        agency.notifyObservers("New Policy Announced!");
        agency.notifyObservers("Stock Market Hits Record High!");
    }
}
```

### Output:

```
CNN received news: New Policy Announced!
BBC received news: New Policy Announced!
CNN received news: Stock Market Hits Record High!
BBC received news: Stock Market Hits Record High!
```

---

## 🔹 Pros

* ✅ Decouples Subject and Observers (loosely coupled).
* ✅ Supports dynamic subscription/unsubscription.
* ✅ Used everywhere (GUIs, event buses, messaging systems).

---

## 🔹 Cons

* ❌ Notification overhead if there are many observers.
* ❌ Can lead to unexpected updates if dependencies are not managed well.
* ❌ Debugging harder in event-driven flow (who triggered what?).

---

## 🔹 Design Principle

* ✔️ Follows **Open/Closed Principle** → add new observers without changing subject.
* ✔️ Promotes **loose coupling** between event producers & consumers.

---

## 🔹 Analogy (for fresher)

Think of a **YouTube Channel** 📺:

* **Channel (Subject)** → publishes videos.
* **Subscribers (Observers)** → automatically get notified.
* Channel doesn’t know the details of subscribers; it just notifies all.

---

✅ Real-world examples:

* Java’s `Observer` & `Observable` (deprecated but classic).
* Event Listeners in GUIs (Swing, JavaFX).
* Pub/Sub systems like **Kafka, RabbitMQ**.
* Spring `ApplicationEventPublisher` & `@EventListener`.

---