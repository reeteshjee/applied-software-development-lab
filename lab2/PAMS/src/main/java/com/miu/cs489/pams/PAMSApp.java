package com.pams.app;

import com.google.gson.*;
import com.pams.model.Patient;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PAMSApp {

    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        patients.add(new Patient(1, "Daniel", "Agar", "(641) 123-0009", "dagar@m.as", "1 N Street", LocalDate.of(1987, 1, 19)));
        patients.add(new Patient(2, "Ana", "Smith", null, "amsith@te.edu", null, LocalDate.of(1948, 12, 5)));
        patients.add(new Patient(3, "Marcus", "Garvey", "(123) 292-0018", null, "4 East Ave", LocalDate.of(2001, 9, 18)));
        patients.add(new Patient(4, "Jeff", "Goldbloom", "(999) 165-1192", "jgold@es.co.za", null, LocalDate.of(1995, 2, 28)));
        patients.add(new Patient(5, "Mary", "Washington", null, null, "30 W Burlington", LocalDate.of(1932, 5, 31)));

        patients.sort(Comparator.comparing(Patient::getAge).reversed());

        // LocalDate adapter (inline)
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                })
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter("patients.json")) {
            gson.toJson(patients, writer);
            System.out.println("Patient data written to patients.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
