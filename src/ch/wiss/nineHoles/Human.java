package ch.wiss.nineHoles;

public class Human extends Player {


	
	private String errorMsg = "------------------------INVALID------------------------";
	private String yPosMsg = String.format(">> Spieler %c y-Pos", piece);
	private String xPosMsg = String.format(">> Spieler %c x-Pos", piece);
	private String directionMsg = "Wählen Sie eine Richtung. (u,d,r,l)";
	private String direction;
	private int x;
	private int y;

	/**
	 * Human class
	 * This class extends the player class and uses human input to place pieces.
	 */
	Human(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
		// TODO Auto-generated method stub
		/**
		 * This is the humans y drop position.
		 */
		int y = ConsoleReader.readInteger(yPosMsg);
		/**
		 * This is the humans x drop position.
		 */
		int x = ConsoleReader.readInteger(xPosMsg);
		/**
		 * If the human places his pieces on an illegal spot, it will hinder the drop, display an error message and redo the method.
		 */
		if (!board.setPiece(y, x, piece)) {
			System.out.println(errorMsg);
			nextDrop();
		}
	}

	// Maybe move code-segment to Player
	@Override
	public void nextMove() {
		// TODO Auto-generated method stub
		/**
		 * This is the humans y move position.
		 */
		y = ConsoleReader.readInteger(yPosMsg);
		/**
		 * This is the humans x move position.
		 */
		x = ConsoleReader.readInteger(xPosMsg);
		direction = ConsoleReader.readString(directionMsg);
		/**
		 * This switch checks the input and moves in that direction.
		 */
		switch (direction){
		case "u":
			if(!board.moveUp(y, x, piece)) {
				System.out.println(errorMsg);
				nextMove();
			}
			break;
		case "d":
			if(!board.moveDown(y, x, piece)) {
				System.out.println(errorMsg);
				nextMove();
			}
			break;
		case "r":
			if(!board.moveRight(y, x, piece)) {
				System.out.println(errorMsg);
				nextMove();
			}
			break;
		case "l":
			if(!board.moveLeft(y, x, piece)) {
				System.out.println(errorMsg);
				nextMove();
			}
			break;
		default:
			nextMove();
		}
	}

}
