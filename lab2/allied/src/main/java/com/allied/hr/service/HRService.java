package com.allied.hr.service;

import com.allied.hr.model.Department;
import com.allied.hr.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class HRService {
    private final Map<String, Department> departments;
    private final Map<String, Employee> employees;

    public HRService(Map<String, Department> departments, Map<String, Employee> employees) {
        this.departments = departments;
        this.employees = employees;
    }

    public List<Department> getDepartmentsSortedByTotalAnnualSalaryDesc() {
        return departments.values().stream()
                .sorted(Comparator.comparingDouble(Department::getTotalAnnualSalary).reversed())
                .collect(Collectors.toList());
    }

    public List<Map<String,Object>> getEmployeesSortedByYearsDescThenLastName() {
        return employees.values().stream()
                .sorted(Comparator.comparingInt(Employee::getYearsOfEmployment).reversed()
                        .thenComparing(Employee::getLastName))
                .map(e -> {
                    Map<String,Object> m = new LinkedHashMap<>();
                    m.put("employeeNo", e.getEmployeeNo());
                    m.put("firstName", e.getFirstName());
                    m.put("lastName", e.getLastName());
                    m.put("department", Map.of(
                            "departmentNo", e.getDepartment().getDepartmentNo(),
                            "name", e.getDepartment().getName()
                    ));
                    m.put("yearsOfEmployment", e.getYearsOfEmployment());
                    return m;
                }).toList();
    }
}
