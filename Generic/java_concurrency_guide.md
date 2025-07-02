## ✅ Java Concurrency Deep-Dive Cheat Sheet — Fully Expanded

### 🧩 1️⃣ What is a Thread?
Already expanded.

### 🧩 2️⃣ How do you create Threads?
Already expanded.

### 🧩 3️⃣ Runnable vs Callable
Runnable runs code without a return, Callable returns a value and can throw checked exceptions. Runnable’s `run()` returns void, Callable’s `call()` returns a value. Executors can submit both.
```java
ExecutorService ex = Executors.newSingleThreadExecutor();
Future<Integer> future = ex.submit(() -> 42);
System.out.println(future.get());
ex.shutdown();
```

### 🧩 4️⃣ Thread Lifecycle
States: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.
```java
Thread t = new Thread(() -> {});
System.out.println(t.getState());
```

### 🧩 5️⃣ Synchronization
`synchronized` keyword ensures only one thread enters a block.
```java
synchronized(this) {
  // critical
}
```

### 🧩 6️⃣ ReentrantLock vs synchronized
ReentrantLock allows tryLock(), fairness, interrupts. Always unlock in `finally`.
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
 // work
} finally {
 lock.unlock();
}
```

### 🧩 7️⃣ volatile
Guarantees visibility but not atomicity. Use for flags.

### 🧩 8️⃣ wait()/notify()
Coordinate threads using object monitors.

### 🧩 9️⃣ Deadlock
Threads hold resources waiting for each other. Fix: lock ordering, timeouts.

### 🧩 🔟 Starvation
Thread never gets CPU due to other threads dominating locks.

### 🧩 1️⃣1️⃣ Livelock
Threads keep yielding for each other without progress.

### 🧩 1️⃣2️⃣ Fork/Join
Divide big tasks into subtasks.

### 🧩 1️⃣3️⃣ ExecutorService
Better than raw threads. Fixed or cached pools. Shut down gracefully.

### 🧩 1️⃣4️⃣ Modern Concurrency Tools
- Semaphore: limits threads
- CountDownLatch: wait for other threads
- CyclicBarrier: sync group
- Exchanger: swap data
- Phaser: advanced barrier
- BlockingQueue: producer-consumer
- ConcurrentHashMap: safe concurrent map

Use these to write safe, scalable concurrency code.

