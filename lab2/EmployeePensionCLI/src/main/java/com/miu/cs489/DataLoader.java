package com.miu.cs489;

import com.miu.cs489.model.Employee;
import com.miu.cs489.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Daniel", "Agar",
                LocalDate.of(2023, 1, 17), 105945.50,
                null));
        employees.add(new Employee(2, "Benard", "Shaw",
                LocalDate.of(2022, 9, 3), 197750.00,
                null));
        employees.add(new Employee(3, "Carly", "Agar",
                LocalDate.of(2014, 5, 16), 842000.75,
                new PensionPlan("SM2307", LocalDate.of(2017, 5, 17), 1555.50)));
        employees.add(new Employee(4, "Wesley", "Schneider",
                LocalDate.of(2023, 7, 21), 74500.00, null));
        employees.add(new Employee(5, "Anna", "Wiltord",
                LocalDate.of(2023, 3, 15), 85750.00, null));
        employees.add(new Employee(6, "Yosef", "Tesfalem",
                LocalDate.of(2024, 10, 31), 100000.00, null));

        return employees;
    }
}
