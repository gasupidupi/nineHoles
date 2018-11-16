package ch.wiss.nineHoles;

public class Board {
	static final char PIECE_X = 'X';
	static final char PIECE_Y = 'O';
	private char[][] field = new char[3][3];
	
	public void setPiece(int x, int y, char piece) {
		field[x][y] = piece;
	}
}
