package ch.wiss.nineHoles;

public abstract class Player {

	Board board;
	char piece;
	
	Player(Board board, char piece) {
		this.board = board;
		this.piece = piece;
	}
	
	abstract public void nextDrop();
	
	abstract public void nextMove();
}
