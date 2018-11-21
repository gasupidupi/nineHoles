package ch.wiss.nineHoles;

import java.util.ArrayList;
import java.util.List;

public class Board {
	static final char PIECE_X = 'X';
	static final char PIECE_O = 'O';
	static final char EMPTY = '\u0000';
	private char[][] field  = new char[3][3];
	private int amountPieces = 0;
	private char lastPiece;
	String line = "-------------------------------------------------------";
	private int roundsCount = 0;
	
	public Board() {
		
	}
	
	/**
	 * the board
	 * This is the board.
	 * 
	 * @param field This is the char[][] array, where each position has its character. It can also be EMPTY which is '\u0000'
	 * @param amountPieces The amount of amount of pieces.
	 * @param lastPiece The piece that was moved last.
	 */
	private Board(char[][] field, int amountPieces, char lastPiece) {
		this.field = field;
		this.lastPiece = lastPiece;
		this.amountPieces = amountPieces;
	}

	/**
	 * Gets a copy of the board.
	 */
	public Board getCopy() {
		char[][] fieldCopy = new char[3][3];
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				fieldCopy[y][x] = field[y][x];
			}
		}
		return new Board(fieldCopy, amountPieces, lastPiece);
	}
	
	/**
	 * Get's the state and checks if a row/col is complete or 1000 rounds are surpassed.
	 */
	public State getState() {
		if (roundsCount > 1000) {
		return State.DRAW;
		}
		if (isARowComplete() || isAColComplete()) {
			if (lastPiece == PIECE_X) {
				return State.X_WON;
			} else if (lastPiece == PIECE_O) {
				return State.O_WON;
			}
		} else if (amountPieces < 6) {
			return State.DROP;
		} else if (lockedUp('O')) {
			return State.DRAW;
		} else if (lockedUp('X')) {
			return State.DRAW;
		} else {
			return State.MOVE;
		}

		return State.DRAW;
	}

	private boolean lockedUp(char c) {
			return getAvailableMoves(c).isEmpty();
	}

	/**
	 * Actually checks if a row is complete.
	 */
	private boolean isRowComplete(int y) {
		return field[y][0] != EMPTY && field[y][0] == field[y][1] && field[y][0] == field[y][2];
	}
	
	/**
	 * Actually checks if a column is complete.
	 */
	private boolean isColComplete(int x) {
		return field[0][x] != EMPTY && field[0][x] == field[1][x] && field[0][x] == field[2][x];
	}

	/**
	 * Checks if a row is complete by using isRowComplete method.
	 */
	private boolean isARowComplete() {
		for (int i = 0; i < 3; i++) {
			if (isRowComplete(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a column is complete by using isColComplete method.
	 */
	private boolean isAColComplete() {
		for (int i = 0; i < 3; i++) {
			if (isColComplete(i)) {
				return true;
			}
		}
		return false;
	}
	/*
	 * else { return State.DRAW; }
	 */

	/**
	 * Sets a piece.
	 * 
	 * @param y y-position of piece.
	 * @param x x-position of piece.
	 * @param piece With which players character you want to set a piece with.
	 */
	public boolean setPiece(int y, int x, char piece) {
		if (y < 3 && y > -1 && x < 3 && x > -1 && field[y][x] == EMPTY) {
			field[y][x] = piece;
			amountPieces++;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves a piece right.
	 * 
	 * @param y y-position of piece.
	 * @param x x-position of piece.
	 * @param piece With which players character you want to move a piece with.
	 */
	public boolean moveRight(int y, int x, char piece) {

		if (y < 3 && y > -1 && x + 1 < 3 && x + 1 > -1 && isFieldEmpty(y, x + 1) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y][x + 1] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			roundsCount++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves a piece up.
	 * 
	 * @param y y-position of piece.
	 * @param x x-position of piece.
	 * @param piece With which players character you want to move a piece with.
	 */
	public boolean moveUp(int y, int x, char piece) {
		if (!isFieldEmpty(y - 1, x)) {
			return false;
		}
		if (y - 1 < 3 && y - 1 > -1 && x < 3 && x > -1 && isFieldEmpty(y - 1, x) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y - 1][x] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			roundsCount++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves a piece left.
	 * 
	 * @param y y-position of piece.
	 * @param x x-position of piece.
	 * @param piece With which players character you want to move a piece with.
	 */
	public boolean moveLeft(int y, int x, char piece) {
		if (y < 3 && y > -1 && x - 1 < 3 && x - 1 > -1 && isFieldEmpty(y, x - 1) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y][x - 1] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			roundsCount++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves a piece down.
	 * 
	 * @param y y-position of piece.
	 * @param x x-position of piece.
	 * @param piece With which players character you want to move a piece with.
	 */
	public boolean moveDown(int y, int x, char piece) {

		if (y + 1 < 3 && y + 1 > -1 && x < 3 && x > -1 && isFieldEmpty(y + 1, x) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y + 1][x] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			roundsCount++;
			return true;
		} else {
			return false;
		}

	}

	/**
	 *Checks if a position is empty.
	 */
	public boolean isFieldEmpty(int y, int x) {
		return field[y][x] == EMPTY;
	}

	/**
	 *Checks if a direction is empty.
	 */
	public boolean isDirectionEmpty(int y, int x, char direction) {
		if (y - 1 < 3 && y - 1 > -1 && x < 3 && x > -1 && direction == 'u') {
			return isFieldEmpty(y - 1, x);
		} else if (y + 1 < 3 && y + 1 > -1 && x < 3 && x > -1 && direction == 'd') {
			return isFieldEmpty(y + 1, x);
		} else if (y < 3 && y > -1 && x - 1 < 3 && x - 1 > -1 && direction == 'l') {
			return isFieldEmpty(y, x - 1);
		} else if (y < 3 && y > -1 && x + 1 < 3 && x + 1 > -1 && direction == 'r') {
			return isFieldEmpty(y, x + 1);
		}
		return false;
	}

	/**
	 *Get a list with all available positions.
	 */
	public List<int[]> getAvailableFields() {
		List<int[]> availableFields = new ArrayList<int[]>();
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				if (isFieldEmpty(y, x)) {
					availableFields.add(new int[] { y, x });
				}
			}
		}

		return availableFields;
	}

	/**
	 *Get a list with all available moves.
	 */
	public List<Move> getAvailableMoves(char piece) {
		List<Move> availableMoves = new ArrayList<Move>();
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				if (field[y][x] == piece) {
					if (isDirectionEmpty(y, x, 'd')) {
						availableMoves.add(new Move(y, x, 'd'));
					}
					if (isDirectionEmpty(y, x, 'u')) {
						availableMoves.add(new Move(y, x, 'u'));
					}
					if (isDirectionEmpty(y, x, 'r')) {
						availableMoves.add(new Move(y, x, 'r'));
					}
					if (isDirectionEmpty(y, x, 'l')) {
						availableMoves.add(new Move(y, x, 'l'));
					}
				}
			}
		}
		return availableMoves;
	}

	/**
	 * Display the board.
	 */
	public void display() {
		System.out.println(line);
		for (int i = 0; i < 3; i++) {
			if (i < 1) {
				System.out.print("  ");
			} else {
				System.out.print("   ");
			}
			System.out.print(i);
		}
		System.out.print(" < x");
		System.out.println("");
		for (int y = 0; y < field.length; y++) {
			System.out.print(y);
			for (int x = 0; x < field[y].length; x++) {
				if (field[y][x] == EMPTY) {
					System.out.print("( )");
				} else {
					System.out.print("(");
					System.out.print(field[y][x]);
					System.out.print(")");
				}
				if (x < 2) {
					System.out.print("-");
				}
			}
			System.out.print("\n");
		}
		System.out.println("^");
		System.out.println("y");
		System.out.println(line);
	}

}
