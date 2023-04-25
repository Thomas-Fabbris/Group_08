package main;

import personalgamearea.Bookshelf;

public class Controller {
	public static Player firstPlayer;
	private final Token gameToken;
	private final Game game;
	
	/**
	 * The constructor builds a new Controller Object, which controls the course of the entire game
	 * @param firstPlayer
	 * @param game
	 */
	public Controller(Game game, Player firstPlayer) {
		//Parameters have to be passed by the object who initializes the game
		
		this.game = game;
		this.firstPlayer = firstPlayer;
		gameToken = new Token(firstPlayer);
	}
	
	/**
	 * This method determines whether a player has filled completely his shelf and assigns him the end of game token, if necessary.
	 * @param playerShelves
	 */
	public void assignEndOfGameToken(Bookshelf[] playerShelves) {
		for(Bookshelf shelf: playerShelves){
			if(shelf.getPlayer().equals(gameToken.getCurrentOwner())){
				if(shelf.isFull()) {
					for(Player player : game.players) {
						if(player.equals(gameToken.getCurrentOwner())) {
							player.setEndOfGameToken(true);
						}
					}
					return;
				}
			}
		}
	}
	
	
	
	
	
}
