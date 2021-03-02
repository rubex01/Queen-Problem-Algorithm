import java.util.Arrays;

public class QueenProblem {

    // Board size and N of queens
    private static final int N = 8;

    // Total number of solutions found
    private static int totalSolutions = 0;

    public static void main(String[] args) {

        // Initialize starting position of the board
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], 0);
        }

        // Start calculating solutions
        calculateOption(board, 0);

        // Report number of solutions found
        System.out.println("Solutions found: " + Integer.toString(QueenProblem.totalSolutions));
    }

    public static void calculateOption(int[][] currentRunningBoard, int currentRow) {

        // Solution found
        if (currentRow == N) {
            // Print solution and return
            printSolution(currentRunningBoard);
            return;
        }

        // For every column/square in the row
        for (int i = 0; i < currentRunningBoard[currentRow].length; i++) {
            // If the column/square is not locked
            if (currentRunningBoard[currentRow][i] == 0) {

                // Create new board instance
                int[][] newRunningBoard = new int[N][];
                for (int r = 0; r < N; r++) {
                    newRunningBoard[r] = Arrays.copyOf(currentRunningBoard[r], currentRunningBoard[r].length);
                }

                // Place queen on column/square
                newRunningBoard[currentRow][i] = 2;

                // Lock column and diagonals on which the queen was placed
                // (only column/diagonals in rows below current row are calculated for speed improvement)
                for (int x = 0; x < N; x++) {
                    if (x > currentRow) {
                        newRunningBoard[x][i] = 1;
                    }

                    if (currentRow + (x + 1) < N && i - x > 0) {
                        newRunningBoard[currentRow + (x + 1)][i - (x + 1)] = 1;
                    }
                    if (currentRow + (x + 1) < N && i + x < (N - 1)) {
                        newRunningBoard[currentRow + (x + 1)][i + (x + 1)] = 1;
                    }
                }

                // Start new iteration to solve the next row
                calculateOption(newRunningBoard, currentRow+1);
            }
        }
    }

    private static void printSolution(int[][] solution) {

        // Print the solution
        for (int y = 0; y < N; y++) {
            String solutionVisualization = "";
            for (int x = 0; x < N; x++) {
                if (solution[y][x] == 0 || solution[y][x] == 1) {
                    solutionVisualization += " - ";
                }
                else {
                    solutionVisualization += " Q ";
                }
            }
            System.out.println(solutionVisualization);
        }
        System.out.println("\n");

        // Add 1 to total found solutions
        totalSolutions = totalSolutions + 1;
    }
}
