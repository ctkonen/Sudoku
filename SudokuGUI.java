import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuGUI {
    private Sudoku sudoku = new Sudoku();
    private JTextField[][] fields = new JTextField[9][9];
    private JFrame frame = new JFrame("Sudoku Solver");

    public SudokuGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(9, 9));
        boardPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i;  // Final variable for i
                final int col = j;  // Final variable for j
                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Arial", Font.BOLD, 20));
                field.setBackground(Color.LIGHT_GRAY);
                int value = sudoku.getCellValue(row, col);
                if (value != 0) {
                    field.setText(String.valueOf(value));
                    field.setEditable(false);  // Make prefilled cells non-editable
                    field.setBackground(Color.WHITE);
                }
                field.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent e) {
                        JTextField textField = (JTextField) e.getSource();
                        String text = textField.getText().trim();
                        int num = 0;
                        try {
                            num = Integer.parseInt(text);
                        } catch (NumberFormatException ex) {
                            textField.setText("");  // Clear invalid input
                            return;
                        }
                        if (!sudoku.isValidPlacement(row, col, num)) {
                            textField.setForeground(Color.RED);
                        } else {
                            textField.setForeground(Color.BLACK);
                        }
                    }
                });

                // Set borders for 3x3 subgrid
                int top = (i % 3 == 0) ? 2 : 1;
                int left = (j % 3 == 0) ? 2 : 1;
                int bottom = (i % 3 == 2) ? 2 : 1;
                int right = (j % 3 == 2) ? 2 : 1;
                field.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                fields[i][j] = field;
                boardPanel.add(field);
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Arial", Font.BOLD, 16));
        solveButton.setBackground(new Color(34, 139, 34));  // Dark green
        solveButton.setForeground(Color.GREEN);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(220, 20, 60));  // Crimson red
        resetButton.setForeground(Color.RED);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void solve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = fields[i][j].getText().trim();
                int value = 0;
                try {
                    value = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    fields[i][j].setText("");  // Clear invalid inputs
                    continue;
                }
                sudoku.board[i][j] = value;
            }
        }
        if (sudoku.solve(0, 0)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    fields[i][j].setText(String.valueOf(sudoku.board[i][j]));
                    fields[i][j].setBackground(Color.WHITE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No solution exists.");
        }
    }

    private void resetBoard() {
        sudoku.generatePuzzle(17);  // Regenerate the puzzle
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = sudoku.getCellValue(i, j);
                if (value != 0) {
                    fields[i][j].setText(String.valueOf(value));
                    fields[i][j].setEditable(false);  // Make prefilled cells non-editable
                    fields[i][j].setBackground(Color.WHITE);
                } else {
                    fields[i][j].setText("");
                    fields[i][j].setEditable(true);
                    fields[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    public static void main(String[] args) {
        new SudokuGUI();
    }
}

