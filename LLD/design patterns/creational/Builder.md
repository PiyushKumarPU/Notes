# Builder Design Pattern

## 🔹 What is it?

The **Builder Pattern** is a **creational design pattern** that helps construct **complex objects step by step**.
It separates the **construction process** from the **final product**, so the same building process can create different representations.

---

## 🔹 Why use it?

* When object creation involves **lots of parameters** (some required, some optional).
* To avoid **telescoping constructors** (constructor overload hell).
* To **make code readable** with a fluent, chainable API.
* When we want to ensure the **object is immutable** after creation.

---

## 🔹 How it works?

1. Define a **Builder class** that collects parameters step by step.
2. Provide chainable methods (`setXyz()`) to configure the object.
3. The `build()` method returns the final **immutable product**.
4. The **Product** class constructor is made private → enforce builder usage.

---

## ✅ Example

```java
// Product
class User {
    private final String firstName;  // required
    private final String lastName;   // required
    private final int age;           // optional
    private final String email;      // optional

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName  = builder.lastName;
        this.age       = builder.age;
        this.email     = builder.email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + 
               (age > 0 ? ", age: " + age : "") + 
               (email != null ? ", email: " + email : "");
    }

    // Builder Class
    public static class UserBuilder {
        private String firstName;
        private String lastName;
        private int age;
        private String email;

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

// Client
public class BuilderDemo {
    public static void main(String[] args) {
        User user = new User.UserBuilder()
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setAge(30)
                        .setEmail("john@example.com")
                        .build();

        System.out.println(user);
    }
}
```

### Output:

```
John Doe, age: 30, email: john@example.com
```

---

## 🔹 Pros

* ✅ Clean & **readable** object creation.
* ✅ Avoids telescoping constructors (`new User("a","b","c","d")`).
* ✅ Supports **immutability** (final fields).
* ✅ More maintainable if new fields are added.

---

## 🔹 Cons

* ❌ More boilerplate (extra Builder class).
* ❌ Overkill for simple objects with 2–3 fields.
* ❌ Requires careful validation (builder might allow inconsistent state if not checked).

---

## 🔹 Design Principle

* ✔️ Follows **Single Responsibility Principle** → separates object construction from representation.
* ✔️ Aligns with **Open/Closed Principle** → you can extend builder methods without changing client code.
* ✔️ Improves **readability & maintainability**.

---

## 🔹 Analogy

Think of **ordering a burger 🍔**:

* Instead of directly constructing with all ingredients in one messy line,
* You **instruct step by step**: add bun → add patty → add cheese → add lettuce → done.
* Final product = your burger (immutable & consistent).

---