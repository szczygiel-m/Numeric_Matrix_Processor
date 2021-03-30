import java.util.Scanner;

public class CalculatorEngine {

    static double determinant = 0;
    static Scanner input = new Scanner(System.in);

    public static void inverseMatrix() {
        System.out.println("Enter size of matrix: ");
        int matrixSize = input.nextInt();
        double[][] matrix = new double[matrixSize][matrixSize];
        System.out.println("Enter the elements of matrix: ");

        for (int i = 0; i < matrixSize; i++)
            for (int j = 0; j < matrixSize; j++)
                matrix[i][j] = input.nextDouble();

        double[][] invertedMatrix = invert(matrix);

        System.out.println("The inverse is: ");

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                System.out.print(invertedMatrix[i][j] + "  ");
            }
            System.out.println();
        }

        input.close();
    }

    public static double[][] invert(double[][] matrix) {
        int matrixLength = matrix.length;
        double[][] x = new double[matrixLength][matrixLength];
        double[][] b = new double[matrixLength][matrixLength];
        int[] index = new int[matrixLength];

        for (int i = 0; i < matrixLength; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(matrix, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < matrixLength - 1; ++i)
            for (int j = i + 1; j < matrixLength; ++j)
                for (int k = 0; k < matrixLength; ++k)
                    b[index[j]][k]
                            -= matrix[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < matrixLength; ++i) {
            x[matrixLength - 1][i] = b[index[matrixLength - 1]][i] / matrix[index[matrixLength - 1]][matrixLength - 1];

            for (int j = matrixLength - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];

                for (int k = j + 1; k < matrixLength; ++k) {
                    x[j][i] -= matrix[index[j]][k] * x[k][i];
                }

                x[j][i] /= matrix[index[j]][j];
            }
        }

        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    public static void gaussian(double[][] a, int[] index) {
        int indexLength = index.length;
        double[] c = new double[indexLength];

        // Initialize the index
        for (int i = 0; i < indexLength; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < indexLength; ++i) {
            double c1 = 0;

            for (int j = 0; j < indexLength; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }

            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < indexLength - 1; ++j) {
            double pi1 = 0;

            for (int i = j; i < indexLength; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;

            for (int i = j + 1; i < indexLength; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < indexLength; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public static void addMatrices() {
        System.out.println("Enter size of first matrix: ");
        int x = input.nextInt();
        int y = input.nextInt();
        double[][] firstMatrix = new double[x][y];

        int i, j;

        System.out.println("Enter first matrix: ");
        for (i = 0; i < x; i++) {
            for (j = 0; j < y; j++) {
                firstMatrix[i][j] = input.nextDouble();
            }
        }

        System.out.println("Enter size of second matrix ");
        int k = input.nextInt();
        int l = input.nextInt();
        double[][] secondMatrix = new double[k][l];

        if (x == k && y == l) {
            System.out.println("Enter second matrix: ");
            for (i = 0; i < k; i++) {
                for (j = 0; j < l; j++) {
                    secondMatrix[i][j] = input.nextDouble();
                }
            }

            for (i = 0; i < k; i++) {
                for (j = 0; j < l; j++) {
                    secondMatrix[i][j] += firstMatrix[i][j];
                    System.out.print(secondMatrix[i][j] + " ");
                }
                System.out.println();
            }


        } else {
            System.out.println("ERROR");
        }
    }

    public static void multiplyMatrix() {
        System.out.println("Enter size of matrix: ");
        int matrixLength = input.nextInt();
        int matrixWidth = input.nextInt();
        double[][] matrix = new double[matrixLength][matrixWidth];

        int i, j;

        System.out.println("Enter matrix: ");

        for (i = 0; i < matrixLength; i++) {
            for (j = 0; j < matrixWidth; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }

        System.out.println("Enter multiplication");

        int multiplication = input.nextInt();

        for (i = 0; i < matrixLength; i++) {
            for (j = 0; j < matrixWidth; j++) {
                matrix[i][j] *= multiplication;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void multiplyMatrices() {
        System.out.println("Enter size of first matrix: ");

        int matrixLength = input.nextInt();
        int matrixWidth = input.nextInt();
        double[][] array = new double[matrixLength][matrixWidth];


        System.out.println("Enter first matrix: ");

        for (int i = 0; i < matrixLength; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                array[i][j] = input.nextDouble();
            }
        }

        System.out.println("Enter size of second matrix ");

        int secondMatrixLength = input.nextInt();
        int secondMatrixWidth = input.nextInt();
        double[][] secondMatrix = new double[secondMatrixLength][secondMatrixWidth];

        System.out.println("Enter second matrix: ");

        for (int i = 0; i < secondMatrixLength; i++) {
            for (int j = 0; j < secondMatrixWidth; j++) {
                secondMatrix[i][j] = input.nextDouble();
            }
        }

        double[][] resultMatrix = new double[matrixLength][secondMatrixWidth];

        if (matrixWidth != secondMatrixLength) {
            System.out.println("Cannot multiply this matrices!");
        } else {
            for (int i = 0; i < matrixLength; i++) {
                for (int j = 0; j < secondMatrixWidth; j++) {
                    for (int z = 0; z < matrixWidth; z++) {
                        resultMatrix[i][j] += array[i][z] * secondMatrix[z][j];
                    }
                    System.out.print(resultMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void transposeMatrix() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice: ");
        int choice = input.nextInt();

        System.out.println("Enter size of matrix: ");
        int matrixLength = input.nextInt();
        int matrixWidth = input.nextInt();
        double[][] matrix = new double[matrixLength][matrixWidth];

        System.out.println("Enter matrix: ");

        for (int i = 0; i < matrixLength; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }

        System.out.println("Your result: \n");

        switch (choice) {
            case 1:
                for (int i = 0; i < matrixLength; i++) {
                    for (int j = 0; j < matrixWidth; j++) {
                        System.out.print(matrix[j][i] + " ");
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = 0; i < matrixLength; i++) {
                    for (int j = 0; j < matrixWidth; j++) {
                        System.out.print(matrix[matrixLength - 1 - j][matrixLength - 1 - i] + " ");
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 0; i < matrixLength; i++) {
                    for (int j = 0; j < matrixWidth; j++) {
                        System.out.print(matrix[i][matrixLength - 1 - j] + " ");
                    }
                    System.out.println();
                }
                break;
            case 4:
                for (int i = 0; i < matrixLength; i++) {
                    for (int j = 0; j < matrixWidth; j++) {
                        System.out.print(matrix[matrixLength - 1 - i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            default:
                System.out.println("Wrong choice!");
        }
    }

    public static void calculateDeterminant() {
        System.out.println("Enter size of matrix: ");

        int matrixLength = input.nextInt();
        int matrixWidth = input.nextInt();
        double[][] matrix = new double[matrixLength][matrixWidth];

        System.out.println("Enter matrix: ");

        for (int i = 0; i < matrixLength; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }

        System.out.println("The result is:\n" + calculationsOfDeterminant(matrix, matrixLength));
        determinant = 0;
    }


    public static double calculationsOfDeterminant(double[][] matrix, int matrixLength) {

        if (matrix[0].length == 1) {
            return matrix[0][0];
        }
        if (matrix[0].length == 3) {
            return matrix[0][0] * matrix[1][1] * matrix[2][2] + matrix[1][0] * matrix[2][1] * matrix[0][2] + matrix[2][0] * matrix[0][1] * matrix[1][2] -
                    matrix[0][2] * matrix[1][1] * matrix[2][0] - matrix[1][2] * matrix[2][1] * matrix[0][0] - matrix[2][2] * matrix[0][1] * matrix[1][0];
        }
        if (matrix[0].length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] - matrix[1][0];
        }
        if (matrix[0].length > 3) {
            for (int i = 0; i < matrixLength; i++) {
                if (i % 2 == 0) {
                    determinant = determinant + matrix[0][i] * calculationsOfDeterminant(subMatrix(matrix, matrixLength, i), matrixLength - 1);
                } else {
                    determinant = determinant + (-matrix[0][i] * calculationsOfDeterminant(subMatrix(matrix, matrixLength, i), matrixLength - 1));
                }
            }
        }

        return determinant;
    }

    public static double[][] subMatrix(double[][] array, int x, int a) {
        double[][] result = new double[x - 1][x - 1];

        for (int j = 1; j < array.length; j++) {
            for (int k = 0; k < array[0].length; k++) {
                if (k < a) {
                    result[j - 1][k] = array[j][k];
                } else if (k > a) {
                    result[j - 1][k - 1] = array[j][k];
                }
            }
        }

        return result;
    }
}
