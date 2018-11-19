package ch.wiss.nineHoles;

import java.util.ArrayList;
import java.util.List;

public class AI extends Player {

	AI(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
		// TODO Auto-generated method stub

		Board testBoard = board.getCopy();
		List<int[]> emptyPos = testBoard.getAvailableFields();
		int[] dropRatings = dropRatings(emptyPos, testBoard);
		int[] bestPos = bestPos(dropRatings, emptyPos);
		board.setPiece(bestPos[0], bestPos[1], piece);

	}

	private int[] dropRatings(List<int[]> emptyPos, Board testBoard) {
		int[] dropRatings = new int[emptyPos.size()];

		rateDrop(emptyPos, dropRatings, piece, 2);
		rateDrop(emptyPos, dropRatings, opponent, 1);

		return dropRatings;
	}

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

	private int[] moveRatings(List<Move> possibleMoves, Board testBoard) {
		int[] moveratings = new int[possibleMoves.size()];

		rateMove(possibleMoves, moveratings, piece, 2);
		rateMove(possibleMoves, moveratings, opponent, 1);

		return moveratings;
	}

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

	private Move bestMove(int[] moveRatings, List<Move> possibleMoves) {
		int highestIndex = indexOfMax(moveRatings);
		Move bestMove = possibleMoves.get(highestIndex);
		return bestMove;

	}

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
