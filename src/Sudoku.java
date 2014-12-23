public class Sudoku {
	private int[][] grid;

	/**
	 * Solves a Sudoku with help of recursion and backtracking methods.
	 */
	public Sudoku() {
		grid = new int[9][9];
	}

	/**
	 * The recursive method that finds a valid number for one cell
	 * @param row the row in the grid
	 * @param col the column in the grid       
	 */
	public boolean solve(int row, int col) {
		if(row > 8){
			return true;
		}
		else if (grid[row][col] != 0) {
				return nextCell(row, col);
			} else {
				for (int i = 1; i < 10; i++) {
				if (checkCol(col, i) && checkRow(row, i)
						&& checkBox(row, col, i)) {
					grid[row][col] = i;
					if(nextCell(row, col)){
						return true;
					}
				}
			}
			grid[row][col] = 0;
		}
		return false;
			
	}

	/**
	 * Starts to solve the sudoku
	 */
	public boolean solveSudoku() {
			return solve(0,0);
			
	}

	/**
	 * Inserts a number into a specified cell
	 * @param row the row in the grid
	 * @param col the column in the grid
	 * @param num the number inserted to the cell
	 */
	public void insertNbr(int row, int col, int num) {
		if (num > 0 && num < 10) {
			grid[row][col] = num;
		}
	}

	/**
	 * Returns the number in a specified cell
	 * @param row the row in the grid
	 * @param col the column in the grid
	 * @return the number in a specified cell
	 */
	public String getNbr(int row, int col) {
		return String.valueOf(grid[row][col]);
	}

	/**
	 * Solves the next cell in the grid
	 * @param row the row in the grid
	 * @param col the column in the grid
	 */
	public boolean nextCell(int row, int col) {
		if (col < 8) {
			return solve(row, col + 1);
		} else {
			return solve(row + 1, 0);
		}
	}

	/**
	 * Checks if a specified number is acceptable for the box, 
	 * @param row the row in the grid
	 * @param col the column in the grid
	 * @param num number in a specified cell
	 * @return true if the number is acceptable, otherwise returns false
	 */
	public boolean checkBox(int row, int col, int num) {
		row = (row / 3) * 3;
		col = (col / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[row + i][col + j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if a specified number is acceptable for the given column
	 * @param col the row in the grid
	 * @param num number in a specified cell
	 * @return true if the number is acceptable, otherwise returns false
	 */
	public boolean checkCol(int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] == num) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a specified number is acceptable for the given row 
	 * @param row the row in the grid
	 * @param num number in a specified cell
	 * @return true if the number is acceptable, otherwise returns false
	 */
	public boolean checkRow(int row, int num) {
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == num) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Clears the grid, sets all cells to 0
	 */
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = 0;
			}
		}
	}
}