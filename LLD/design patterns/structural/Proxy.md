# Proxy Design Pattern

## 🔹 What is it?

The **Proxy Pattern** is a **structural design pattern** that provides a **surrogate (placeholder) object** that controls access to another object.
Instead of calling the real object directly, the client talks to the **proxy**, which then decides whether/how to forward the request to the real object.

---

## 🔹 Why use it?

* To control access to an object (e.g., security, permissions).
* To add additional behavior like caching, logging, or lazy initialization.
* To hide the complexity of remote or expensive operations (e.g., network calls).

---

## 🔹 How it works?

1. Define a **Subject interface** (common contract).
2. Create a **RealSubject** (the actual object doing the work).
3. Create a **Proxy** that implements Subject, holds a reference to RealSubject, and controls access to it.
4. Client interacts with Proxy instead of RealSubject.

---

## ✅ Example (Java)

```java
// Subject
interface Image {
    void display();
}

// RealSubject
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();  // expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);  // lazy loading
        }
        realImage.display();
    }
}

// Client
public class ProxyDemo {
    public static void main(String[] args) {
        Image img = new ProxyImage("photo.png");

        // image is loaded only when needed
        img.display();
        System.out.println("---");

        // second time, no loading (reuses object)
        img.display();
    }
}
```

### Output:

```
Loading photo.png
Displaying photo.png
---
Displaying photo.png
```

---

## 🔹 Pros

* ✅ Adds control over object access.
* ✅ Supports **lazy loading** → load heavy objects only when needed.
* ✅ Can add extra behavior (caching, logging, security checks).
* ✅ Hides complexity of remote objects (remote proxies).

---

## 🔹 Cons

* ❌ Adds extra layer of indirection (slight overhead).
* ❌ If overused, can make design harder to follow (too many proxy wrappers).

---

## 🔹 Design Principle

* ✔️ Uses **composition over inheritance**.
* ✔️ Follows **Single Responsibility Principle** → Proxy handles access, RealSubject handles business logic.

---

## 🔹 Analogy

Think of a **Credit Card** 💳:

* Instead of carrying **cash (RealSubject)**, you use a **proxy (credit card)**.
* The card doesn’t hold money itself; it just **controls access** to your bank account.

---

## 🔥 Real-World Examples

* **Spring AOP Proxies** → adding logging, transactions, security around methods.
* **Hibernate Lazy Loading** → proxy objects represent entities; actual DB fetch happens when accessed.
* **Reverse Proxy (Nginx, HAProxy)** → client requests go through proxy server before hitting backend servers.
* **API Gateways** → act as proxies for multiple microservices.

---