# Strategy Design Pattern

## 🔹 What is it?

The **Strategy Pattern** is a **behavioral design pattern** that lets you **define a family of algorithms**, encapsulate each one, and make them **interchangeable at runtime**.

Instead of hardcoding an algorithm in a class, you **inject** the desired behavior.

---

## 🔹 Why use it?

* To switch between multiple algorithms without changing client code.
* To follow **Open/Closed Principle** → add new strategies without modifying existing logic.
* To avoid messy `if-else` or `switch` statements when choosing behavior.

---

## 🔹 How it works?

1. Define a **Strategy interface** (common behavior).
2. Implement multiple **Concrete Strategies**.
3. Create a **Context class** that uses a Strategy.
4. Client selects which strategy to use at runtime.

---

## ✅ Example (Java)

```java
// Strategy
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}

class UpiPayment implements PaymentStrategy {
    private String upiId;
    public UpiPayment(String upiId) { this.upiId = upiId; }
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI: " + upiId);
    }
}

// Context
class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(int amount) {
        strategy.pay(amount);
    }
}

// Client
public class StrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876"));
        context.checkout(100);

        context.setPaymentStrategy(new UpiPayment("john@upi"));
        context.checkout(200);
    }
}
```

### Output:

```
Paid 100 using Credit Card: 1234-5678-9876
Paid 200 using UPI: john@upi
```

---

## 🔹 Pros

* ✅ Avoids bloated classes with multiple algorithms.
* ✅ Easy to add new strategies without changing client.
* ✅ Promotes **dependency injection** and **polymorphism**.

---

## 🔹 Cons

* ❌ More classes to maintain (each strategy is a class).
* ❌ Client must know which strategy to pick.

---

## 🔹 Design Principle

* ✔️ Follows **Open/Closed Principle** (add strategies without modifying context).
* ✔️ Uses **composition over inheritance** (context delegates work to strategy).

---

## 🔹 Analogy (for fresher)

Think of a **Navigation App (Google Maps)** 🗺️:

* You want to go from A → B.
* **Strategy** = route type (Car, Bike, Walking, Public Transport).
* You can switch route strategy at runtime, but the overall process (navigate) stays the same.

---

✅ Real-world examples:

* Payment gateways (CreditCard, UPI, Wallet).
* Sorting algorithms (QuickSort, MergeSort, HeapSort).
* Compression algorithms (Zip, Rar, GZip).
* Authentication strategies (OAuth, JWT, SAML).

---
