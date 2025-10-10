package com.learning.patterns.creational.builder;

public class CandidateBuilderTester {

    static void main() {
        Candidate candidate = Candidate.CandidateBuilder.builder()
                .firstName("John")
                .lastName("Doe")
                .age(28)
                .gender("Male")
                .email("john.doe@example.com")
                .phone("1234567890")
                .build();

        System.out.println(candidate);

    }
}
