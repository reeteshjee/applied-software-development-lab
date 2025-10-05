package com.allied.hr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;

public class Employee {
    private String employeeNo;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEmployment;
    private double biweeklySalary;

    // Back reference to avoid infinite recursion
    @JsonBackReference
    private Department department;

    public Employee(String employeeNo, String firstName, String lastName,
                    LocalDate dob, LocalDate doe, double salary) {
        this.employeeNo = employeeNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.dateOfEmployment = doe;
        this.biweeklySalary = salary;
    }

    public String getEmployeeNo() { return employeeNo; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public LocalDate getDateOfEmployment() { return dateOfEmployment; }
    public double getBiweeklySalary() { return biweeklySalary; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public int getYearsOfEmployment() {
        return LocalDate.now().getYear() - dateOfEmployment.getYear();
    }
}
