/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.Scanner;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads an integer within a valid range and keeps asking until input is valid
     */
    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /**
     * Requires a realistic full name with at least first name and last name
     */
    public String readFullName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim().replaceAll("\\s+", " ");

            if (isValidFullName(name)) {
                return name;
            }

            System.out.println("Invalid name. Please enter first name and last name using letters only.");
        }
        
        
    }

    private boolean isValidFullName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        String[] parts = name.split(" ");
        if (parts.length < 2) {
            return false;
        }

        return name.matches("[A-Za-z]+([ '-][A-Za-z]+)+");
    }

    /**
     * Reads non-empty search text for exact or partial name searching
     */
    public String readSearchText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = scanner.nextLine().trim();

            if (!text.isBlank()) {
                return text;
            }

            System.out.println("Search text cannot be empty.");
        }
    }
    
    /**
    * Reads and validates a single name part such as first name or last name
    * Only letters, apostrophes, and hyphens are accepted
    */
    public String readNamePart(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();

            if (isValidNamePart(name)) {
                return name;
            }

            System.out.println("Invalid name. Please use letters only. Example: John or O'Connor.");
        }
    }

    /**
     * Validates one name part
     * Spaces and numbers are not allowed here
     * because first name and last name are entered separately
     */
    private boolean isValidNamePart(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        return name.matches("[\\p{L}]+(['-][\\p{L}]+)*");
    }
}

