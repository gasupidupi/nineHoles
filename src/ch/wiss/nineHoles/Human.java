package ch.wiss.nineHoles;

public class Human extends Player {

	private String errorMsg = "##################ERROR:OCCUPIED/NOT ON BOARD/INCORRECT PIECE##################";
	private String yPosMsg = String.format("Player %c Y-Pos?", piece);
	private String xPosMsg = String.format("Player %c X-Pos?", piece);
	private String directionMsg = "Which direction(u,d,r,l)? ";
	private String direction;
	private int x;
	private int y;

	Human(Board board, char piece) {
		super(board, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextDrop() {
		// TODO Auto-generated method stub
		int y = ConsoleReader.readInteger(yPosMsg);
		int x = ConsoleReader.readInteger(xPosMsg);
		if (!board.setPiece(y, x, piece)) {
			System.out.println(errorMsg);
			nextDrop();
		}
	}

	// Maybe move code-segment to Player
	@Override
	public void nextMove() {
		// TODO Auto-generated method stub
		y = ConsoleReader.readInteger(yPosMsg);
		x = ConsoleReader.readInteger(xPosMsg);
		direction = ConsoleReader.readString(directionMsg);
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

}
