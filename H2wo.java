import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    void delete(int data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = deleteRec(root.left, data);
        else if (data > root.data)
            root.right = deleteRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            Node temp = minValueNode(root.right);
            root.data = temp.data;
            root.right = deleteRec(root.right, temp.data);
        }
        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node maxValueNode(Node node) {
        Node current = node;
        while (current.right != null)
            current = current.right;
        return current;
    }

    void displayArray() {
        int height = height();
        int size = (int)Math.pow(2, height) - 1;
        int[] arr = new int[size];
        Arrays.fill(arr, 0);
        displayArrayRec(root, arr, 0);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    void displayArrayRec(Node root, int[] arr, int i) {
        if (root == null) {
            return;
        }

        arr[i] = root.data;
        displayArrayRec(root.left, arr, 2 * i + 1);
        displayArrayRec(root.right, arr, 2 * i + 2);

        if (root.left == null && 2 * i + 1 < arr.length) {
            arr[2 * i + 1] = 0;
        }
        if (root.right == null && 2 * i + 1 < arr.length) {
            arr[2 * i + 2] = 0;
        }
    }

    void preorder() {
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    void postorder() {
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    int height() {
        return heightRec(root);
    }

    int heightRec(Node root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = heightRec(root.left);
            int rightHeight = heightRec(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch;

        do {
            BinarySearchTree bst = new BinarySearchTree();
            System.out.print("Enter int elements to insert(Seperate by space): ");
            String[] inputs = sc.nextLine().split(" ");

            for (String input : inputs) {
                try {
                    int data = Integer.parseInt(input);
                    bst.insert(data);
                } catch (NumberFormatException e) {
                    System.out.println(input + " invalid");
                }
            }

            System.out.print("\n1-D Array: ");
            bst.displayArray();

            System.out.print("\nPreorder: ");
            bst.preorder();

            System.out.print("Inorder: ");
            bst.inorder();

            System.out.print("Postorder: ");
            bst.postorder();

            System.out.println("\n");
            System.out.print("Do you want to delete? (Y/N): ");
            char del = sc.nextLine().charAt(0);

            System.out.println();
            if (del == 'y' || del == 'Y') {
                System.out.print("Enter elements to delete separated by spaces: ");
                String[] deletes = sc.nextLine().split(" ");

                for (String delete : deletes) {
                    try {
                        int datar = Integer.parseInt(delete);
                        bst.root = bst.deleteRec(bst.root, datar);
                    } catch (NumberFormatException e) {
                        System.out.println(delete + " invalid.");
                    }
                }
            }

            System.out.print("\n1-D Array: ");
            bst.displayArray();

            System.out.print("\nPreorder: ");
            bst.preorder();

            System.out.print("Inorder: ");
            bst.inorder();

            System.out.print("Postorder: ");
            bst.postorder();

            System.out.print("\nDo you want to try again? (Y/N): ");
            ch = sc.nextLine().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }
}
