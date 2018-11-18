package ch.wiss.nineHoles;

public abstract class Player {

	Board board;
	char piece;
	char opponent;

	Player(Board board, char piece) {
		this.board = board;
		this.piece = piece;
		if (this.piece == Board.PIECE_O) {
			this.opponent = Board.PIECE_X;
		} else {
			this.opponent = Board.PIECE_O;
		}

	}

	abstract public void nextDrop();

	abstract public void nextMove();
}
