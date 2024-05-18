import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Sudoku {
    public int[][] board = new int[9][9];
    private Random random = new Random();

    public Sudoku() {
        generatePuzzle(17); // Generate a puzzle with 17 clues initially
    }

    public boolean solve(int row, int col) {
        if (col == 9) {
            col = 0;
            row++;
            if (row == 9) return true; // Entire board filled successfully
        }

        if (board[row][col] != 0) return solve(row, col + 1);

        Integer[] nums = getRandomNumbers(); // Get shuffled numbers 1-9
        for (int num : nums) {
            if (isValidPlacement(row, col, num)) {
                board[row][col] = num;
                if (solve(row, col + 1)) return true;
                board[row][col] = 0; // Reset on backtrack
            }
        }
        return false; // Trigger backtracking
    }

    private Integer[] getRandomNumbers() {
        Integer[] nums = new Integer[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(nums), random); // Shuffle array
        return nums;
    }

    public boolean isValidPlacement(int row, int col, int num) {
        return !isInRow(row, num) && !isInCol(col, num) && !isInBox(row - row % 3, col - col % 3, num);
    }

    public boolean isInRow(int row, int num) {
        for (int i = 0; i < 9; i++)
            if (board[row][i] == num) return true;
        return false;
    }

    public boolean isInCol(int col, int num) {
        for (int i = 0; i < 9; i++)
            if (board[i][col] == num) return true;
        return false;
    }

    public boolean isInBox(int startRow, int startCol, int num) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i + startRow][j + startCol] == num) return true;
        return false;
    }

    public void generatePuzzle(int clues) {
        solve(0, 0); // Ensure the board is filled
        ArrayList<Integer> positions = new ArrayList<>(81);
        for (int i = 0; i < 81; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions, random);
        for (int i = 0; i < 81 - clues; i++) {
            int pos = positions.get(i);
            board[pos / 9][pos % 9] = 0; // Clear the cell
        }
    }
    public int getCellValue(int row, int col) {
        return board[row][col];
    }
    

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-----------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(board[i][j] != 0 ? board[i][j] : ".");
            }
            System.out.println();
        }
    }
}



        // Scanner size_puzzle = new Scanner(System.in); // Create a Scanner object
        // System.out.println("Enter n for how many spots filled in the puzzle: ");
        // int num_fill = size_puzzle.nextInt(); // Read user input

        // Now you can access each list by its name
        // For example, to add an element to H-1
       // lists.get("H-1").add(1);
        
        // And to add an element to H-2
       // lists.get("H-2").add(2);
        
        // Let's print the elements of H-1 and H-2 for demonstration
        // System.out.println("Contents of H-1: " + lists.get("H-1"));
        // System.out.println("Contents of H-2: " + lists.get("H-2"));