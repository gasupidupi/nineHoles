package application;

import java.util.ArrayList;
import java.util.List;

public class AI extends Player {

	/**
	 * AI Class
	 * This class extends the player class and rates the next possible moves in the next round.
	 */
	public AI(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
		// TODO Auto-generated method stub
		System.out.println(">> KI wählt eine Position aus.");
		Board testBoard = board.getCopy();
		List<int[]> emptyPos = testBoard.getAvailableFields();
		int[] dropRatings = dropRatings(emptyPos, testBoard);
		int[] bestPos = bestPos(dropRatings, emptyPos);
		board.setPiece(bestPos[0], bestPos[1], piece);

	}

	/**
	 * dropRatings
	 * This class triggeres the rating of a possible drop by the opponent and own piece.
	 * 
	 * @param emptyPos This is a List<int[]> list of all the available positions. e.g [[0,2][1,2][0,0]]
	 * @param testBoard The board you want to rate.
	 */
	private int[] dropRatings(List<int[]> emptyPos, Board testBoard) {
		int[] dropRatings = new int[emptyPos.size()];

		rateDrop(emptyPos, dropRatings, piece, 2);
		rateDrop(emptyPos, dropRatings, opponent, 1);

		return dropRatings;
	}

	/**
	 * rateDrop
	 * This class gets triggered by dropRatings and actually rates the drop.
	 * 
	 * @param emptyPos This is a List<int[]> list of all the available positions. e.g [[0,2][1,2][0,0]]
	 * @paramdropRatings A list with all the ratings corresponding to the index of emptyPos.
	 * @param piece Do you want to test the opponents possible drops or your own?
	 * @param factor The rating of the setting. e.g. 2
	 */
	private void rateDrop(List<int[]> emptyPos, int[] dropRatings, char piece, int factor) {
		for (int i = 0; i < emptyPos.size(); i++) {
			Board testInputBoard = board.getCopy();
			testInputBoard.setPiece(emptyPos.get(i)[0], emptyPos.get(i)[1], piece);
			if (piece == Board.PIECE_O && testInputBoard.getState() == State.O_WON
					|| piece == Board.PIECE_X && testInputBoard.getState() == State.X_WON) {
				dropRatings[i] += factor;
			}
		}
	}

	/**
	 * moveRatings
	 * This class triggeres the rating of a possible move by the opponent and own piece.
	 * 
	 * @param emptyPos This is a List<int[]> list of all the available moves. e.g [[0,2][1,2][0,0]]
	 * @param testBoard The board you want to rate.
	 */
	private int[] moveRatings(List<Move> possibleMoves, Board testBoard) {
		int[] moveratings = new int[possibleMoves.size()];

		rateMove(possibleMoves, moveratings, piece, 2);
		rateMove(possibleMoves, moveratings, opponent, 1);

		return moveratings;
	}

	/**
	 * rateMove
	 * This class gets triggered by moveRatings and actually rates the move.
	 * 
	 * @param possibleMoves This is a List<Move> list of all the available moves.
	 * @paramdropRatings A list with all the ratings corresponding to the index of possibleMoves.
	 * @param piece Do you want to test the opponents possible moves or your own?
	 * @param factor The rating of the setting. e.g. 2
	 */
	private void rateMove(List<Move> possibleMoves, int[] moveratings, char piece, int factor) {
		for (int i = 0; i < possibleMoves.size(); i++) {
			Board testInputBoard = board.getCopy();
			Move move = possibleMoves.get(i);
			switch (move.direction) {
			case 'u':
				testInputBoard.moveUp(move.y, move.x, piece);
				break;
			case 'd':
				testInputBoard.moveDown(move.y, move.x, piece);
				break;
			case 'r':
				testInputBoard.moveRight(move.y, move.x, piece);
				break;
			case 'l':
				testInputBoard.moveLeft(move.y, move.x, piece);
				break;
			}
			if (piece == Board.PIECE_O && testInputBoard.getState() == State.O_WON
					|| piece == Board.PIECE_X && testInputBoard.getState() == State.X_WON) {
				moveratings[i] += factor;
			}

		}
	}

	/**
	 * bestMove
	 * This class extracts the rating of all possible moves, set together by moveRatings.
	 * 
	 * @param moveRatings Your list with ratings corresponding to the index of possibleMoves.
	 * @param possilbeMoves List of all possible moves.
	 */
	private Move bestMove(int[] moveRatings, List<Move> possibleMoves) {
		int highestIndex = indexOfMax(moveRatings);
		Move bestMove = possibleMoves.get(highestIndex);
		return bestMove;

	}

	/**
	 * bestPos
	 * This class extracts the rating of all possible position, set together by dropRatings.
	 * 
	 * @param ratings Your list with ratings corresponding to the index of emptyPos.
	 * @param emptyPos List of all possible positions to drop.
	 */
	private int[] bestPos(int[] ratings, List<int[]> emptyPos) {
		int[] bestPos;

		int highestIndex = indexOfMax(ratings);

		if (highestIndex > 0) {
			bestPos = emptyPos.get(highestIndex);
		} else {
			List<Integer> indices = new ArrayList<Integer>();
			for (int i = 0; i < ratings.length; i++) {
				if (ratings[i] == 0) {
					indices.add(i);
				}
			}
			int randomIndex = indices.get(RandomPlayer.randomNumberInRange(0, indices.size()));
			bestPos = emptyPos.get(randomIndex);
		}

		
		return bestPos;
	}

	/**
	 * This class actually extracts the largest int in the int[] list.
	 * @param list The list you want the extract the highest value of.
	 */
	private int indexOfMax(int[] list) {
		int max = -100;
		int index = -1;
		for (int i = 0; i < list.length; i++) {
			if (list[i] > max) {
				max = list[i];
				index = i;
			}
		}
		return index;

	}

	@Override
	public void nextMove() {
		// TODO Auto-generated method stub
		System.out.println(">> KI wählt einen Zug.");
		Board testBoard = board.getCopy();
		List<Move> possibleMoves = testBoard.getAvailableMoves(piece);
		int[] Moveratings = moveRatings(possibleMoves, testBoard);
		Move bestMove = bestMove(Moveratings, possibleMoves);
		switch (bestMove.direction) {
		case 'u':
			board.moveUp(bestMove.y, bestMove.x, piece);
			break;
		case 'd':
			board.moveDown(bestMove.y, bestMove.x, piece);
			break;
		case 'r':
			board.moveRight(bestMove.y, bestMove.x, piece);
			break;
		case 'l':
			board.moveLeft(bestMove.y, bestMove.x, piece);
			break;
		}

	}


}
