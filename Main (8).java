import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyQueue<Object> queue = new MyQueue<>();
        
        boolean terminate = false;
        Scanner scanner = new Scanner(System.in);
        while (!terminate) {
            System.out.println("\nQueue Operations:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display Queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter the element to enqueue: ");
                    if (scanner.hasNextInt()) {
                        queue.enqueue(scanner.nextInt());
                    } else if (scanner.hasNextDouble()) {
                        queue.enqueue(scanner.nextDouble());
                    } else if (scanner.hasNextBoolean()) {
                        queue.enqueue(scanner.nextBoolean());
                    } else {
                        queue.enqueue(scanner.next());
                    }
                    System.out.println("Queue after enqueue:");
                    queue.printQueue();
                    break;
                case 2:
                    if (!queue.isEmpty()) {
                        Object dequeuedElement = queue.dequeue();
                        System.out.println("Dequeued element: " + dequeuedElement);
                        System.out.println("Queue after dequeue:");
                        queue.printQueue();
                    } else {
                        System.out.println("Queue is empty. Cannot dequeue element.");
                    }
                    break;
                case 3:
                    if (!queue.isEmpty()) {
                        System.out.println("Contents of the queue:");
                        queue.printQueue();
                    } else {
                        System.out.println("Queue is empty.");
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

class MyQueue<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] queueArray;
    private int front;
    private int rear;
    private int currentSize;

    public MyQueue() {
        queueArray = new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public void enqueue(T element) {
        if (currentSize == queueArray.length) {
            resize();
        }
        rear = (rear + 1) % queueArray.length;
        queueArray[rear] = element;
        currentSize++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T dequeuedElement = (T) queueArray[front];
        front = (front + 1) % queueArray.length;
        currentSize--;
        return dequeuedElement;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return (T) queueArray[front];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void resize() {
        int newCapacity = queueArray.length * 2;
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < currentSize; i++) {
            newArray[i] = queueArray[(front + i) % queueArray.length];
        }
        front = 0;
        rear = currentSize - 1;
        queueArray = newArray;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue (front to rear): ");
        int index = front;
        for (int i = 0; i < currentSize; i++) {
            System.out.print(queueArray[index] + " ");
            index = (index + 1) % queueArray.length;
        }
        System.out.println();
    }
}
