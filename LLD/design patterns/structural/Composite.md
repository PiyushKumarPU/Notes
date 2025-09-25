# Composite Design Pattern

## 🔹 What is it?

The **Composite Pattern** is a **structural design pattern** that lets you **treat individual objects and groups of objects uniformly**.

It’s useful when you have a tree-like structure (hierarchy) where **leaf nodes** (single objects) and **composite nodes** (groups of objects) should be treated the same way.

---

## 🔹 Why use it?

* To work with **hierarchical structures** (trees, directories, menus).
* When you want to **treat single and multiple objects the same**.
* To avoid complex conditionals like `if (leaf) else if (composite)` in client code.

---

## 🔹 How it works?

1. Define a **Component interface** (common operations).
2. **Leaf class** → represents an individual object.
3. **Composite class** → represents a group of Components (can contain both Leafs and other Composites).
4. Client works with `Component` uniformly, without caring if it’s a leaf or composite.

---

## ✅ Example (Java)

```java
// Component
interface FileSystem {
    void showDetails();
}

// Leaf
class File implements FileSystem {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileSystem {
    private String name;
    private List<FileSystem> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystem fs) {
        children.add(fs);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystem fs : children) {
            fs.showDetails();
        }
    }
}

// Client
public class CompositeDemo {
    public static void main(String[] args) {
        FileSystem file1 = new File("resume.pdf");
        FileSystem file2 = new File("photo.png");

        Directory dir1 = new Directory("Documents");
        dir1.add(file1);
        dir1.add(file2);

        FileSystem file3 = new File("song.mp3");
        Directory dir2 = new Directory("Media");
        dir2.add(file3);

        Directory root = new Directory("Root");
        root.add(dir1);
        root.add(dir2);

        root.showDetails();
    }
}
```

### Output:

```
Directory: Root
Directory: Documents
File: resume.pdf
File: photo.png
Directory: Media
File: song.mp3
```

---

## 🔹 Pros

* ✅ Treats individual objects and groups **uniformly**.
* ✅ Simplifies client code (no `if-else` checks for leaf/composite).
* ✅ Easy to add new leaf or composite classes.

---

## 🔹 Cons

* ❌ Can make code harder to restrict (you might allow meaningless operations, e.g., “add child” on a File).
* ❌ Can make the design overly general and harder to enforce constraints.

---

## 🔹 Design Principle

* ✔️ Follows **Open/Closed Principle** → add new file types without touching existing code.
* ✔️ Promotes **polymorphism** → client only sees `Component`.

---

## 🔹 Analogy

Think of a **company org chart** 🏢:

* **Employee (Leaf)** → Developer, Designer.
* **Manager (Composite)** → Manages employees (and possibly other managers).
* You can ask *anyone* (employee or manager) to “show details” → the behavior is uniform.

---

