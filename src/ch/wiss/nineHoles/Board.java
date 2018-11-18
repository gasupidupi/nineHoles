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
	
	public Board() {
		
	}
	
	private Board(char[][] field, int amountPieces, char lastPiece) {
		this.field = field;
		this.lastPiece = lastPiece;
		this.amountPieces = amountPieces;
	}

	public Board getCopy() {
		char[][] fieldCopy = new char[3][3];
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				fieldCopy[y][x] = field[y][x];
			}
		}
		return new Board(fieldCopy, amountPieces, lastPiece);
	}
	
	public State getState() {
		if (isARowComplete() || isAColComplete()) {
			if (lastPiece == PIECE_X) {
				return State.X_WON;
			} else if (lastPiece == PIECE_O) {
				return State.O_WON;
			}
		} else if (amountPieces < 6) {
			return State.DROP;
		} else {
			return State.MOVE;
		}
		return State.DRAW;
	}

	private boolean isRowComplete(int y) {
		return field[y][0] != EMPTY && field[y][0] == field[y][1] && field[y][0] == field[y][2];
	}

	private boolean isColComplete(int x) {
		return field[0][x] != EMPTY && field[0][x] == field[1][x] && field[0][x] == field[2][x];
	}

	private boolean isARowComplete() {
		for (int i = 0; i < 3; i++) {
			if (isRowComplete(i)) {
				return true;
			}
		}
		return false;
	}

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

	public boolean moveRight(int y, int x, char piece) {

		if (y < 3 && y > -1 && x + 1 < 3 && x + 1 > -1 && isFieldEmpty(y, x + 1) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y][x + 1] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}

	public boolean moveUp(int y, int x, char piece) {
		if (!isFieldEmpty(y - 1, x)) {
			return false;
		}
		if (y - 1 < 3 && y - 1 > -1 && x < 3 && x > -1 && isFieldEmpty(y - 1, x) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y - 1][x] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}

	public boolean moveLeft(int y, int x, char piece) {
		if (y < 3 && y > -1 && x - 1 < 3 && x - 1 > -1 && isFieldEmpty(y, x - 1) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y][x - 1] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}

	public boolean moveDown(int y, int x, char piece) {

		if (y + 1 < 3 && y + 1 > -1 && x < 3 && x > -1 && isFieldEmpty(y + 1, x) && field[y][x] != lastPiece
				&& !isFieldEmpty(y, x)) {
			field[y + 1][x] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}

	}

	public boolean isFieldEmpty(int y, int x) {
		return field[y][x] == EMPTY;
	}

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
