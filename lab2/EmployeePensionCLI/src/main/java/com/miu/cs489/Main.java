package com.miu.cs489;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<com.miu.cs489.model.Employee> employees = DataLoader.loadEmployees();

        System.out.println("==== All Employees ====");
        EmployeeService.printAllEmployeesJson(employees);

        System.out.println("\n==== Quarterly Upcoming Enrollees ====");
        QuarterlyReport.printUpcomingEnrollees(employees);
    }
}
