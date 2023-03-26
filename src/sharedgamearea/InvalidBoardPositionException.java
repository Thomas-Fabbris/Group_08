package sharedgamearea;

public class InvalidBoardPositionException extends RuntimeException{	
	public InvalidBoardPositionException(int row, int column) {
		super("Row " + row + " column " + column + " is not a valid position on the board!");
	}
}
