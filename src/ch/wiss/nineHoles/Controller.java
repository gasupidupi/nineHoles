package ch.wiss.nineHoles;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String logo = "   ___      _    _    ____    _        ______    _____ \r\n" + 
				"  / _ \\    | |  | |  / __ \\  | |      |  ____|  / ____|\r\n" + 
				" | (_) |   | |__| | | |  | | | |      | |__    | (___  \r\n" + 
				"  \\__, |   |  __  | | |  | | | |      |  __|    \\___ \\ \r\n" + 
				"    / /    | |  | | | |__| | | |____  | |____   ____) |\r\n" + 
				"   /_/     |_|  |_|  \\____/  |______| |______| |_____/ \r\n" + 
				"";


		Board board = new Board();

		Player player1 = new RandomPlayer(board, Board.PIECE_X);
		Player player2 = new AI(board, Board.PIECE_O);

		Player currentPlayer = player1;
		
		System.out.print(logo);
		
		board.display();

		while (board.getState() == State.DROP) {
			currentPlayer.nextDrop();
			board.display();
			if (currentPlayer != player2) {
				currentPlayer = player2;

			} else {
				currentPlayer = player1;

			}
		}
		
		while (board.getState() == State.MOVE) {
			currentPlayer.nextMove();
			board.display();
			if (currentPlayer != player2) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
		}
		
		if(board.getState() == State.O_WON) {
			System.out.println("Player O won.");
		} else if(board.getState() == State.X_WON) {
			System.out.println("Player X won.");
		} else {
			System.out.println("It's a draw.");
		}

	}



}
