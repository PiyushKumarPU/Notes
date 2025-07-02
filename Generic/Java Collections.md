# ✅ Java Collections

## 1️⃣ ArrayList vs LinkedList
**Q:** Difference? When to use which?

**A:**
- `ArrayList`: Backed by dynamic array. Fast random access (`O(1)`), great for read-heavy operations. Insert/remove at end: `O(1)` amortized. Insert/remove in middle: `O(n)` due to shifting.
- `LinkedList`: Doubly linked list. Random access is `O(n)`. Insert/remove at start or middle: `O(1)` if iterator used. More memory overhead due to node pointers.
- Modern practice: Prefer `ArrayList` for general use; `LinkedList` only if you really need fast frequent inserts/removals at both ends.

---

## 2️⃣ TreeSet & TreeMap Ordering
**Q:** How is ordering maintained?

**A:**
- Backed by Red-Black Tree (self-balancing BST).
- Uses natural order (`Comparable`) or a custom `Comparator`.
- Without `Comparable` or `Comparator`, insertion causes `ClassCastException`.

---

## 3️⃣ HashMap Collisions
**Q:** How does `HashMap` handle hash collisions?

**A:**
- Uses buckets based on `hashCode()`.
- Keys with same hash go to same bucket → stored as linked list.
- Java 8+: If bucket chain length > 8 and table size ≥ 64 → converts to balanced tree for `O(log n)` lookups.
- Resizing doubles capacity and rehashes entries.

---

## 4️⃣ HashMap vs Hashtable vs ConcurrentHashMap
**Q:** Differences? When to use?

**A:**
- `HashMap`: Not thread-safe; allows `null` keys/values.
- `Hashtable`: Thread-safe via synchronized methods; legacy class; no `null` keys/values.
- `ConcurrentHashMap`: Thread-safe with high concurrency — uses fine-grained bucket-level locks & CAS; no global lock; disallows `null` keys/values.

---

## 5️⃣ Fail-Fast vs Fail-Safe
**Q:** What’s the difference?

**A:**
- **Fail-fast**: Iterator throws `ConcurrentModificationException` if collection structure changes during iteration (`ArrayList`, `HashMap`).
- **Fail-safe**: Iterator works on snapshot or uses safe mechanism — doesn’t throw (`ConcurrentHashMap`, `CopyOnWriteArrayList`).
- Trade-off: fail-safe may show stale data.

---

## 6️⃣ SynchronizedList vs CopyOnWriteArrayList
**Q:** How are they different?

**A:**
- `Collections.synchronizedList`: Wraps a list with coarse-grained synchronization. All methods block on same lock. Manual sync needed for iteration.
- `CopyOnWriteArrayList`: Creates new copy of underlying array on every write. Reads are lock-free, fast. Writes expensive. Great for read-heavy scenarios.

---

## 7️⃣ Immutable vs Unmodifiable
**Q:** Difference? How to create each?

**A:**
- **Unmodifiable**: A read-only view; original can still change.
  ```java
  List<String> unmod = Collections.unmodifiableList(list);
  ```
- **Immutable**: Truly frozen, no change possible.
  ```java
  List<String> immutable = List.of("A", "B");
  ```
- Use immutability for true safety and sharing between threads.

---

## 8️⃣ Streams — Collectors
**Q:** How to convert a `Stream` to `List`? How to group?

**A:**
```java
List<T> result = stream.collect(Collectors.toList());

Map<Integer, List<String>> grouped = list.stream()
  .collect(Collectors.groupingBy(String::length));
```

---

## 9️⃣ HashMap Capacity & Load Factor
**Q:** Impact on performance?

**A:**
- Initial capacity: starting number of buckets.
- Load factor: threshold ratio to trigger resize (default 0.75).
- Frequent resizing → CPU/memory cost for rehashing.
- Tune for large or write-heavy maps.

---

## 🔟 equals() & hashCode()
**Q:** Why must they be consistent?

**A:**
- `hashCode()` decides bucket; `equals()` checks true equality inside bucket.
- If `equals()` returns true but hash codes differ → map can’t find keys reliably.
- Always override both to follow contract.

---

## 1️⃣1️⃣ Safe Removal
**Q:** Why is `for-each` unsafe?

**A:**
- `for-each` uses an `Iterator` under the hood.
- Removing via the collection breaks iterator’s state → `ConcurrentModificationException`.
- Safe:
  ```java
  Iterator<String> it = list.iterator();
  while (it.hasNext()) {
    if (condition) it.remove();
  }
  ```

---

## 1️⃣2️⃣ PriorityQueue
**Q:** How does it work? Is iterator sorted?

**A:**
- Backed by binary heap (array). `poll()` gives smallest (or largest) based on comparator.
- Iterator does not guarantee sorted order. Use repeated `poll()` for priority order.

---

## 1️⃣3️⃣ subList()
**Q:** Is subList independent?

**A:**
- No. `subList()` is a backed view. Changes to parent affect sublist and vice versa.
- Structural changes to parent outside subrange → `ConcurrentModificationException`.
- To decouple: `new ArrayList<>(subList)`.

---

## 1️⃣4️⃣ ConcurrentHashMap: Java 8 vs Older
**Q:** Key changes?

**A:**
- Java 5–7: Segments with ReentrantLocks → concurrency limited to segments.
- Java 8+: Removed segments. Single bucket array with fine-grained locks & CAS. Reads lock-free. Buckets can treeify for bad hash distribution.

---

## 1️⃣5️⃣ Huge Datasets — Lazy Collection Design
**Q:** How to handle huge data that doesn’t fit in RAM?

**A:**
- Use a custom `Iterator` or `Spliterator` that loads data lazily.
- Fetch pages/chunks on demand.
- Backed by DB query, file read, or streaming API.
- Expose as `Stream` or `List` implementation with `get()` that triggers paging.

```java
public class LazyBigList<T> extends AbstractList<T> {
  private final DataSource source;
  private final int pageSize;
  public T get(int index) { ... fetch page ... }
}
```

- Keeps memory usage low while providing standard Java Collection APIs.

---
