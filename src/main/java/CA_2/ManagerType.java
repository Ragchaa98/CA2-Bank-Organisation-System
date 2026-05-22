/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public enum ManagerType {
    BANK_MANAGER(1, "Bank Manager"),
    ASSISTANT_MANAGER(2, "Assistant Manager"),
    TEAM_LEAD(3, "Team Lead");

    private final int number;
    private final String displayName;

    ManagerType(int number, String displayName) {
        this.number = number;
        this.displayName = displayName;
    }

    public int getNumber() {
        return number;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Converts a menu number into a valid manager type
     */
    public static ManagerType fromNumber(int number) {
        for (ManagerType type : values()) {
            if (type.number == number) {
                return type;
            }
        }
        return null;
    }

    /**
     * Maps the generic sample-file job title/position fields into the three
     * chosen bank manager categories
     */
    public static ManagerType fromCsvFields(String jobTitle, String position) {
        String title = jobTitle == null ? "" : jobTitle.trim().toLowerCase();
        String level = position == null ? "" : position.trim().toLowerCase();

        if (title.contains("assistant")) {
            return ASSISTANT_MANAGER;
        }

        if (title.contains("team lead") || level.equals("middle") || level.equals("junior") || level.equals("intern")) {
            return TEAM_LEAD;
        }

        // Senior managers, managers, contractors, and unclassified records report to the Bank Manager category.
        return BANK_MANAGER;
    }

    public static void printOptions() {
        System.out.println("\nPlease select from the following Bank Management Staff:");
        for (ManagerType type : values()) {
            System.out.printf("%d. %s%n", type.number, type.displayName);
        }
    }
}
