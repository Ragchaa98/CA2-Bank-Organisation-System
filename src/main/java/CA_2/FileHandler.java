/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class FileHandler {
    public static final String EXPECTED_FILE_NAME = "Applicants_Form.txt";

    /**
     * Loads the expected file from the project root first, then resources as fallback
     */
    public ArrayList<Employee> readApplicantsFile(String fileName) throws IOException {
        if (!EXPECTED_FILE_NAME.equals(fileName)) {
            throw new IOException("Only " + EXPECTED_FILE_NAME + " is allowed for this assignment.");
        }

        Path rootPath = Path.of(EXPECTED_FILE_NAME);
        if (Files.exists(rootPath)) {
            try (BufferedReader reader = Files.newBufferedReader(rootPath)) {
                return parseEmployees(reader);
            }
        }

        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(EXPECTED_FILE_NAME);
        if (resourceStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
                return parseEmployees(reader);
            }
        }

        throw new IOException(EXPECTED_FILE_NAME + " was not found in the project root or resources folder.");
    }

    /**
     * The sample data contains no comma inside field values
     * so split with -1 safely preserves empty fields
     */
    private ArrayList<Employee> parseEmployees(BufferedReader reader) throws IOException {
        ArrayList<Employee> employees = new ArrayList<>();
        String line;
        boolean isHeader = true;

        while ((line = reader.readLine()) != null) {
            if (isHeader) {
                isHeader = false;
                continue;
            }

            if (line.isBlank()) {
                continue;
            }

            String[] fields = line.split(",", -1);
            if (fields.length < 9) {
                continue;
            }

            String firstName = fields[0];
            String lastName = fields[1];
            String gender = fields[2];
            String email = fields[3];
            double salary = parseSalary(fields[4]);
            String originalDepartment = fields[5];
            String position = fields[6];
            String jobTitle = fields[7];
            String company = fields[8];

            DepartmentType departmentType = DepartmentType.fromCsvDepartment(originalDepartment);
            ManagerType managerType = ManagerType.fromCsvFields(jobTitle, position);

            Department department = new Department(departmentType, originalDepartment, company);
            Manager manager = new Manager(managerType, jobTitle);

            employees.add(new Employee(firstName, lastName, gender, email, salary, position, department, manager));
        }

        return employees;
    }

    private double parseSalary(String salaryText) {
        try {
            return Double.parseDouble(salaryText.trim());
        } catch (NumberFormatException exception) {
            return 0.0;
        }
    }
}
