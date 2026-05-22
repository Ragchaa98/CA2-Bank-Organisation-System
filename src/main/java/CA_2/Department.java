/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class Department {
    // Stores the controlled department type selected from the DepartmentType enum.
    private DepartmentType departmentType;
    
    // Keeps the original department name read from the input file.
    private String originalDepartmentName;
    
    // Stores the company or organisation name related to this department.
    private String companyName;

    // It stores both the mapped enum department type and the original file data.
    public Department(DepartmentType departmentType, String originalDepartmentName, String companyName) {
        this.departmentType = departmentType;
        this.originalDepartmentName = clean(originalDepartmentName);
        this.companyName = clean(companyName);
    }
    
    // This prevents empty values from causing unclear output in the program.
    private String clean(String value) {
        return value == null || value.isBlank() ? "N/A" : value.trim();
    }
    
    // Returns the enum department type used by the bank system.
    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    // Returns the display name of the mapped bank department.
    public String getDepartmentName() {
        return departmentType.getDisplayName();
    }

    // This is useful for checking how file data was mapped into the bank structure.
    public String getOriginalDepartmentName() {
        return originalDepartmentName;
    }

    // Returns the company name read from the file or entered by the user.
    public String getCompanyName() {
        return companyName;
    }
}
