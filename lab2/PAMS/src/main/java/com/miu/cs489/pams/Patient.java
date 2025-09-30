package com.pams.model;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String mailingAddress;
    private LocalDate dob;
    private int age;

    public Patient(int id, String firstName, String lastName, String phone,
                   String email, String mailingAddress, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.dob = dob;
        this.age = Period.between(dob, LocalDate.now()).getYears();
    }

    public int getAge() {
        return age;
    }

    // Getters and setters here (or use Lombok if preferred)
}
