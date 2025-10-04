package com.learning.patterns.prototype;

import java.util.ArrayList;
import java.util.List;

/*
 * Concrete Prototype: Candidate
 * - Demonstrates cloning when class has both mutable and immutable fields.
 */
public class CandidatePrototype implements BasePrototype {

    private String name;                       // Immutable (String is immutable in Java)
    private String interviewLocation;          // Immutable
    private List<String> skills;               // Mutable (list of skills)

    // Default constructor
    public CandidatePrototype() {
        this.skills = new ArrayList<>();
    }

    // Parameterized constructor
    public CandidatePrototype(String name, String interviewLocation, List<String> skills) {
        this.name = name;
        this.interviewLocation = interviewLocation;
        // Defensive copy for safety
        this.skills = new ArrayList<>(skills);
    }

    public String getName() { return name; }
    public String getInterviewLocation() { return interviewLocation; }
    public List<String> getSkills() { return skills; }

    /*
     * Clone method
     * - Immutable fields (name, interviewLocation) can be copied directly.
     * - Mutable field (skills list) must be DEEP-COPIED to avoid shared references.
     */
    @Override
    public BasePrototype clone() {
        return new CandidatePrototype(this.name, this.interviewLocation, new ArrayList<>(this.skills));
    }
}


