package ch.wiss.nineHoles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {

	/**
	 * RandomPlayer Class
	 * This class extends the player class and uses randomness to place pieces.
	 */
	RandomPlayer(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	// importiert
	public static int randomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

	public void nextDrop() {
		/*
		 * if (!board.setPiece(randomNumberInRange(0,3), randomNumberInRange(0,3),
		 * piece)) { nextDrop(); }
		 */
		System.out.println(">> AI is picking a position.");
		int randomFieldNumber = randomNumberInRange(0, board.getAvailableFields().size());
		int[] randomField = board.getAvailableFields().get(randomFieldNumber);
		if (!board.setPiece(randomField[0], randomField[1], piece)) {
			nextDrop();
		}
	}

	public void nextMove() {
		
		System.out.println(">> AI is picking a Move.");
		
		List<Move> moves = board.getAvailableMoves(piece);
		
		int randomMoveId = randomNumberInRange(0,moves.size());
		Move randomMove = moves.get(randomMoveId);
		/**
		 * This switch checks the random move randomMove and moves in that direction.
		 */
		switch(randomMove.direction) {
		case 'u':
			board.moveUp(randomMove.y, randomMove.x, piece);
			break;
		case 'd':
			board.moveDown(randomMove.y, randomMove.x, piece);
			break;
		case 'r':
			board.moveRight(randomMove.y, randomMove.x, piece);
			break;
		case 'l':
			board.moveLeft(randomMove.y, randomMove.x, piece);
			break;
		}

	}
}
