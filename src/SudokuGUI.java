import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SudokuGUI extends JFrame {
	private Sudoku sudoku = new Sudoku();
	private JTextField[][] textField;

	public static void main(String[] args) {
		new SudokuGUI();
	}

	public SudokuGUI() {
		JFrame frame = new JFrame();
		frame.setTitle("Sudoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField = new JTextField[9][9];
		frame.setSize(300, 330);
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		frame.add(textPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		Font font = new Font("Verdana", Font.PLAIN, 20);
		textPanel.setLayout(new GridLayout(9, 9));
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		textPanel.setBorder(blackBorder);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j] = new JTextField();
				textField[i][j].setFont(font);
				textField[i][j].setHorizontalAlignment(JTextField.CENTER);
				textField[i][j].setBorder(blackBorder);
				textPanel.add(textField[i][j]);
			}
		}

		JButton solve = new JButton("Solve!");
		solve.setForeground(Color.BLUE);
		solve.addActionListener(new SolveActionListener());
		buttonPanel.add(solve);
		solve.setToolTipText("Solves the Sudoku");

		JButton clear = new JButton("Clear!");
		clear.setForeground(Color.RED);
		clear.addActionListener(new ClearActionListener());
		buttonPanel.add(clear);
		clear.setToolTipText("Clears the grid");

		pack();
		frame.setVisible(true);
	}

	/**
	 * Creates a ActionListener that activates when the user press the Solve
	 * button in the interface
	 */
	public class SolveActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			sudoku.clear();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					String string = textField[i][j].getText();
					if (string.equals("1") || string.equals("2")
							|| string.equals("3") || string.equals("4")
							|| string.equals("5") || string.equals("6")
							|| string.equals("7") || string.equals("8")
							|| string.equals("9")) {
						if (!sudoku.checkBox(i, j, Integer.parseInt(string))
								|| !sudoku
										.checkRow(i, Integer.parseInt(string))
								|| !sudoku
										.checkCol(j, Integer.parseInt(string))) {
							JOptionPane.showMessageDialog(null,
									"Error: No solution could be found!");
							return;
						}
						sudoku.insertNbr(i, j, Integer.parseInt(string));
					} else if (string.equals("")) {
						sudoku.insertNbr(i, j, 0);
					} else {
						JOptionPane.showMessageDialog(null,
								"Error: Character/number not supported!");
						return;

					}
				}
				
			}
			if(sudoku.solveSudoku()){
				sudoku.solveSudoku();
			}
			else{
				JOptionPane.showMessageDialog(null, "no solution found");
				return;
			}
			sudoku.solveSudoku();
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					String nbr = sudoku.getNbr(i, k);
					textField[i][k].setText(nbr);
				}
			}
		}
	}

	/**
	 * Creates a ActionListener that activates when the user press the Clear
	 * button in the interface
	 */
	public class ClearActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					textField[i][k].setText("");
				}
			}
			sudoku.clear();
		}
	}
}