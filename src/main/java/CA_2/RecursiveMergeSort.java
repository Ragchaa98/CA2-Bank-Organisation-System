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

public class RecursiveMergeSort {

    public ArrayList<Employee> sortByName(ArrayList<Employee> employees) {
        if (employees == null || employees.size() <= 1) {
            return employees == null ? new ArrayList<>() : new ArrayList<>(employees);
        }

        int middle = employees.size() / 2;
        ArrayList<Employee> left = new ArrayList<>(employees.subList(0, middle));
        ArrayList<Employee> right = new ArrayList<>(employees.subList(middle, employees.size()));

        return merge(sortByName(left), sortByName(right));
    }

    /**
     * Merges two sorted halves into one fully sorted list
     */
    private ArrayList<Employee> merge(ArrayList<Employee> left, ArrayList<Employee> right) {
        ArrayList<Employee> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            Employee leftEmployee = left.get(leftIndex);
            Employee rightEmployee = right.get(rightIndex);

            if (compareEmployees(leftEmployee, rightEmployee) <= 0) {
                result.add(leftEmployee);
                leftIndex++;
            } else {
                result.add(rightEmployee);
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex++));
        }

        return result;
    }

    /**
     * Uses email as a secondary comparison key when duplicate names exist
     */
    private int compareEmployees(Employee first, Employee second) {
        int nameComparison = first.getFullName().compareToIgnoreCase(second.getFullName());
        if (nameComparison != 0) {
            return nameComparison;
        }
        return first.getEmail().compareToIgnoreCase(second.getEmail());
    }
}
