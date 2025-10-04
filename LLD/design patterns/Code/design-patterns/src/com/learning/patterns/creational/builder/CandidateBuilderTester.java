package com.learning.patterns.creational.builder;

import java.util.Arrays;

public class CandidateBuilderTester {

    static void main() {
        Candidate candidate = Candidate.CandidateBuilder.builder()
                .firstName("John")
                .lastName("Doe")
                .age(28)
                .gender("Male")
                .email("john.doe@example.com")
                .phone("1234567890")
                .highestQualification("B.Tech Computer Science")
                .yearsOfExperience(5)
                .currentCompany("TechCorp")
                .currentPosition("Software Engineer")
                .currentCTC(12.5)
                .expectedCTC(18.0)
                .noticePeriodDays(60)
                .technicalSkills(Arrays.asList("Java", "Spring Boot", "SQL"))
                .softSkills(Arrays.asList("Communication", "Teamwork"))
                .linkedInProfile("https://linkedin.com/in/johndoe")
                .githubProfile("https://github.com/johndoe")
                .build();

        System.out.println(candidate);

    }
}
