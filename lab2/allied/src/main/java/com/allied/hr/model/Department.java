package com.allied.hr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentNo;
    private String name;

    // Forward reference for serialization
    @JsonManagedReference
    private Employee head;

    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();

    public Department(String departmentNo, String name) {
        this.departmentNo = departmentNo;
        this.name = name;
    }

    public String getDepartmentNo() { return departmentNo; }
    public String getName() { return name; }

    public Employee getHead() { return head; }
    public void setHead(Employee head) { this.head = head; }

    public List<Employee> getEmployees() { return employees; }
    public void addEmployee(Employee emp) { this.employees.add(emp); }

    public double getTotalAnnualSalary() {
        return employees.stream().mapToDouble(e -> e.getBiweeklySalary() * 26).sum();
    }
}
