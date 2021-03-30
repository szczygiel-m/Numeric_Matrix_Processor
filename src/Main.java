import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice = 1;

        while (choice != 0) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.println("Your choice: ");
            choice = input.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    CalculatorEngine.addMatrices();
                    break;
                case 2:
                    CalculatorEngine.multiplyMatrix();
                    break;
                case 3:
                    CalculatorEngine.multiplyMatrices();
                    break;
                case 4:
                    CalculatorEngine.transposeMatrix();
                    break;
                case 5:
                    CalculatorEngine.calculateDeterminant();
                    break;
                case 6:
                    CalculatorEngine.inverseMatrix();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong choice!");
                    break;
            }
            System.out.println();
        }
    }
}
