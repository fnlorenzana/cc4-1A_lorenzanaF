import java.util.Scanner;
import java.util.EmptyStackException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the stack: ");
        int size = scanner.nextInt();
        
        MyStack<Object> stack = new MyStack<>(size);
        
        boolean terminate = false;
        while (!terminate) {
            System.out.println("\nStack Operations:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display Stack");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    if (!stack.isFull()) {
                        System.out.print("Enter the element to push: ");
                        if (scanner.hasNextInt()) {
                            stack.push(scanner.nextInt());
                        } else if (scanner.hasNextDouble()) {
                            stack.push(scanner.nextDouble());
                        } else if (scanner.hasNextBoolean()) {
                            stack.push(scanner.nextBoolean());
                        } else {
                            stack.push(scanner.next());
                        }
                        System.out.println("Stack after push:");
                        stack.printStack();
                    } else {
                        System.out.println("Stack is full. Cannot push element.");
                    }
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        Object poppedElement = stack.pop();
                        System.out.println("Popped element: " + poppedElement);
                        System.out.println("Stack after pop:");
                        stack.printStack();
                    } else {
                        System.out.println("Stack is empty. Cannot pop element.");
                    }
                    break;
                case 3:
                    if (!stack.isEmpty()) {
                        System.out.println("Contents of the stack:");
                        stack.printStack();
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;
                case 4:
                    terminate = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        
        scanner.close();
    }
}

class MyStack<T> {
    private int maxSize;
    private Object[] stackArray;
    private int top;

    public MyStack(int size) {
        maxSize = size;
        stackArray = new Object[maxSize];
        top = -1;
    }

    public void push(T element) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        stackArray[++top] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) stackArray[top--];
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }
}
