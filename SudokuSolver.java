public class SudokuSolver {
    public static void main(String[] args) {
        int[][] sudoku = {
                {  0,3,0,7,0,6,4,0,8  },
                {  7,0,0,0,5,0,0,3,0  },
                {  0,0,2,0,0,3,0,0,6  },
                {  0,0,7,0,0,1,2,0,4  },
                {  0,9,0,3,8,2,0,7,0  },
                {  2,0,5,9,0,0,6,0,0  },
                {  4,0,0,6,0,0,1,0,0  },
                {  0,6,0,0,1,0,0,0,9  },
                {  9,0,1,8,0,5,0,6,0  }
        };
        if (solveSudoku(sudoku)) {
            printSudoku(sudoku);
        } else {
            System.out.println("No solution exists");
        }
    }

    private static boolean solveSudoku(int[][] sudoku) {
        int row = 0;
        int col = 0;
        boolean isEmpty = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                break;
            }
        }
        if (!isEmpty) {
            return true;
        }
        for (int num = 1; num <= 9; num++) {
            if (isSafe(sudoku, row, col, num)) {
                sudoku[row][col] = num;
                if (solveSudoku(sudoku)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] sudoku, int row, int col, int num) {
        // check row
        for (int j = 0; j < 9; j++) {
            if (sudoku[row][j] == num) {
                return false;
            }
        }
        // check column
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == num) {
                return false;
            }
        }
        // check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }
}