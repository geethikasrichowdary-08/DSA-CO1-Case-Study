public class SubmissionPercentileTree
 {

    // Node structure for Binary Search Tree
    static class Node {

        int data;
        int size;

        Node left;
        Node right;

        Node(int value) {
            data = value;
            size = 1;
            left = null;
            right = null;
        }
    }

    // Insert nodes into tree
    static Node insert(Node root, int value) {

        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        }

        else {
            root.right = insert(root.right, value);
        }

        // Update subtree size
        root.size = 1 + getSize(root.left)
                       + getSize(root.right);

        return root;
    }

    // Get subtree size
    static int getSize(Node root) {

        if (root == null) {
            return 0;
        }

        return root.size;
    }

    // Count submissions less than given value
    static int countLess(Node root, int value) {

        if (root == null) {
            return 0;
        }

        if (root.data < value) {

            return 1
                   + countLess(root.left, value)
                   + countLess(root.right, value);
        }

        else {

            return countLess(root.left, value);
        }
    }

    // Print tree structure
    static void printTree(Node root, int space) {

        if (root == null) {
            return;
        }

        space += 10;

        printTree(root.right, space);

        System.out.println();

        for (int i = 10; i < space; i++) {
            System.out.print(" ");
        }

        System.out.println(root.data + "[" + root.size + "]");

        printTree(root.left, space);
    }

    public static void main(String[] args) {

        Node root = null;

        // Submission execution times
        int submissions[] = {
            120, 85, 200, 65, 150,
            95, 180, 75, 110, 240, 90
        };

        // Insert into tree
        for (int value : submissions) {

            root = insert(root, value);
        }

        System.out.println("\nCODEFORCES / LEETCODE STYLE");
        System.out.println("PERCENTILE COMPUTATION SYSTEM");

        System.out.println("\nSubmission Times:");

        for (int value : submissions) {
            System.out.print(value + " ");
        }

        System.out.println("\n\nTREE STRUCTURE WITH SUBTREE SIZE\n");

        printTree(root, 0);

        // Percentile Query
        int mySubmission = 100;

        int better = countLess(root, mySubmission);

        int total = getSize(root);

        double percentile =
                100.0 * (total - better) / total;

        System.out.println("\n--------------------------------");

        System.out.println("\nPERCENTILE QUERY RESULT");

        System.out.println("\nNew Submission Time : "
                           + mySubmission + " ms");

        System.out.println("Submissions Faster Than 100 ms : "
                           + better);

        System.out.println("Total Submissions : "
                           + total);

        System.out.printf("Calculated Percentile : %.2f %%\n",
                           percentile);

        System.out.println("\nExecution Completed Successfully");
    }
}