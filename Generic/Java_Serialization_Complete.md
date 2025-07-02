
## ✅ Java Serialization — Senior-Level Complete Notes

---

### 🔹 What is Serialization?

**Serialization** is the process of converting an object’s state into a **byte stream** to:
- Save to disk
- Send over a network
- Store & rebuild later (deserialization)

**Deserialization** = restoring the object from its byte form.

---

### 🔹 How Java Implements Serialization

- By using `java.io.Serializable` — a **marker interface** (no methods).
- The JVM uses `ObjectOutputStream` to serialize and `ObjectInputStream` to deserialize.

```java
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.ser"));
oos.writeObject(myObject);
oos.close();

ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.ser"));
MyClass obj = (MyClass) ois.readObject();
ois.close();
```

✅ **Key:** The class must implement `Serializable`.

---

### 🔹 Basic Example

```java
import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int id;
    transient String password; // not serialized

    Employee(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("Alice", 101, "secret");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("emp.ser"));
        out.writeObject(emp);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("emp.ser"));
        Employee saved = (Employee) in.readObject();
        in.close();

        System.out.println(saved.name); // Alice
        System.out.println(saved.password); // null
    }
}
```

---

### 🔹 serialVersionUID

- Controls versioning.
- Ensures sender & receiver have compatible class definitions.

```java
private static final long serialVersionUID = 1L;
```

If you change the class but keep the same UID → backward compatibility works.

---

### 🔹 `transient` Keyword

Fields marked `transient` are **skipped** during serialization.

Used for:
- Sensitive data (passwords, keys).
- Derived fields (cached results).

---

### 🔹 Custom Serialization Hooks

Override `writeObject` & `readObject` for more control:

```java
private void writeObject(ObjectOutputStream oos) throws IOException {
    oos.defaultWriteObject(); // default fields
    // add custom logic
}

private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ois.defaultReadObject();
    // add custom logic
}
```

---

### 🔹 Full Control: Externalizable

`Externalizable` = complete manual control.

```java
class MyData implements Externalizable {
    String name;

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
    }
}
```

---

## ✅ Security: Serialization Risks

- **Deserialization exploits** can lead to **Remote Code Execution** if untrusted data is processed.
- `readObject()` instantiates classes → unsafe if attacker controls bytes.

✅ **How to Protect:**
- **Never deserialize untrusted data**.
- Use a **whitelist** of allowed classes.

```java
ObjectInputStream ois = new ObjectInputStream(in) {
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        if (!allowedClasses.contains(desc.getName())) {
            throw new InvalidClassException("Unauthorized: " + desc.getName());
        }
        return super.resolveClass(desc);
    }
};
```

- Validate object state after deserialization.
- Use modern safe formats (JSON, Avro, Protobuf) for public APIs.

---

## ✅ If You DON’T Want Your Class Serialized

✔️ **Don’t implement `Serializable`**.  
✔️ If forced (e.g., base class is `Serializable`), block it:

```java
private void writeObject(ObjectOutputStream oos) throws IOException {
    throw new NotSerializableException("This class must not be serialized");
}
```

---

## ✅ If You MUST Serialize a Sensitive Class

✔️ Mark fields `transient` or encrypt in `writeObject`.  
✔️ Validate on `readObject`.  
✔️ Keep backward compatibility → maintain `serialVersionUID`.  
✔️ Write unit tests for version upgrades.

---

## ✅ Serialization Alternatives

- ✅ **JSON** (Jackson, Gson)
- ✅ **Protobuf** (Google Protocol Buffers)
- ✅ **Avro** (Apache)
- ✅ **Kryo** (fast Java serialization for big graphs)

Better for cross-language, versioning, safe for external data.

---

## ✅ Real-World Uses

- Java RMI (Remote Method Invocation)
- Session storage in distributed web apps
- Distributed caching (Hazelcast, Ignite)
- Message brokers (rare with native Java serialization, better to use JSON/Protobuf)

---

## ✅ Serialization Pitfalls

❌ Changing class structure → breaks backward compatibility.  
❌ Sensitive fields leaked → mark `transient`.  
❌ Large graphs → performance hit.  
❌ Blind deserialization → RCE risk.

---

## ✅ Best Practices

✅ Always set `serialVersionUID`.  
✅ Use `transient` wisely.  
✅ Consider `Externalizable` for tight control.  
✅ Whitelist allowed classes if you must deserialize.  
✅ Favor portable formats for APIs.

---

## ✅ Quick Cheat Recap

| Concept | Summary |
|---------|---------|
| Serializable | Marker for Java built-in serialization |
| serialVersionUID | Controls version compatibility |
| transient | Skip sensitive/derived fields |
| writeObject/readObject | Custom hooks |
| Externalizable | Full manual control |
| Don’t serialize | Throw `NotSerializableException` |
| Safer alternatives | JSON, Avro, Protobuf, Kryo |

---

**🔑 Tip:** Modern Java microservices rarely use built-in serialization for public data — JSON/Protobuf is safer, clearer, cross-language friendly.

✅ **Done — solid senior-level Serialization knowledge!**

