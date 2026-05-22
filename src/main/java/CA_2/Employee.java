/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class Employee {
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private double salary;
    private String position;
    
    // Links each employee to a Department object.
    private Department department;
    
    // Links each employee to a Manager object.
    private Manager manager;

    // Creates an Employee object using data read from the file or entered by the user.
    public Employee(String firstName, String lastName, String gender, String email, double salary,
                    String position, Department department, Manager manager) {
        this.firstName = clean(firstName);
        this.lastName = clean(lastName);
        this.gender = clean(gender);
        this.email = clean(email);
        this.salary = salary;
        this.position = clean(position);
        this.department = department;
        this.manager = manager;
    }

    // This keeps employee records readable and prevents empty output.
    private String clean(String value) {
        return value == null || value.isBlank() ? "N/A" : value.trim();
    }

    /**
    * Combines first name and last name for sorting, searching, and display
    */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
    * Returns a lowercase name key used by the recursive search algorithm
    */
    public String getSearchKey() {
        return getFullName().toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    /**
    * Returns the employee's mapped manager type for display in search and tree output
    */
    public String getManagerTypeName() {
        return manager.getManagerName();
    }

    // Returns the employee's mapped bank department for display in search and tree output.
    public String getDepartmentName() {
        return department.getDepartmentName();
    }

    public Manager getManager() {
        return manager;
    }

    public Department getDepartment() {
        return department;
    }

    /**
     * Formats employee data in a short table row format
     * This is used for sorted list and binary tree display
     */
    public String toShortLine() {
        return String.format("%-24s | %-18s | %-18s", getFullName(), getManagerTypeName(), getDepartmentName());
    }

    // Formats employee data with more detail for search results.
    public String toDetailedLine() {
        return String.format(
                "Name: %-24s | Manager Type: %-18s | Department: %-18s | Email: %-32s | Original Role: %s",
                getFullName(),
                getManagerTypeName(),
                getDepartmentName(),
                email,
                manager.getSourceJobTitle()
        );
    }

    // Returns the detailed employee information when the object is printed.
    @Override
    public String toString() {
        return toDetailedLine();
    }
}
