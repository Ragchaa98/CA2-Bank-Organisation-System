/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.ArrayList;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class RecursiveNameSearch {

    /**
     * Performs recursive binary search for an exact full name
     */
    public ArrayList<Employee> binarySearchExact(ArrayList<Employee> sortedEmployees, String fullName) {
        ArrayList<Employee> results = new ArrayList<>();
        if (sortedEmployees == null || sortedEmployees.isEmpty() || fullName == null || fullName.isBlank()) {
            return results;
        }

        String target = fullName.trim().toLowerCase();
        int foundIndex = findExactIndex(sortedEmployees, target, 0, sortedEmployees.size() - 1);

        if (foundIndex == -1) {
            return results;
        }

        collectExactMatchesLeft(sortedEmployees, target, foundIndex - 1, results);
        results.add(sortedEmployees.get(foundIndex));
        collectExactMatchesRight(sortedEmployees, target, foundIndex + 1, results);
        return results;
    }

    private int findExactIndex(ArrayList<Employee> employees, String target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int middle = low + (high - low) / 2;
        int comparison = employees.get(middle).getSearchKey().compareTo(target);

        if (comparison == 0) {
            return middle;
        }

        if (comparison > 0) {
            return findExactIndex(employees, target, low, middle - 1);
        }

        return findExactIndex(employees, target, middle + 1, high);
    }

    /**
     * Recursively collects duplicate exact-name records before the found index
     */
    private void collectExactMatchesLeft(ArrayList<Employee> employees, String target, int index, ArrayList<Employee> results) {
        if (index < 0 || !employees.get(index).getSearchKey().equals(target)) {
            return;
        }

        collectExactMatchesLeft(employees, target, index - 1, results);
        results.add(employees.get(index));
    }

    /**
     * Recursively collects duplicate exact-name records after the found index
     */
    private void collectExactMatchesRight(ArrayList<Employee> employees, String target, int index, ArrayList<Employee> results) {
        if (index >= employees.size() || !employees.get(index).getSearchKey().equals(target)) {
            return;
        }

        results.add(employees.get(index));
        collectExactMatchesRight(employees, target, index + 1, results);
    }

    /**
     * Partial search supports first name, last name, and incomplete text input
     */
    public ArrayList<Employee> recursivePartialSearch(ArrayList<Employee> employees, String query) {
        ArrayList<Employee> results = new ArrayList<>();
        if (employees == null || employees.isEmpty() || query == null || query.isBlank()) {
            return results;
        }

        searchPartial(employees, query.trim().toLowerCase(), 0, employees.size() - 1, results);
        return results;
    }

    private void searchPartial(ArrayList<Employee> employees, String query, int low, int high, ArrayList<Employee> results) {
        if (low > high) {
            return;
        }

        int middle = low + (high - low) / 2;
        Employee current = employees.get(middle);

        if (current.getSearchKey().contains(query)) {
            results.add(current);
        }

        // Both sides must be searched because substring matches are not ordered like exact full names.
        searchPartial(employees, query, low, middle - 1, results);
        searchPartial(employees, query, middle + 1, high, results);
    }
}
