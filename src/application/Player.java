package application;

public abstract class Player {


	
	Board board;
	/**
	 * The current players piece.
	 */
	char piece;
	/**
	 * The current players opponents piece.
	 */
	char opponent;

	/**
	 * Player class
	 * This class is abstract and is the upper class of players.
	 * 
	 * @param board This is the board, that the player requires.
	 * @param piece This is the player character piece, which also will be seen on the board.
	 */
	Player(Board board, char piece) {
		this.board = board;
		this.piece = piece;
		if (this.piece == Board.PIECE_O) {
			this.opponent = Board.PIECE_X;
		} else {
			this.opponent = Board.PIECE_O;
		}

	}

	/**
	 * This will be triggered during the dropping phase.
	 */
	abstract public void nextDrop();
	/**
	 * This will be triggered during the moving phase.
	 */
	abstract public void nextMove();
	



}
