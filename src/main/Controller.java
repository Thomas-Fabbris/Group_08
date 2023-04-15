package main;

public class Controller {
	public static Player firstPlayer;
	private final Token gameToken;
	
	public Controller(Player firstPlayer) {
		//firstPlayer parameter has to be passed by the object who initializes the game
		
		this.firstPlayer = firstPlayer;
		gameToken = new Token(firstPlayer);
	}
	
	
	
}
