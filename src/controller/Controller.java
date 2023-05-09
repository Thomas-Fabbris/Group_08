package controller;
import model.Player;
public class Controller {
	private Player firstPlayer;
	private final Token gameToken;
	private final MainController game;
	/**The constructor builds a new Controller Object, which controls the course of the entire game
	 	@param firstPlayer
 		@param game
	 **/
	public Controller(MainController game, Player firstPlayer) {
		//Parameters have to be passed by the object who initializes the game
		this.game = game;
		this.firstPlayer = firstPlayer;
		gameToken = new Token(firstPlayer);
	}
	
	public void nextTurn() {
		if(!game.gameState.equals(GameState.ENDED) && !checkEndOfGame()) {
			{
			
			}
		}
		
		
		
	}
	
	/**
	 * This method checks whether the current game has finished
	 * @return true if one player has completed his shelf, otherwise returns false
	 */
	public boolean checkEndOfGame() {
		Player currentPlayer = game.getCurrentPlayer();
		
		if(currentPlayer.bookshelf.isFull()) {
			currentPlayer.setEndOfGameToken(true);
			game.gameState = GameState.ENDED;
			return true;
		}
		
		return false;
	}
}

