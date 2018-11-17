package ch.wiss.nineHoles;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board board = new Board();

		Player player1 = new Human(board, Board.PIECE_X);
		Player player2 = new Human(board, Board.PIECE_O);

		Player currentPlayer = player1;

		while (board.getState() == State.DROP) {
			currentPlayer.nextDrop();
			board.display();
			if (currentPlayer != player2) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
			
		}

	}

}
