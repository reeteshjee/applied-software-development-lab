package com.allied.hr.cli;

import com.allied.hr.data.DataLoader;
import com.allied.hr.model.Department;
import com.allied.hr.model.Employee;
import com.allied.hr.service.HRService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Department> departments = DataLoader.loadDepartments();
        Map<String, Employee> employees = DataLoader.loadEmployees();
        DataLoader.wireUp(departments, employees);

        HRService service = new HRService(departments, employees);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        if(args.length==0){
            System.out.println("Usage: java -jar hr-cli.jar <print-departments|print-employees>");
            return;
        }

        switch(args[0]){
            case "print-departments":
                List<Department> deptList = service.getDepartmentsSortedByTotalAnnualSalaryDesc();
                System.out.println(mapper.writeValueAsString(deptList));
                break;
            case "print-employees":
                List<Map<String,Object>> empList = service.getEmployeesSortedByYearsDescThenLastName();
                System.out.println(mapper.writeValueAsString(empList));
                break;
            default:
                System.out.println("Unknown command. Use print-departments or print-employees");
        }
    }
}
