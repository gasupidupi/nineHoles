package ch.wiss.nineHoles;

import java.util.ArrayList;
import java.util.List;

public class AI extends Player{

	AI(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
		// TODO Auto-generated method stub
		

			Board testBoard = board.getCopy();
			List<int[]> emptyPos = testBoard.getAvailableFields();
			int[] ratings = ratings(emptyPos, testBoard);
			int[] bestPos = bestPos(ratings, emptyPos);
			board.setPiece(bestPos[0], bestPos[1], piece);
			
	}
	
	private int[] ratings(List<int[]> emptyPos, Board testBoard) {
		 int[] ratings = new int[emptyPos.size()];
			
			for(int i = 0; i<emptyPos.size(); i++){
				Board testInputBoard = board.getCopy();
				testInputBoard.setPiece(emptyPos.get(i)[0], emptyPos.get(i)[1], piece);
				if(piece == Board.PIECE_O && testInputBoard.getState() == State.O_WON || piece == Board.PIECE_X && testInputBoard.getState() == State.X_WON){
					ratings[i] = 2;
				}
				if(opponent == Board.PIECE_O && testInputBoard.getState() == State.O_WON || opponent == Board.PIECE_X && testInputBoard.getState() == State.X_WON) {
					ratings[i] = 1;
				}
				else {
					ratings[i] = 0;
				}
			
			}
		
		return ratings;
	}
	
	private int[] moveRatings(List<Move> possibleMoves, Board testBoard) {
		 int[] moveratings = new int[possibleMoves.size()];
			
			for(int i = 0; i<possibleMoves.size(); i++){
				Board testInputBoard = board.getCopy();
				Move move = possibleMoves.get(i);
				switch(move.direction) {
				case 'u':
					board.moveUp(move.y, move.x, piece);
					break;
				case 'd':
					board.moveDown(move.y, move.x, piece);
					break;
				case 'r':
					board.moveRight(move.y, move.x, piece);
					break;
				case 'l':
					board.moveLeft(move.y, move.x, piece);
					break;
				}
				if(piece == Board.PIECE_O && testInputBoard.getState() == State.O_WON || piece == Board.PIECE_X && testInputBoard.getState() == State.X_WON){
					moveratings[i] = 2;
				}
				if(opponent == Board.PIECE_O && testInputBoard.getState() == State.O_WON || opponent == Board.PIECE_X && testInputBoard.getState() == State.X_WON) {
					moveratings[i] = 1;
				}
				else {
					moveratings[i] = 0;
				}
			
			}
		
		return moveratings;
	}
	
	private Move bestMove(int[] moveRatings, List<Move> possibleMoves) {
		int highestIndex = indexOfMax(moveRatings);
		Move bestMove = possibleMoves.get(highestIndex);  
		return bestMove;
		
	}
	
	private int[] bestPos(int[] ratings, List<int[]> emptyPos) {
		int highestIndex = indexOfMax(ratings);
		int[] bestPos = emptyPos.get(highestIndex);  
		return bestPos;
	}
	
	private int indexOfMax(int[] list) {
		int max = -100;
		int index = -1;
		for(int i=0; i<list.length; i++) {
			if(list[i] > max) {
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
		switch(bestMove.direction) {
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
