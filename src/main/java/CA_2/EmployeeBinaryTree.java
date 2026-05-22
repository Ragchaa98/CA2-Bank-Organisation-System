/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Dorjragchaa Chuluunbaatar
 */

public class EmployeeBinaryTree {
    private TreeNode root;

    private static class TreeNode {
        private Employee employee;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(Employee employee) {
            this.employee = employee;
        }
    }

    /**
     * Inserts each employee using breadth-first/level-order insertion
     */
    public void insertLevelOrder(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();

            if (current.left == null) {
                current.left = newNode;
                return;
            }
            queue.add(current.left);

            if (current.right == null) {
                current.right = newNode;
                return;
            }
            queue.add(current.right);
        }
    }

    /**
     * Displays employees level by level to show the hierarchy clearly
     */
    public void displayLevelOrder() {
        if (root == null) {
            System.out.println("The binary tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.println("\nLevel " + level + ":");

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.remove();
                System.out.println("  " + current.employee.toShortLine());

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            level++;
        }
    }

    public int getHeight() {
        return calculateHeight(root);
    }

    /**
     * Recursively calculates tree height
     */
    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(calculateHeight(node.left), calculateHeight(node.right));
    }

    public int getNodeCount() {
        return countNodes(root);
    }

    /**
     * Recursively counts all nodes in the tree
     */
    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}
