package com.miu.cs489;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.miu.cs489.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class QuarterlyReport {
    public static void printUpcomingEnrollees(List<Employee> employees) throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate nextQuarterStart = today.plusMonths(3 - (today.getMonthValue() - 1) % 3).withDayOfMonth(1);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(3).minusDays(1);

        List<Employee> filtered = employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> e.getEmploymentDate().plusYears(3).isAfter(nextQuarterStart.minusDays(1)))
                .filter(e -> e.getEmploymentDate().plusYears(3).isBefore(nextQuarterEnd.plusDays(1)))
                .sorted((e1, e2) -> e2.getEmploymentDate().compareTo(e1.getEmploymentDate()))
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(employees);
        System.out.println(json);
    }
}
