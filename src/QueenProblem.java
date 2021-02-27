import java.util.Arrays;

public class QueenProblem {

    // Board size and N of queens
    private static final int boardSize = 8;

    // Total number of solutions found
    private static int totalSolutions = 0;

    public static void main(String[] args) {

        // Initialize starting position of the board
        int[][] board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(board[i], 0);
        }

        // Start calculating solutions
        calculateOption(board, 0);

        // Report number of solutions found
        System.out.println("Solutions found: " + Integer.toString(QueenProblem.totalSolutions));
    }

    public static void calculateOption(int[][] currentRunningBoard, int currentRow) {

        // Solution found
        if (currentRow == QueenProblem.boardSize) {
            // Print solution and return
            QueenProblem.printSolution(currentRunningBoard);
            return;
        }

        // For every column/square in the row
        for (int i = 0; i < currentRunningBoard[currentRow].length; i++) {
            // If the column/square is not locked
            if (currentRunningBoard[currentRow][i] == 0) {

                // Set local board size variable
                int boardSize = QueenProblem.boardSize;

                // Create new board instance
                int[][] newRunningBoard = new int[boardSize][];
                for (int r = 0; r < boardSize; r++) {
                    newRunningBoard[r] = Arrays.copyOf(currentRunningBoard[r], currentRunningBoard[r].length);
                }

                // Place queen on column/square
                newRunningBoard[currentRow][i] = 2;

                // Lock column/square
                for (int x = 0; x < boardSize; x++) {
                    if (x > currentRow) {
                        newRunningBoard[x][i] = 1;
                    }
                }

                // Lock diagonal lines
                for (int f = 0; f < boardSize; f++) {
                    int newRow = currentRow + (f + 1);
                    if (i - (f + 1) >= 0 && newRow < boardSize) {
                        newRunningBoard[newRow][i - (f + 1)] = 1;
                    }
                    if (i + (f + 1) <= (boardSize - 1) && newRow < boardSize) {
                        newRunningBoard[newRow][i + (f + 1)] = 1;
                    }
                }

                // Start new iteration to solve the next row
                QueenProblem.calculateOption(newRunningBoard, currentRow+1);
            }
        }
    }

    private static void printSolution(int[][] solution) {

        // Print the solution
        for (int y = 0; y < QueenProblem.boardSize; y++) {
            String solutionVisualization = "";
            for (int x = 0; x < QueenProblem.boardSize; x++) {
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
        QueenProblem.totalSolutions = QueenProblem.totalSolutions + 1;
    }
}
