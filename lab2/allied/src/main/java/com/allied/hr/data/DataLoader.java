package com.allied.hr.data;

import com.allied.hr.model.Department;
import com.allied.hr.model.Employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {

    public static Map<String, Department> loadDepartments() {
        Map<String, Department> departments = new HashMap<>();
        departments.put("31288741190182539912", new Department("31288741190182539912","Sales"));
        departments.put("29274582650152771644", new Department("29274582650152771644","Marketing"));
        departments.put("29274599650152771609", new Department("29274599650152771609","Engineering"));
        return departments;
    }

    public static Map<String, Employee> loadEmployees() {
        Map<String, Employee> employees = new HashMap<>();
        employees.put("000-11-1234", new Employee("000-11-1234","Michael","Philips",
                LocalDate.of(1995,12,31),LocalDate.of(2021,10,15),2750.50));
        employees.put("000-61-0987", new Employee("000-61-0987","Anna","Smith",
                LocalDate.of(1981,9,17),LocalDate.of(2022,8,21),2500.00));
        employees.put("000-99-1766", new Employee("000-99-1766","John","Hammonds",
                LocalDate.of(2001,7,15),LocalDate.of(2025,1,23),1560.75));
        employees.put("000-41-1768", new Employee("000-41-1768","Barbara","Jones",
                LocalDate.of(2000,11,18),LocalDate.of(2025,3,13),1650.55));
        return employees;
    }

    public static void wireUp(Map<String, Department> departments, Map<String, Employee> employees) {
        // Assign employees to departments
        employees.get("000-11-1234").setDepartment(departments.get("29274599650152771609"));
        employees.get("000-61-0987").setDepartment(departments.get("31288741190182539912"));
        employees.get("000-99-1766").setDepartment(departments.get("31288741190182539912"));
        employees.get("000-41-1768").setDepartment(departments.get("29274582650152771644"));

        // Add employees to department lists
        for(Employee e: employees.values()){
            e.getDepartment().addEmployee(e);
        }

        // Set department heads
        departments.get("31288741190182539912").setHead(employees.get("000-61-0987"));
        departments.get("29274599650152771609").setHead(employees.get("000-11-1234"));
        // Marketing has null head
    }
}
