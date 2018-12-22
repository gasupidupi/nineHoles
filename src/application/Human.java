package application;

import application.view.Tile;

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
	public Human(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
 
		
		// TODO Auto-generated method stub
		/**
		 * This is the humans y drop position.
		 */
		int y = Tile.getCurrentTyleY();
		/**
		 * This is the humans x drop position.
		 */
		int x = Tile.getCurrentTyleX();
		/**
		 * If the human places his pieces on an illegal spot, it will hinder the drop, display an error message and redo the method.
		 */
		if (!board.setPiece(y, x, piece)) {
			System.out.println(errorMsg);
			nextDrop();
		}
		}
	

	// Maybe move code-segment to Player
	public void nextMove(String direction) {
		// TODO Auto-generated method stub
		/**
		 * This is the humans y move position.
		 */
		int y = Tile.getCurrentTyleY();
		/**
		 * This is the humans x move position.
		 */
		int x = Tile.getCurrentTyleX();
		
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

	@Override
	public void nextMove() {
		// TODO Auto-generated method stub
		
	}



}
