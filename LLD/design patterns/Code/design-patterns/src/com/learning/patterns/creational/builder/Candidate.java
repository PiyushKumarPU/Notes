package com.learning.patterns.creational.builder;

import lombok.Getter;

import java.util.List;

/*
 * Candidate class
 * Represents a job applicant with personal, professional, and skill details.
 */
@Getter
public class Candidate {

    // --- Getters (you can keep or remove setters for immutability) ---
    // --- Personal Information ---
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;

    // --- Professional Information ---
    private String highestQualification;
    private int yearsOfExperience;
    private String currentCompany;
    private String currentPosition;
    private double currentCTC;   // Current Salary
    private double expectedCTC;  // Expected Salary
    private int noticePeriodDays;

    // --- Skills & Interests ---
    private List<String> technicalSkills;
    private List<String> softSkills;
    private List<String> certifications;
    private List<String> languages;  // Spoken Languages

    // --- Additional ---
    private String linkedInProfile;
    private String githubProfile;
    private String resumeFilePath;

    // --- Private Constructor (only accessible via Builder) ---
    private Candidate() {}

    // --- Builder Class ---
    public static class CandidateBuilder {
        // same fields as Candidate
        private String firstName;
        private String lastName;
        private int age;
        private String gender;
        private String email;
        private String phone;
        private String address;
        private String city;
        private String country;

        private String highestQualification;
        private int yearsOfExperience;
        private String currentCompany;
        private String currentPosition;
        private double currentCTC;
        private double expectedCTC;
        private int noticePeriodDays;

        private List<String> technicalSkills;
        private List<String> softSkills;
        private List<String> certifications;
        private List<String> languages;

        private String linkedInProfile;
        private String githubProfile;
        private String resumeFilePath;

        private CandidateBuilder() {}

        public static CandidateBuilder builder() {
            return new CandidateBuilder();
        }

        // --- Fluent Builder methods ---
        public CandidateBuilder firstName(String firstName) { this.firstName = firstName; return this; }
        public CandidateBuilder lastName(String lastName) { this.lastName = lastName; return this; }
        public CandidateBuilder age(int age) { this.age = age; return this; }
        public CandidateBuilder gender(String gender) { this.gender = gender; return this; }
        public CandidateBuilder email(String email) { this.email = email; return this; }
        public CandidateBuilder phone(String phone) { this.phone = phone; return this; }
        public CandidateBuilder address(String address) { this.address = address; return this; }
        public CandidateBuilder city(String city) { this.city = city; return this; }
        public CandidateBuilder country(String country) { this.country = country; return this; }

        public CandidateBuilder highestQualification(String highestQualification) { this.highestQualification = highestQualification; return this; }
        public CandidateBuilder yearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; return this; }
        public CandidateBuilder currentCompany(String currentCompany) { this.currentCompany = currentCompany; return this; }
        public CandidateBuilder currentPosition(String currentPosition) { this.currentPosition = currentPosition; return this; }
        public CandidateBuilder currentCTC(double currentCTC) { this.currentCTC = currentCTC; return this; }
        public CandidateBuilder expectedCTC(double expectedCTC) { this.expectedCTC = expectedCTC; return this; }
        public CandidateBuilder noticePeriodDays(int noticePeriodDays) { this.noticePeriodDays = noticePeriodDays; return this; }

        public CandidateBuilder technicalSkills(List<String> technicalSkills) { this.technicalSkills = technicalSkills; return this; }
        public CandidateBuilder softSkills(List<String> softSkills) { this.softSkills = softSkills; return this; }
        public CandidateBuilder certifications(List<String> certifications) { this.certifications = certifications; return this; }
        public CandidateBuilder languages(List<String> languages) { this.languages = languages; return this; }

        public CandidateBuilder linkedInProfile(String linkedInProfile) { this.linkedInProfile = linkedInProfile; return this; }
        public CandidateBuilder githubProfile(String githubProfile) { this.githubProfile = githubProfile; return this; }
        public CandidateBuilder resumeFilePath(String resumeFilePath) { this.resumeFilePath = resumeFilePath; return this; }

        // --- Build method ---
        public Candidate build() {
            Candidate candidate = new Candidate();
            candidate.firstName = this.firstName;
            candidate.lastName = this.lastName;
            candidate.age = this.age;
            candidate.gender = this.gender;
            candidate.email = this.email;
            candidate.phone = this.phone;
            candidate.address = this.address;
            candidate.city = this.city;
            candidate.country = this.country;

            candidate.highestQualification = this.highestQualification;
            candidate.yearsOfExperience = this.yearsOfExperience;
            candidate.currentCompany = this.currentCompany;
            candidate.currentPosition = this.currentPosition;
            candidate.currentCTC = this.currentCTC;
            candidate.expectedCTC = this.expectedCTC;
            candidate.noticePeriodDays = this.noticePeriodDays;

            candidate.technicalSkills = this.technicalSkills;
            candidate.softSkills = this.softSkills;
            candidate.certifications = this.certifications;
            candidate.languages = this.languages;

            candidate.linkedInProfile = this.linkedInProfile;
            candidate.githubProfile = this.githubProfile;
            candidate.resumeFilePath = this.resumeFilePath;

            return candidate;
        }
    }

    // --- toString() for debugging ---
    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + firstName + " " + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", currentPosition='" + currentPosition + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", skills=" + technicalSkills +
                '}';
    }
}
