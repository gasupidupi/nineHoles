package ch.wiss.nineHoles;

public class Board {
	static final char PIECE_X = 'X';
	static final char PIECE_O = 'O';
	static final char EMPTY = '\u0000';
	private char[][] field = new char[3][3];
	private int amountPieces = 0;
	private char lastPiece;

	public State getState() {
		if (isARowComplete() || isAColComplete()) {
			if (lastPiece == PIECE_X) {
				return State.X_WON;
			} else if (lastPiece == PIECE_O) {
				return State.O_WON;
			}
		} else if (amountPieces < 6) {
			return State.DROP;
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
		if (y<3 && x<3 && field[y][x] == EMPTY) {
			field[y][x] = piece;
			amountPieces++;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}

	public void display() {
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				if (field[y][x] == EMPTY) {
					System.out.print('-');
				} else {
					System.out.print(field[y][x]);
				}

			}
			System.out.print("\n");
		}
	}
}
