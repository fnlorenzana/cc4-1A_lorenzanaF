import java.util.Scanner;
import java.util.EmptyStackException;

class Stack {
    private Node top;

    private class Node {
        int data;
        Node next;
    }

    public Stack() {
        top = null;
    }

    public void push(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void displayStack() {
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack stack = new Stack();
        while (true) {
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display stack");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Enter the value to push: ");
                int value = scanner.nextInt();
                stack.push(value);
            } else if (choice == 2) {
                try {
                    System.out.println("Popped value: " + stack.pop());
                } catch (EmptyStackException e) {
                    System.out.println("Stack is empty");
                }
            } else if (choice == 3) {
                stack.displayStack();
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}