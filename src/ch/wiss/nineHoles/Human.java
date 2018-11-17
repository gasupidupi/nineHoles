package ch.wiss.nineHoles;

public class Human extends Player{
	


	
	Human(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
		// TODO Auto-generated method stub
		int y = ConsoleReader.readInteger(String.format("Player %c Y-Pos?", piece));
		int x = ConsoleReader.readInteger(String.format("Player %c X-Pos?", piece));
		if(!board.setPiece(y, x, piece)) {
			System.out.println("##################ERROR:OCCUPIED##################");
			nextDrop();
		}
	}

	@Override
	public void nextMove() {
		// TODO Auto-generated method stub
		
	}

	
 
	
	

	
	
}
