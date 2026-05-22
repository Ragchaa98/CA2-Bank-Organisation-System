/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public enum MenuOption {
    SORT(1, "Sort applicant list and display first 20 names"),
    SEARCH(2, "Search applicants by full or partial name"),
    ADD_RECORD(3, "Add a new bank employee record"),
    CREATE_BINARY_TREE(4, "Create employee hierarchy binary tree"),
    DISPLAY_ADDED_RECORDS(5, "Display newly added records"),
    EXIT(6, "Exit program");

    private final int number;
    private final String label;

    MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Converts numeric user input into a MenuOption value
     */
    public static MenuOption fromNumber(int number) {
        for (MenuOption option : values()) {
            if (option.number == number) {
                return option;
            }
        }
        return null;
    }

    /**
     * Prints menu directly from enum values to keep the menu structured
     */
    public static void printMenu() {
        System.out.println("\nPlease select an option:");
        for (MenuOption option : values()) {
            System.out.printf("%d. %s%n", option.number, option.label);
        }
    }
}
