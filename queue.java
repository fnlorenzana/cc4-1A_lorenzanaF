import java.util.Scanner;
import java.util.EmptyStackException;

class Queue {
    private Node front;
    private Node rear;

    private class Node {
        int data;
        Node next;
    }

    public Queue() {
        front = null;
        rear = null;
    }

    public void enqueue(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void displayQueue() {
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue queue = new Queue();
        while (true) {
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display queue");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Enter the value to enqueue: ");
                int value = scanner.nextInt();
                queue.enqueue(value);
            } else if (choice == 2) {
                try {
                    System.out.println("Dequeued value: " + queue.dequeue());
                } catch (EmptyStackException e) {
                    System.out.println("Queue is empty");
                }
            } else if (choice == 3) {
                queue.displayQueue();
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}