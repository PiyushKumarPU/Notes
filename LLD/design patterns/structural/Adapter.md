# Adapter Design Pattern

## 🔹 What is it?

The **Adapter Pattern** is a **structural design pattern** that allows **incompatible interfaces to work together**.
It acts as a **bridge** between an existing class (legacy/third-party) and the client code that expects a different interface.

---

## 🔹 Why use it?

* When you want to **reuse an existing class** but its interface is not compatible with the code.
* When integrating **third-party libraries** that have different method signatures.
* To provide a **uniform API** over multiple incompatible implementations.

---

## 🔹 How it works?

1. Define a **Target interface** (what client expects).
2. Create an **Adapter** class that implements Target.
3. Inside Adapter, **wrap/translate calls** to the **Adaptee** (incompatible class).

---

## ✅ Example (Java)

```java
// Target interface (what client expects)
interface MediaPlayer {
    void play(String fileName);
}

// Adaptee (incompatible existing class)
class LegacyAudioPlayer {
    public void playAudioFile(String file) {
        System.out.println("Playing audio file: " + file);
    }
}

// Adapter (bridge between Target and Adaptee)
class AudioAdapter implements MediaPlayer {
    private LegacyAudioPlayer legacyPlayer;

    public AudioAdapter(LegacyAudioPlayer legacyPlayer) {
        this.legacyPlayer = legacyPlayer;
    }

    @Override
    public void play(String fileName) {
        legacyPlayer.playAudioFile(fileName);  // translate call
    }
}

// Client
public class AdapterDemo {
    public static void main(String[] args) {
        MediaPlayer player = new AudioAdapter(new LegacyAudioPlayer());
        player.play("song.mp3");   // Client uses Target interface
    }
}
```

### Output:

```
Playing audio file: song.mp3
```

---

## 🔹 Pros

* ✅ Reuses legacy or third-party code without modification.
* ✅ Makes systems **interoperable**.
* ✅ Improves flexibility and maintainability.

---

## 🔹 Cons

* ❌ Adds an extra layer → slightly more complexity.
* ❌ If overused, leads to too many small adapter classes.

---

## 🔹 Design Principle

* ✔️ Follows **Open/Closed Principle** → we can extend functionality without modifying existing classes.
* ✔️ Promotes **code reusability** by wrapping existing logic.

---

## 🔹 Analogy 

Think of a **travel plug adapter** 🔌:

* Your laptop charger plug (US type) doesn’t fit the wall socket (EU type).
* Instead of replacing the charger, you use an **adapter** to bridge the gap.
* Both devices work without being modified.

---
