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
		if (y<3 && y>-1 && x<3 && x>-1 && field[y][x] == EMPTY) {
			field[y][x] = piece;
			amountPieces++;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveRight(int y, int x, char piece) {

		if (y<3 && y>-1 && x+1<3 && x+1>-1 && isFieldEmpty(y,x+1) && field[y][x] != lastPiece ) {
			field[y][x+1] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveUp(int y, int x, char piece) {
		if(!isFieldEmpty(y-1,x)) {
			return false;
		}
		if (y-1<3 && y-1>-1 && x<3 && x>-1  && isFieldEmpty(y-1,x) && field[y][x] != lastPiece) {
			field[y-1][x] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveLeft(int y, int x, char piece) {
		if (y<3 && y>-1 && x-1<3 && x-1>-1 && isFieldEmpty(y,x-1) && field[y][x] != lastPiece) {
			field[y][x-1] = piece;
			field[y][x] = EMPTY;
			lastPiece = piece;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveDown(int y, int x, char piece) {

		if (y+1<3 && y+1>-1 && x<3 && x>-1 && isFieldEmpty(y+1,x) && field[y][x] != lastPiece) {
			field[y+1][x] = piece;
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
