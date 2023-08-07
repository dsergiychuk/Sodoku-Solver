// author: Addie Maurus, Daniella Sergyichuk
// description: Project 2; Sudoku
// due: June 3, 2022

import java.util.*;

public class Sudoku {

	static char[][] solved; // declaring solved  
	
	public static void main(String[] args) {

		char[][] puzzle = SudokuP.puzzle(); //initializes SudokuP.java
		solved = puzzle; // stores the solved puzzle
		System.out.println("Starting Board");
		printPuzzle(puzzle); // prints the unsolved puzzle
		solve(puzzle);
		
		
	//--------------------------------------------
	// Several invalid tests.		  
	//--------------------------------------------
		char[][] test1 = new char[][]{{'5','3','.','.','7','.','.','.','.'},
										{'6','.','.','1','9','5','.','.','4'},
										{'.','9','8','.','.','.','.','6','.'},
										{'8','.','.','.','6','.','.','.','3'},
										{'4','.','.','8','.','3','.','.','1'},
										{'7','.','.','.','2','.','.','.','6'},
										{'.','6','.','.','.','.','2','8','.'},
										{'.','.','.','4','1','9','.','.','.'},
										{'.','.','.','.','8','.','.','7','9'}};
										
		char[][] test2 = new char[][]{{'1','2','3','4','5','6','7','8','9'},
										{'.','.','3','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'},
										{'.','.','.','.','.','.','.','.','.'}};
		char[][] test3 = new char[][]{{'.','4','.','.','.','.','7','.','.'},
										{'.','4','1','.','6','.','.','9','.'},
										{'.','5','.','.','7','.','.','.','1'},
										{'.','.','.','2','.','.','.','8','.'},
										{'4','.','.','3','.','.','.','5','.'},
										{'.','.','5','.','.','1','.','.','9'},
										{'.','7','.','.','3','.','.','.','.'},
										{'.','.','.','9','.','5','.','.','.'},
										{'5','.','6','.','.','.','.','1','2'}};
						
		// printPuzzle(test3); // Testing the invalid puzzles
		// solve(test3);

	}

	//--------------------------------------------
	//check - Checks if the given puzzle (complete
	//		  or not) is valid or not.
	//--------------------------------------------
	public static boolean check(char[][] puzzle) {
		int box = 0; /// new code
		if (puzzle == null || puzzle.length != 9 || puzzle[0].length != 9)
			return false;
		// check each column
		for (int i = 0; i < 9; i++) {
			boolean[] m = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] != '.') {
					if (m[(int) (puzzle[i][j] - '1')]) {
						return false;
					}
					m[(int) (puzzle[i][j] - '1')] = true;
				}
			}
	}
	
	for (int j = 0; j < 9; j++) { //check each row
		boolean[] m = new boolean[9];
		for (int i = 0; i < 9; i++) {
			if (puzzle[i][j] != '.') {
				if (m[(int) (puzzle[i][j] - '1')]) {
					return false;
				}
				m[(int) (puzzle[i][j] - '1')] = true;
			}
		}
	}
	
	for ( box = 0; box < 9; box++) { // checks each 3x3 matrix
		boolean[] m = new boolean[9];
		for (int i = box / 3 * 3; i < box / 3 * 3 + 3; i++) {
			for (int j = box % 3 * 3; j < box % 3 * 3 + 3; j++) {
				if (puzzle[i][j] != '.') {
					if (m[(int) (puzzle[i][j] - '1')]) {
						return false;
					}
					m[(int) (puzzle[i][j] - '1')] = true;
				}
			}
		}
	}
	return true;
    }

	//--------------------------------------------
	// printPuzzle - Prints the puzzle out.
	//--------------------------------------------
	public static void printPuzzle(char[][] puzzle) { 
		for (int i = 0;  i< 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(puzzle[i][j] + " "); 
            }
            System.out.println();
        }
	}

	//--------------------------------------------
	// solve - Returns whether the puzzle is 
	//		   solvable or not.
	//--------------------------------------------
	public static boolean solve(char[][] puzzle) {
		if ( puzzle.length == 0 || puzzle == null ) { // Checks if the puzzle is valid
			System.out.println("The given sudoku puzzle is invalid");
			return false;
		}
		if (solvePuzzle(puzzle)) { // checks if the puzzle was solved
			System.out.println("Solution: ");
			printPuzzle(solved); // Prints the solution
			return true;
		} else { // if the puzzle was not solvable
			System.out.println("The puzzle was not solvable.");
			return false;
		}
	}

	//--------------------------------------------
	// solvePuzzle - Solves the unsolved puzzle.
	//--------------------------------------------
	public static boolean solvePuzzle(char[][] puzzle){
		int row = -1;
		int col = -1;
		boolean loop = false;
		for (int i = 0; i < 9; i++) {
			if(loop) {
				break;
			}
			for(int j = 0; j<9; j++) {
				if(puzzle[i][j] == '.') {
					row = i;
					col = j;
					loop = true;
					break;
				}
			}
		}
		if(row == -1) {
			return true;
		} else {
			char error = 'e'; // error
			for(int i = 0; i<9; i++) {
				char a = (char)(i+49); // converts int into char
				if(isValid(puzzle,row,col,a)) {
					puzzle[row][col] = a;
					if (solvePuzzle(puzzle)) {
						error = a;
						break;
					}
				}
			}
			if (error!='e') {
				solved[row][col] = error;
				return true;
			} else {
				puzzle[row][col] = '.';
				return false;
			}
		}
	}
	
	public static boolean isValid(char[][] puzzle, int row, int col, char a) {
		puzzle[row][col] = a;
		return check(puzzle);
	}

}
