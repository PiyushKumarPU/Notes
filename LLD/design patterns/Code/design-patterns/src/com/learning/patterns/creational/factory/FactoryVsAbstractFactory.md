# Factory vs Abstract Factory in Java

This document explains the **Factory Method** and **Abstract Factory** patterns using the EMI Loan Calculator example.

---

## 🔹 Factory Method Pattern

The **Factory Method** defines a method for creating an object, but lets subclasses decide which class to instantiate.

### Example: EMI Calculator Factory

```java
public class EmiCalculatorFactory {
    public static EmiCalculator getEmiCalculator(LoanType loanType) {
        return switch (loanType) {
            case PERSONAL -> new PersonalLoanEmiCalculator();
            case CAR -> new CarLoanEmiCalculator();
            case HOME -> new HomeLoanEmiCalculator();
            default -> throw new IllegalArgumentException("Invalid loan type");
        };
    }
}
```

### Usage:
```java
EmiCalculator calculator = EmiCalculatorFactory.getEmiCalculator(LoanType.PERSONAL);
double emi = calculator.calculateEmi(100000, 10, 12);
```

✅ **Key Point**: Factory returns a **single type of product** (EmiCalculator).

---

## 🔹 Abstract Factory Pattern

The **Abstract Factory** provides an interface for creating **families of related objects**, without specifying their concrete classes.

Instead of just returning one object, the Abstract Factory can return multiple related products (e.g., EMI Calculator + Loan Document).

### Abstract Factory Example

#### Product Interfaces
```java
interface EmiCalculator {
    double calculateEmi(double principal, double rate, int tenure);
}

interface LoanDocument {
    String getDocument();
}
```

#### Concrete Products
```java
class PersonalLoanEmiCalculator implements EmiCalculator {
    public double calculateEmi(double principal, double rate, int tenure) {
        return (principal * rate * tenure) / 100;
    }
}
class PersonalLoanDocument implements LoanDocument {
    public String getDocument() { return "Personal Loan Agreement"; }
}
```

(Similar classes exist for **CarLoan** and **HomeLoan**.)

#### Abstract Factory
```java
interface LoanFactory {
    EmiCalculator createEmiCalculator();
    LoanDocument createLoanDocument();
}
```

#### Concrete Factories
```java
class PersonalLoanFactory implements LoanFactory {
    public EmiCalculator createEmiCalculator() { return new PersonalLoanEmiCalculator(); }
    public LoanDocument createLoanDocument() { return new PersonalLoanDocument(); }
}
```

#### Factory Producer
```java
enum LoanType { PERSONAL, CAR, HOME }

class LoanFactoryProducer {
    public static LoanFactory getLoanFactory(LoanType type) {
        return switch (type) {
            case PERSONAL -> new PersonalLoanFactory();
            case CAR -> new CarLoanFactory();
            case HOME -> new HomeLoanFactory();
        };
    }
}
```

#### Usage (Client)
```java
public class AbstractFactoryDemo {
    static void main(String[] args) {
        LoanFactory personalLoanFactory = LoanFactoryProducer.getLoanFactory(LoanType.PERSONAL);

        EmiCalculator calculator = personalLoanFactory.createEmiCalculator();
        LoanDocument document = personalLoanFactory.createLoanDocument();

        System.out.println("EMI: " + calculator.calculateEmi(100000, 10, 12));
        System.out.println("Document: " + document.getDocument());
    }
}
```

---

## 🔎 Comparison: Factory vs Abstract Factory

| Feature                | Factory Method | Abstract Factory |
|-------------------------|----------------|-----------------|
| Creates                | One product    | Family of related products |
| Example in our case    | Only `EmiCalculator` | `EmiCalculator` + `LoanDocument` |
| Flexibility            | Simple, less scalable | More scalable, handles product families |
| Client knows concrete? | No             | No              |
| Complexity             | Low            | Higher          |

---

## ✅ Summary

- Use **Factory Method** when you only need to create one kind of object but want to hide instantiation details.  
- Use **Abstract Factory** when you need to create **multiple related objects** together, without exposing their creation logic.

---
