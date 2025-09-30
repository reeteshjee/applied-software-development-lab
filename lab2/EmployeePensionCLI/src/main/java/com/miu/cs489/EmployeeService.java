package com.miu.cs489;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.miu.cs489.model.Employee;

import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    public static void printAllEmployeesJson(List<Employee> employees) throws Exception {
        // Sort by yearlySalary DESC, then lastName ASC
        employees.sort(Comparator.comparing(Employee::getYearlySalary).reversed()
                .thenComparing(Employee::getLastName));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());               // <-- Add this
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // <-- Add this
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(employees);
        System.out.println(json);
    }
}
