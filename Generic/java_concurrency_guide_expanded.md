## ✅ Java Concurrency Deep-Dive Cheat Sheet — Fully Expanded

## 🧩 1️⃣ What is a Thread?

A **thread** is the smallest unit of execution within a process.

Every Java application runs at least one thread — the **main thread** — created by the JVM at startup.

---

### 🔍 How it works

* A **process** has its own memory space.
* Threads within the same process **share memory** and resources.
* Threads run **independently**, but can coordinate via shared variables, locks, or other concurrency utilities.

### ✅ Common Use Cases

* Running tasks in **parallel** (e.g., background processing, file I/O).
* Keeping applications responsive (e.g., GUI, servers).
* Handling multiple tasks simultaneously.

---

## ⚙️ Examples: Creating Threads

### 1️⃣ Extending `Thread`

```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        System.out.println("Hello from main: " + Thread.currentThread().getName());
    }
}
```

### 2️⃣ Implementing `Runnable`

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();

        System.out.println("Hello from main: " + Thread.currentThread().getName());
    }
}
```

### 3️⃣ Using `ExecutorService` (Recommended)

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> System.out.println("Task 1 - " + Thread.currentThread().getName()));
        executor.submit(() -> System.out.println("Task 2 - " + Thread.currentThread().getName()));

        executor.shutdown();
    }
}
```

---

## ⚠️ Pitfalls

* **Too many threads:** Each thread consumes stack memory and CPU time → excessive context switching.
* **Race conditions:** Threads may modify shared data inconsistently if not synchronized.
* **Deadlocks:** Poor locking order can lead to threads waiting forever.

---

## 🛡️ Best Practices

* Use `ExecutorService` instead of raw threads for better resource management.
* Synchronize access to shared data (`synchronized`, `ReentrantLock`, `Atomic*` classes).
* Use **thread-safe collections** like `ConcurrentHashMap`.
* Limit the number of threads — use **thread pools** or reactive frameworks.
* Name threads for easier debugging.

---

## 📌 Key APIs

* `Thread`
* `Runnable`
* `Callable`
* `ExecutorService`
* `Future`

---

## ✅ Summary

A **thread** is a lightweight sub-process for concurrent execution. It enables multitasking within a single process but requires careful **synchronization** to avoid issues like race conditions and deadlocks.

**Rule:** *Always prefer managed thread pools and concurrency utilities over raw threads for production systems.*


### 🧩 2️⃣ How do you create Threads?

✅ Thread class: `new Thread(runnable).start()`\
✅ ExecutorService: Recommended for production.

```java
ExecutorService ex = Executors.newFixedThreadPool(10);
ex.submit(() -> { /* work */ });
ex.shutdown();
```

**Pitfalls:** Forgetting to shut down ExecutorService → resource leaks. Using new Thread for each task → unbounded threads.

---

### 🧩 3️⃣ Runnable vs Callable

✅ Runnable: no return value, no checked exception.\
✅ Callable: returns value, can throw checked exception.

```java
Future<Integer> f = ex.submit(() -> 42);
```

**Pitfalls:** Blocking on `future.get()` without timeout → possible deadlock.\
**Best practice:** Always use timeouts with `Future.get()`.

---

### 🧩 4️⃣ Thread Lifecycle

States: NEW, RUNNABLE, BLOCKED, WAITING, TIMED\_WAITING, TERMINATED.\
**Pitfalls:** Misunderstanding states leads to wrong assumptions.\
**Tip:** Use `Thread.getState()` for debugging stuck threads.

---

### 🧩 5️⃣ Synchronization

✅ `synchronized` ensures mutual exclusion.

```java
synchronized(this) { /* critical section */ }
```

**Pitfalls:** Lock granularity too coarse → reduces concurrency. Nested locks → deadlocks.\
**Best practice:** Keep synchronized blocks minimal.

---

### 🧩 6️⃣ ReentrantLock vs synchronized

✅ ReentrantLock supports tryLock(), fairness policy, interruptible locks.

```java
Lock lock = new ReentrantLock();
if (lock.tryLock()) {
  try { /* work */ } finally { lock.unlock(); }
}
```

**Pitfalls:** Forgetting `unlock()` → deadlocks. Overhead vs `synchronized` for simple needs.\
**Best practice:** Always `unlock()` in `finally`.

---

### 🧩 7️⃣ volatile

✅ Guarantees visibility of changes to variables across threads.\
**Pitfalls:** Doesn’t guarantee atomicity for compound actions (`x++` is unsafe!).\
**Use for:** Stop flags, status indicators.\
**Tip:** Use `Atomic` classes for atomicity.

---

### 🧩 8️⃣ wait()/notify()

✅ For low-level thread coordination on object monitors.

```java
synchronized(obj) {
  while(!condition) obj.wait();
  // work
  obj.notifyAll();
}
```

**Pitfalls:** Missed notifications → stuck threads. Using `notify()` instead of `notifyAll()` → possible missed wake-ups.\
**Best practice:** Always use `while` (not `if`) to re-check condition after wake-up.

### 🧩 9️⃣ Deadlock

✅ A **deadlock** occurs when threads wait on each other’s locks in a circular chain, blocking forever.

### ⚙️ Example

```java
Lock lock1 = new ReentrantLock();
Lock lock2 = new ReentrantLock();

Thread t1 = new Thread(() -> {
    lock1.lock();
    try {
        Thread.sleep(50);
        lock2.lock();
        try {
            // do work
        } finally { lock2.unlock(); }
    } catch (InterruptedException ignored) {}
    finally { lock1.unlock(); }
});

Thread t2 = new Thread(() -> {
    lock2.lock();
    try {
        Thread.sleep(50);
        lock1.lock();
        try {
            // do work
        } finally { lock1.unlock(); }
    } catch (InterruptedException ignored) {}
    finally { lock2.unlock(); }
});

t1.start();
t2.start();
```

### ⚠️ Pitfalls

* Inconsistent lock order
* Holding multiple locks unnecessarily
* Long critical sections

### ✅ Prevention & Fixes

* **Lock Ordering:** Always acquire locks in the same order in all threads.
* **Try-Lock with Timeout:** Use `tryLock` with timeout to avoid indefinite waits.
* **Lock Hierarchies:** Define clear rules for lock acquisition.
* **Avoid Nested Locks:** Minimize nested locking when possible.
* **Use Higher-Level Constructs:** Use `ConcurrentHashMap`, `Semaphore`, or `Lock.tryLock` where applicable.
* **Deadlock Detection:** Use JVM tools (`jstack`, thread dumps) to analyze and fix deadlocks during development and production.

**Rule:** Design locking strategy carefully, prefer higher-level concurrency tools, and always release locks in a `finally` block.


### 🧩 🔟 Starvation

✅ A thread never progresses because others monopolize CPU/locks.\
**Common cause:** Unfair locks or priority threads hogging resources.\
**Prevention:** Use fair locks if necessary: `new ReentrantLock(true)`.

---

### 🧩 1️⃣1️⃣ Livelock

✅ Threads actively changing state in response to each other but not making progress.\
**Example:** Two threads repeatedly yielding to each other.\
**Fix:** Add back-off or randomized delays.

---

### 🧩 1️⃣2️⃣ Fork/Join

✅ For divide-and-conquer tasks.

```java
ForkJoinPool pool = new ForkJoinPool();
pool.invoke(new RecursiveTaskExample());
```

**Pitfalls:** Not splitting tasks fine enough → poor parallelism. Splitting too fine → overhead.\
**Best practice:** Choose optimal threshold for splitting.

---

### 🧩 1️⃣3️⃣ ExecutorService

✅ Manages thread pooling.

```java
ExecutorService pool = Executors.newFixedThreadPool(10);
```

**Pitfalls:** Not shutting down → resource leaks. Using `newCachedThreadPool()` without limits → OOM risk.\
**Tip:** Use bounded queues to control task backlog.

---

### 🧩 1️⃣4️⃣ Modern Concurrency Tools

| Tool              | Use                                  | Pitfalls                                | Tip                                     |
| ----------------- | ------------------------------------ | --------------------------------------- | --------------------------------------- |
| Semaphore         | Limit concurrent access              | Deadlock if `release()` forgotten       | Always release in `finally`             |
| CountDownLatch    | Wait until all tasks finish          | Non-reusable                            | For reusable barrier, use CyclicBarrier |
| CyclicBarrier     | Wait for group to reach point        | BrokenBarrierException if threads fail  | Handle barrier breaks properly          |
| Exchanger         | Swap data between threads            | Risk of deadlock if not paired          | Use with timeouts                       |
| Phaser            | Flexible barrier for dynamic parties | Complex API                             | Great for dynamic thread groups         |
| BlockingQueue     | Producer-consumer                    | Large queues can mask producer problems | Monitor queue size                      |
| ConcurrentHashMap | Safe concurrent map                  | Possible stale reads                    | Use compute()/merge() for atomic ops    |

---

## ✅ General Concurrency Best Practices

✅ Prefer immutability.\
✅ Minimize shared state.\
✅ Use higher-level concurrency utilities (`java.util.concurrent`) over low-level threads.\
✅ Test under load.\
✅ Monitor CPU and thread dumps.

