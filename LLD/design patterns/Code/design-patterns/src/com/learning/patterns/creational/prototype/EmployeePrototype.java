package com.learning.patterns.prototype;

import java.util.HashMap;
import java.util.Map;

/*
 * Concrete Prototype: Employee
 * - Has both mutable and immutable fields.
 */
public class EmployeePrototype implements BasePrototype {

    private String name;            // Immutable
    private String company;         // Immutable
    private Map<String, String> metadata;  // Mutable (e.g., extra info: dept, role)

    // Default constructor
    public EmployeePrototype() {
        this.metadata = new HashMap<>();
    }

    // Parameterized constructor
    public EmployeePrototype(String name, String company, Map<String, String> metadata) {
        this.name = name;
        this.company = company;
        // Defensive copy for mutable map
        this.metadata = new HashMap<>(metadata);
    }

    public String getName() { return name; }
    public String getCompany() { return company; }
    public Map<String, String> getMetadata() { return metadata; }

    /*
     * Clone method
     * - Immutable fields (name, company) are copied directly.
     * - Mutable field (metadata map) is DEEP-COPIED to avoid shared references.
     */
    @Override
    public BasePrototype clone() {
        return new EmployeePrototype(this.name, this.company, new HashMap<>(this.metadata));
    }
}


