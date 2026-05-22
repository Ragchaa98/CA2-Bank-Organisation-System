/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public enum DepartmentType {
    CUSTOMER_SERVICE(1, "Customer Service"),
    FOREIGN_EXCHANGE(2, "Foreign Exchange"),
    HR(3, "HR");

    private final int number;
    private final String displayName;

    DepartmentType(int number, String displayName) {
        this.number = number;
        this.displayName = displayName;
    }

    public int getNumber() {
        return number;
    }

    public String getDisplayName() {
        return displayName;
    }

    //Converts menu number input into a valid department type.
    public static DepartmentType fromNumber(int number) {
        for (DepartmentType department : values()) {
            if (department.number == number) {
                return department;
            }
        }
        return null;
    }

    //Maps the generic Applicants_Form.txt departments into three bank departments.
    public static DepartmentType fromCsvDepartment(String departmentName) {
        String department = departmentName == null ? "" : departmentName.trim().toLowerCase();

        // HR records from the file are kept as HR in the bank structure.
        if (department.contains("hr")) {
            return HR;
        }

        // Finance-related departments are grouped under Foreign Exchange.
        if (department.contains("finance") || department.contains("accounting") || department.contains("sales")) {
            return FOREIGN_EXCHANGE;
        }

        // All other departments are grouped under Customer Service.
        return CUSTOMER_SERVICE;
    }
    
    // Displays the available bank departments for validated user input.
    public static void printOptions() {
        System.out.println("\nPlease select the Bank Department:");
        for (DepartmentType department : values()) {
            System.out.printf("%d. %s%n", department.number, department.displayName);
        }
    }
}
