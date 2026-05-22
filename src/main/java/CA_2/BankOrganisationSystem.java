/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class BankOrganisationSystem {
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator(scanner);
    private final FileHandler fileHandler = new FileHandler();
    private final RecursiveMergeSort sorter = new RecursiveMergeSort();
    private final RecursiveNameSearch searcher = new RecursiveNameSearch();

    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Employee> sortedEmployees = new ArrayList<>();
    private final ArrayList<Employee> addedEmployees = new ArrayList<>();

    //Starts the program by loading the required file, then showing the menu.
    public void start() {
        System.out.println("CA2 Algorithms & Constructs - Bank System");
        System.out.println("Required file name: " + FileHandler.EXPECTED_FILE_NAME);

        if (!loadRequiredFile()) {
            System.out.println("Program stopped because the required file could not be loaded.");
            return;
        }

        runMenuLoop();
    }

    private boolean loadRequiredFile() {
        System.out.print("\nPlease enter the filename to read: ");
        String fileName = scanner.nextLine().trim();

        try {
            employees = fileHandler.readApplicantsFile(fileName);
            sortedEmployees = sorter.sortByName(employees);
            System.out.println("File read successfully. Total records loaded: " + employees.size());
            return true;
        } catch (IOException exception) {
            System.out.println("File read failed: " + exception.getMessage());
            return false;
        }
    }

    private void runMenuLoop() {
        boolean running = true;

        while (running) {
            MenuOption.printMenu();
            int choice = validator.readIntInRange("Enter choice: ", 1, MenuOption.values().length);
            MenuOption selectedOption = MenuOption.fromNumber(choice);

            switch (selectedOption) {
                case SORT -> sortAndDisplayFirstTwenty();
                case SEARCH -> searchEmployees();
                case ADD_RECORD -> addNewEmployee();
                case CREATE_BINARY_TREE -> createAndDisplayBinaryTree();
                case DISPLAY_ADDED_RECORDS -> displayAddedEmployees();
                case EXIT -> {
                    System.out.println("Exiting program. Goodbye.");
                    running = false;
                }
            }
        }
    }

    /**
     * Sorts all records recursively and displays only the first 20 names.
     */
    private void sortAndDisplayFirstTwenty() {
        sortedEmployees = sorter.sortByName(employees);
        System.out.println("\nSORT selected");
        System.out.println("First 20 names after recursive Merge Sort:");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-4s %-24s | %-18s | %-18s%n", "No.", "Name", "Manager Type", "Department");
        System.out.println("---------------------------------------------------------------");

        int limit = Math.min(20, sortedEmployees.size());
        for (int i = 0; i < limit; i++) {
            System.out.printf("%-4d %s%n", i + 1, sortedEmployees.get(i).toShortLine());
        }
    }

    /**
     * Supports exact full-name search and partial name search.
     */
    private void searchEmployees() {
        sortedEmployees = sorter.sortByName(employees);

        System.out.println("\nSEARCH selected");
        System.out.println("1. Exact full-name search");
        System.out.println("2. Partial name search");
        int searchChoice = validator.readIntInRange("Choose search type: ", 1, 2);

        if (searchChoice == 1) {
            String fullName = validator.readSearchText("Enter full name to search: ");
            ArrayList<Employee> results = searcher.binarySearchExact(sortedEmployees, fullName);
            displaySearchResults(results, "Exact search results");
        } else {
            String partialName = validator.readSearchText("Enter full or partial name to search: ");
            ArrayList<Employee> results = searcher.recursivePartialSearch(sortedEmployees, partialName);
            displaySearchResults(results, "Partial search results");
        }
    }

    private void displaySearchResults(ArrayList<Employee> results, String heading) {
        System.out.println("\n" + heading + ":");
        if (results.isEmpty()) {
            System.out.println("No matching records were found.");
            return;
        }

        for (Employee employee : results) {
            System.out.println(employee.toDetailedLine());
        }
    }

    /**
 * Adds a new bank employee using separate first name and last name inputs.
 */
private void addNewEmployee() {
    System.out.println("\nADD RECORD selected");

    String firstName = validator.readNamePart("Please input the Employee First Name: ");
    String lastName = validator.readNamePart("Please input the Employee Last Name: ");

    ManagerType.printOptions();
    int managerChoice = validator.readIntInRange("Choose Management Staff: ", 1, ManagerType.values().length);
    ManagerType managerType = ManagerType.fromNumber(managerChoice);

    DepartmentType.printOptions();
    int departmentChoice = validator.readIntInRange("Choose Department: ", 1, DepartmentType.values().length);
    DepartmentType departmentType = DepartmentType.fromNumber(departmentChoice);

    Department department = new Department(departmentType, departmentType.getDisplayName(), "Bank Organisation");
    Manager manager = new Manager(managerType, managerType.getDisplayName());

    Employee employee = new Employee(
            firstName,
            lastName,
            "N/A",
            "N/A",
            0.0,
            "New Input",
            department,
            manager
    );

    employees.add(employee);
    addedEmployees.add(employee);

    // Update the sorted list after adding a new record.
    sortedEmployees = sorter.sortByName(employees);

    System.out.printf("\n\"%s\" has been added as \"%s\" to \"%s\" successfully!%n",
            employee.getFullName(),
            employee.getManagerTypeName(),
            employee.getDepartmentName()
    );

    displayAddedEmployees();
}

    private void displayAddedEmployees() {
        System.out.println("\nNewly added records:");
        if (addedEmployees.isEmpty()) {
            System.out.println("No new records have been added yet.");
            return;
        }

        for (int i = 0; i < addedEmployees.size(); i++) {
            System.out.printf("%-4d %s%n", i + 1, addedEmployees.get(i).toDetailedLine());
        }
    }

    /**
     * Inserts at least 20 sorted records into the binary tree using level-order insertion.
     */
    private void createAndDisplayBinaryTree() {
        sortedEmployees = sorter.sortByName(employees);

        if (sortedEmployees.size() < 20) {
            System.out.println("At least 20 records are required to create the binary tree.");
            return;
        }

        EmployeeBinaryTree tree = new EmployeeBinaryTree();
        for (int i = 0; i < 20; i++) {
            tree.insertLevelOrder(sortedEmployees.get(i));
        }

        System.out.println("\nBinary Tree Hierarchy created using first 20 sorted records:");
        tree.displayLevelOrder();
        System.out.println("\nTree height: " + tree.getHeight());
        System.out.println("Total node count: " + tree.getNodeCount());
    }
}
