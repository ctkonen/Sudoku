# Sudoku Solver and Generator

This repository contains a Java implementation of a Sudoku solver and generator with a graphical user interface (GUI). The program allows users to generate a Sudoku puzzle, input their own values, and solve the puzzle using a backtracking algorithm.

## Features

- **Sudoku Generation**: Generates a Sudoku puzzle with a specified number of clues.
- **Sudoku Solving**: Uses a backtracking algorithm to solve the Sudoku puzzle.
- **Graphical User Interface**: Allows users to interact with the Sudoku puzzle through a simple GUI.
- **Input Validation**: Checks if the user inputs are valid according to Sudoku rules.

## Files

- `Sudoku.java`: Contains the logic for generating and solving the Sudoku puzzle.
- `SudokuGUI.java`: Contains the GUI code for interacting with the Sudoku puzzle.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/sudoku-solver.git
   cd sudoku-solver
   ```

2. Compile the Java files:
   ```sh
   javac Sudoku.java SudokuGUI.java
   ```

3. Run the application:
   ```sh
   java SudokuGUI
   ```

## Usage

### SudokuGUI

The `SudokuGUI` class creates a window with a 9x9 grid representing the Sudoku board. Users can input numbers into the cells and click the "Solve" button to solve the puzzle or the "Reset" button to generate a new puzzle.

### Sudoku

The `Sudoku` class contains the logic for solving and generating Sudoku puzzles. The key methods are:

- `solve(int row, int col)`: Solves the Sudoku puzzle using a backtracking algorithm.
- `generatePuzzle(int clues)`: Generates a Sudoku puzzle with the specified number of clues.
- `isValidPlacement(int row, int col, int num)`: Checks if placing a number in a specific cell is valid.
- `printBoard()`: Prints the Sudoku board to the console.

## Example

Here is an example of how to use the `Sudoku` class to generate and print a Sudoku puzzle:

```java
public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.generatePuzzle(17); // Generate a puzzle with 17 clues
        sudoku.printBoard(); // Print the generated puzzle
    }
}
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License.

---

Feel free to modify this `README.md` file to suit your specific needs. If you have any questions or issues, please open an issue on GitHub.
