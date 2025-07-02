
## ✅ Java Garbage Collector (GC)

---

### 🔹 What is Garbage Collection?

**Garbage Collection (GC)** is the JVM’s process of **automatically reclaiming heap memory** by removing objects that are no longer reachable.

- Prevents memory leaks when used properly.
- Automates memory management.
- Frees developers from manual deallocation (unlike C/C++).

---

### 🔹 How GC Works — Under the Hood

**Key Concepts:**  
- **Reachability:** An object is reachable if it can be accessed via a chain from **GC Roots**.  
- **GC Roots:** Active threads, static vars, method stack refs.  
- **Phases:**  
  1️⃣ **Mark:** Identify live objects.  
  2️⃣ **Sweep:** Remove unreachable.  
  3️⃣ **Compact:** Defragment heap if needed.

---

### 🔹 The Generational Hypothesis

Modern GCs assume:  
- Most objects die young.  
- Few live long.

| Generation | What it holds | GC Type |
|------------|----------------|---------|
| **Young Gen** | New objects (Eden, Survivor) | **Minor GC** |
| **Old Gen** | Surviving long-lived objects | **Major/Full GC** |
| **Metaspace** | Class metadata | Managed separately |

---

### ✅ Minor GC

- New objects in Eden Space.
- Minor GC: marks dead Eden objects, copies survivors to Survivor Spaces.
- Surviving long enough → promoted to Old Gen.

✅ Frequent, fast, but stop-the-world.

---

### ✅ Major / Full GC

- Cleans Old Gen.
- May clean Young Gen too → Full GC.
- Costlier, longer pause.

---

## 🔹 Popular Garbage Collectors

### ✅ 1️⃣ Serial GC

- Single-threaded, stop-the-world.
- Small heaps, single-core.

`-XX:+UseSerialGC`

---

### ✅ 2️⃣ Parallel GC (Throughput)

- Multi-threaded.
- Optimizes throughput, longer pauses.

`-XX:+UseParallelGC`

---

### ✅ 3️⃣ CMS (Concurrent Mark Sweep)

- Mark & Sweep concurrently.
- Low pause times.
- May fragment → no compaction.
- Deprecated.

`-XX:+UseConcMarkSweepGC`

---

### ✅ 4️⃣ G1 GC (Garbage First)

- Heap split into regions.
- Collects garbage in regions with most garbage first.
- Balanced throughput + predictable pause.

Default in modern JVMs.

`-XX:+UseG1GC`

---

### ✅ 5️⃣ ZGC & Shenandoah

- Ultra-low pause, concurrent collectors.
- Large heaps, modern apps.

`-XX:+UseZGC` or `-XX:+UseShenandoahGC`

---

## 🔹 Tuning Parameters

| Option | Meaning |
|--------|---------|
| `-Xms` | Initial heap size |
| `-Xmx` | Max heap size |
| `-XX:NewRatio` | Young/Old ratio |
| `-XX:SurvivorRatio` | Eden/Survivor ratio |
| `-Xlog:gc*` | GC logs |

---

## ✅ GC Best Practices

✅ Pick GC for workload (latency vs throughput).  
✅ Tune heap sizes.  
✅ Null references when done.  
✅ Avoid static leaks.  
✅ Use Weak/Soft refs for caches.  
✅ Profile + monitor (`jvisualvm`, `jstat`).  
✅ Load test real scenarios.  
✅ Use try-with-resources.

GC log example:  
```bash
java -Xmx2G -Xms2G -XX:+UseG1GC -Xlog:gc* MyApp
```

---

## ✅ Common Pitfalls

❌ Heap too big → long pauses.  
❌ Assuming GC fixes all leaks.  
❌ Over-tuning without profiling.

---

## ✅ Deep-Dive Points

✔️ Generational GC: Minor vs Major.  
✔️ Mark-Sweep-Compact.  
✔️ Compare G1, CMS, Parallel.  
✔️ ZGC, Shenandoah for modern low-latency.  
✔️ Causes of OOM despite GC.  
✔️ Practical tuning examples.

---

## ✅ Quick Recap

| Concept | Key |
|---------|-----|
| Minor GC | Young Gen |
| Major GC | Old Gen |
| Full GC | Whole heap |
| Serial GC | Simple |
| Parallel GC | Throughput |
| CMS | Low pause |
| G1 | Balanced |
| ZGC | Ultra-low pause |

✅ GC is the JVM’s heartbeat — tune it, monitor it, test it.

---
