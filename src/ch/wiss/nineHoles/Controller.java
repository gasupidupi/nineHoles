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
		String line = "-------------------------------------------------------";


		Board board = new Board();
		
		System.out.print(logo);
		System.out.println(line);
		int gameMode = ConsoleReader.readInteger("Which gamemode do you want to play? Human vs. AI (1), Human vs. Human (2), AI vs. AI(3)");
		
		Player player1 = new RandomPlayer(board, Board.PIECE_X);
		Player player2 = new AI(board, Board.PIECE_O);
		
		Player currentPlayer = player1;
		
		if(gameMode == 1) {
			player1 = new Human(board, Board.PIECE_X);
			player2 = new AI(board, Board.PIECE_O);
			int firstPlayer = ConsoleReader.readInteger("Who should start? AI (1) Human (2)");
			if(firstPlayer == 1) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
		} else if(gameMode == 2) {
			player1 = new Human(board, Board.PIECE_X);
			player2 = new Human(board, Board.PIECE_O);
		} else if(gameMode == 3) {
			player1 = new AI(board, Board.PIECE_X);
			player2 = new AI(board, Board.PIECE_O);
		}

		
		

		
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
		} else if(board.getState() == State.DRAW){
			System.out.println("100 moves surpassed. It's a draw.");
		}

	}



}
