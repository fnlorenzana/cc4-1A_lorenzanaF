import java.util.Scanner;

public class recordAskOnce {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // User input for alpha
        System.out.print("Enter the base address (alpha): ");
        int alpha = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // User input for esize
        System.out.print("Enter the size of each element (esize): ");
        int esize = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // User input for dimensions
        int[] dimensions = null;
        boolean validDimensions = false;
        while (!validDimensions) {
            System.out.print("Enter the dimensions separated by spaces: ");
            String dimensionsInput = scanner.nextLine();
            String[] dimensionsStr = dimensionsInput.trim().split("\\s+");
            try {
                dimensions = new int[dimensionsStr.length];
                for (int i = 0; i < dimensionsStr.length; i++) {
                    int dimension = Integer.parseInt(dimensionsStr[i]);
                    if (dimension >= 0) {
                        dimensions[i] = dimension;
                    } else {
                        throw new NumberFormatException();
                    }
                }
                validDimensions = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter non-negative integers only.");
                System.out.println();
            }
        }
        System.out.println();
        // Displaying the dimensions
        System.out.print("Your record is: A");
        for (int dimension : dimensions) {
            System.out.print("[" + dimension + "]");
        }
        
        System.out.println();
        // Displaying the total number of elements
        int totalElements = computeTotalElements(dimensions);
        System.out.println("Total number of elements: " + totalElements);
        System.out.println();
        
        // Label for address calculation
        System.out.println("Address Calculation");
        
        // User input for indices
        int[] indices = null;
        boolean validIndices = false;
        while (!validIndices) {
            System.out.print("Enter the indices separated by spaces: ");
            String indicesInput = scanner.nextLine();
            String[] indicesStr = indicesInput.trim().split("\\s+");
            if (indicesStr.length == dimensions.length) {
                indices = new int[indicesStr.length];
                try {
                    for (int i = 0; i < indicesStr.length; i++) {
                        int index = Integer.parseInt(indicesStr[i]);
                        if (index >= 0) {
                            indices[i] = index;
                        } else {
                            throw new NumberFormatException();
                        }
                    }
                    validIndices = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter non-negative integers only.");
                    System.out.println();
                }
            } else {
                System.out.println("Invalid input! Please enter the correct number of indices.");
                System.out.println();
            }
        }
        
        System.out.println();
        // Finding the address based on user input indices
        int address = computeAddress(alpha, esize, dimensions, indices);
        
        // Displaying the original address separately
        System.out.println("Address of A" + formatIndices(indices) + ": " + address + " bytes");
        
        scanner.close();
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
    
    // Method to compute the total number of elements given the dimensions
    public static int computeTotalElements(int[] dimensions) {
        int totalElements = 1;
        for (int dimension : dimensions) {
            totalElements *= dimension;
        }
        return totalElements;
    }
}
