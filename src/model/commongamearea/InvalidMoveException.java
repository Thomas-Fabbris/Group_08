package model.commongamearea;

public class InvalidMoveException extends RuntimeException{	

	private static final long serialVersionUID = 7170372016189930018L;

	public InvalidMoveException(String message) {
		super(message);
	}
}
