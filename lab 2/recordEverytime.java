import java.util.InputMismatchException;
import java.util.Scanner;

public class recordEverytime {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // User input for alpha
            System.out.print("Enter the base address (alpha): ");
            int alpha = scanner.nextInt();
            
            // User input for esize
            System.out.print("Enter the size of each element (esize): ");
            int esize = scanner.nextInt();
            
            // User input for dimensions
            System.out.print("Enter the number of dimensions: ");
            int numDimensions = scanner.nextInt();
            int[] dimensions = new int[numDimensions];
            int totalElements = 1;
            for (int i = 0; i < numDimensions; i++) {
                dimensions[i] = readNonNegativeInteger(scanner, "Enter the size of dimension " + (i + 1) + ": ");
                totalElements *= dimensions[i];
            }
            System.out.println();
            // Displaying the dimensions
            System.out.print("Your record is: A");
            for (int i = 0; i < dimensions.length; i++) {
                System.out.print("[" + dimensions[i] + "]");
            }
            
            // Displaying the total number of elements
            System.out.println("\nTotal number of elements: " + totalElements);
            System.out.println(); // New line after displaying total elements
            
            // Label for address calculation
            System.out.println("Address Calculation");
            
            // User input for indices
            int[] indices = new int[numDimensions];
            for (int i = 0; i < numDimensions; i++) {
                int dimensionSize = dimensions[i];
                indices[i] = readIndex(scanner, "Enter the index for dimension " + (i + 1) + " (maximum " + (dimensionSize - 1) + "): ", dimensionSize);
            }
            System.out.println();
            // Finding the address based on user input indices
            int address = computeAddress(alpha, esize, dimensions, indices);
            
            // Displaying the original address separately
            System.out.println("Address of A" + formatIndices(indices) + ": " + address + " bytes");
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter integers only.");
        } finally {
            scanner.close();
        }
    }
    
    // Method to read a non-negative integer from input
    public static int readNonNegativeInteger(Scanner scanner, String message) {
        int input;
        do {
            System.out.print(message);
            input = scanner.nextInt();
            if (input < 0) {
                System.out.println("Invalid input! Please enter a non-negative integer.");
                System.out.println(); // New line after error message
            }
        } while (input < 0);
        return input;
    }
    
    // Method to read an index within the specified range
    public static int readIndex(Scanner scanner, String message, int dimensionSize) {
        int index;
        do {
            index = readNonNegativeInteger(scanner, message);
            if (index >= dimensionSize) {
                System.out.println("Invalid input! Index must be less than " + dimensionSize + ".");
                System.out.println(); // New line after error message
            }
        } while (index >= dimensionSize);
        return index;
    }
    
    // Method to format indices for display
    public static String formatIndices(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int index : indices) {
            sb.append("[").append(index).append("]");
        }
        return sb.toString();
    }
    
    // Method to compute the memory address for a given index
    public static int computeAddress(int alpha, int esize, int[] dimensions, int[] indices) {
        int address = alpha;
        int stride = esize;
        for (int i = dimensions.length - 1; i >= 0; i--) {
            address += indices[i] * stride;
            stride *= dimensions[i];
        }
        return address;
    }
}
