
## ✅ Comparable vs Comparator — Senior-Level Note

---

### 🔹 Why do we need them?

- Define **natural ordering** or **custom ordering**.
- Used in sorting (`Collections.sort()`, `TreeSet`, `TreeMap`).

### ✅ Comparable

- `java.lang.Comparable`
- Defines **natural order** inside class.

```java
class Employee implements Comparable<Employee> {
    int id;
    public int compareTo(Employee other) {
        return this.id - other.id;
    }
}
```

### ✅ Comparator

- `java.util.Comparator`
- Defines **custom order**, external.

```java
class NameComparator implements Comparator<Employee> {
    public int compare(Employee e1, Employee e2) {
        return e1.name.compareTo(e2.name);
    }
}
```

Or Java 8+:

```java
list.sort(Comparator.comparing(Employee::getName));
```

### ✅ Differences

| Comparable | Comparator |
|------------|-------------|
| Natural order | Custom order |
| In class | External |
| One per class | Many possible |

✅ **Best Practice:** Use `Comparable` for natural order, `Comparator` for flexibility.

---

## ✅ Immutable Classes — Senior-Level Note

---

### 🔹 What is an Immutable Class?

- Object’s state **can’t change** after creation.
- Fields are `final`, no setters.

### ✅ Why Immutable?

- Thread-safe by default.
- Safe for `HashMap` keys.
- `String` is immutable.

### ✅ Example

```java
public final class Person {
    private final String name;
    private final int age;
    public Person(String name, int age) {
        this.name = name; this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
}
```

### ✅ With Mutable Field

```java
public final class Student {
    private final String name;
    private final Date dob;
    public Student(String name, Date dob) {
        this.name = name;
        this.dob = new Date(dob.getTime());
    }
    public Date getDob() {
        return new Date(dob.getTime());
    }
}
```

### ✅ Rules

| Rule | Why |
|------|-----|
| `final` class | No subclassing |
| `private final` fields | No change |
| No setters | Lock state |
| Defensive copies | For mutable fields |

### ✅ Tip

- Use `record` in Java 17+ for easy immutability:

```java
record Person(String name, int age) {}
```

**String** is immutable for pooling, thread safety, and hash key reliability.

---

✅ **Combined Comparable vs Comparator & Immutable Classes ready!**
