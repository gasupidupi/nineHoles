package ch.wiss.nineHoles;

/**
 * 9 Holes Spiel
 * Dieses Spiel wurde nach Angaben der WISS als bewertetes Projekt erstellt.
 *
 * @author Schaffluetzel Gabriel
 * @version 1.0
 */

public class Controller {
	
	/**
	 * Controller class
	 * This class initialises the players, starts the main board and checks who won.
	 *
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String logo = "   ___      _    _    ____    _        ______    _____ \r\n" + 
				"  / _ \\    | |  | |  / __ \\  | |      |  ____|  / ____|\r\n" + 
				" | (_) |   | |__| | | |  | | | |      | |__    | (___  \r\n" + 
				"  \\__, |   |  __  | | |  | | | |      |  __|    \\___ \\ \r\n" + 
				"    / /    | |  | | | |__| | | |____  | |____   ____) |\r\n" + 
				"   /_/     |_|  |_|  \\____/  |______| |______| |_____/ \r\n" + 
				"Version 1.0";
		String firmenName = "Topomedics ©";

		Board board = new Board();

		/**
		 * Initialisation of the player 1.
		 */
		Player player1 = new AI(board, Board.PIECE_X);
		
		/**
		 * Initialisation of the player 2.
		 */
		Player player2 = new Human(board, Board.PIECE_O);

		/**
		 * The first currentPlayer is player1, but the variable will change to player2 in the next round.
		 */
		Player currentPlayer = player1;
		
		System.out.println(logo);
		System.out.println(firmenName);
		
		board.display();

		/**
		 * This is the controllers drop phase. It also changes currentPlayer.
		 */
		while (board.getState() == State.DROP) {
			currentPlayer.nextDrop();
			board.display();
			if (currentPlayer != player2) {
				currentPlayer = player2;

			} else {
				currentPlayer = player1;

			}
		}
		
		/**
		 * This is the controllers moving phase. It also changes currentPlayer.
		 */
		while (board.getState() == State.MOVE) {
			currentPlayer.nextMove();
			board.display();
			if (currentPlayer != player2) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
		}
		
		/**
		 * This is the controllers drop phase. It also changes currentPlayer.
		 */
		if(board.getState() == State.O_WON) {
			System.out.println("Player O won.");
		} else if(board.getState() == State.X_WON) {
			System.out.println("Player X won.");
		} else if(board.getState() == State.DRAW) {
			System.out.println("1000 moves surpassed. It's a draw.");
		}

	}



}
